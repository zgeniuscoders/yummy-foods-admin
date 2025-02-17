package cd.zgeniuscoders.yummyfoodsadmin.orders.data.repository

import android.content.Context
import android.util.Log
import cd.zgeniuscoders.yummyfoods.food.data.dto.OrderDtoData
import cd.zgeniuscoders.yummyfoods.food.data.dto.OrdersDto
import cd.zgeniuscoders.yummyfoodsadmin.R
import cd.zgeniuscoders.yummyfoodsadmin.orders.data.dto.CustomerDtoData
import cd.zgeniuscoders.yummyfoodsadmin.orders.domain.repository.OrderRepository
import cd.zgeniuscoders.yummyfoodsadmin.util.Resource
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class OrderRepositoryImpl(
    private val db: FirebaseFirestore,
    private val context: Context
) : OrderRepository {

    private val collection = db.collection("users")

    override suspend fun cancelOrder(orderId: String, userId: String): Flow<Resource<Boolean>> =
        callbackFlow {
        try {

            collection
                .document(userId)
                .collection("orders")
                .document(orderId)
                .update("orderStatus", "annuler")

            trySend(
                Resource.Success(
                    true
                )
            )

        } catch (e: Exception) {
            trySend(
                Resource.Error(
                    message = e.message.toString()
                )
            )
        } catch (e: FirebaseFirestoreException) {
            trySend(
                Resource.Error(
                    message = e.message.toString()
                )
            )
        }

        awaitClose()
    }

    override suspend fun markAsDelivered(orderId: String, userId: String): Flow<Resource<Boolean>> =
        callbackFlow {
        try {

            collection
                .document(userId)
                .collection("orders")
                .document(orderId)
                .update("orderStatus", "livrer")

            trySend(
                Resource.Success(
                    true
                )
            )

        } catch (e: Exception) {
            trySend(
                Resource.Error(
                    message = e.message.toString()
                )
            )
        } catch (e: FirebaseFirestoreException) {
            trySend(
                Resource.Error(
                    message = e.message.toString()
                )
            )
        }

        awaitClose()
    }

    override suspend fun getOrders(): Flow<Resource<OrdersDto>> = callbackFlow {
        try {

            collection
                .addSnapshotListener { value, error ->

                    if (error != null) {
                        error.printStackTrace()
                        throw Exception(context.getString(R.string.error_retrieve_orders))
                    }

                    if (value != null) {

                        val customers = value.toObjects(CustomerDtoData::class.java)

                        for (customer in customers) {

                            val ordersList: MutableList<OrderDtoData> = mutableListOf()

                            val orderCollection = collection
                                .document(customer.userId)
                                .collection("orders")

                            orderCollection
                                .whereEqualTo("orderStatus", "en attente")
                                .addSnapshotListener { orderCollectionValue, orderCollectionerror ->

                                    if (orderCollectionerror != null) {
                                        orderCollectionerror.printStackTrace()
                                        throw Exception(context.getString(R.string.error_retrieve_orders))
                                    }

                                    if (orderCollectionValue != null) {
                                        val orders =
                                            orderCollectionValue.toObjects(OrderDtoData::class.java)
                                        for (order in orders) {
                                            if (order != null) {
                                                ordersList.add(order)
                                            }
                                        }

                                        trySend(
                                            Resource.Success(
                                                data = OrdersDto(
                                                    ordersList
                                                )
                                            )
                                        )
                                    }

                                }


                        }



                    }

                }

        } catch (e: Exception) {
            trySend(
                Resource.Error(
                    message = e.message.toString()
                )
            )
        } catch (e: FirebaseFirestoreException) {
            trySend(
                Resource.Error(
                    message = e.message.toString()
                )
            )
        }

        awaitClose()
    }
}