package cd.zgeniuscoders.yummyfoodsadmin.orders.data.repository

import android.content.Context
import cd.zgeniuscoders.yummyfoodsadmin.R
import cd.zgeniuscoders.yummyfoodsadmin.orders.data.dto.CustomerCountDto
import cd.zgeniuscoders.yummyfoodsadmin.orders.data.dto.CustomerDto
import cd.zgeniuscoders.yummyfoodsadmin.orders.data.dto.CustomerDtoData
import cd.zgeniuscoders.yummyfoodsadmin.orders.data.dto.CustomersDto
import cd.zgeniuscoders.yummyfoodsadmin.orders.domain.repository.CustomerRepository
import cd.zgeniuscoders.yummyfoodsadmin.util.Resource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class CustomerRepositoryImpl(
    private val db: FirebaseFirestore,
    private val context: Context
) : CustomerRepository {

    private val collection = db.collection("users")

    override suspend fun getCustomers(): Flow<Resource<CustomersDto>> = callbackFlow {
        collection
            .addSnapshotListener { value, error ->

                if (error != null) {
                    trySend(
                        Resource.Error(
                            message = error.message.toString()
                        )
                    )
                }

                if (value != null) {
                    val data = value.toObjects(CustomerDtoData::class.java)
                    trySend(
                        Resource.Success(
                            CustomersDto(
                                data = data
                            )
                        )
                    )
                }

            }

        awaitClose()
    }

    override suspend fun getCustomer(customerId: String): Flow<Resource<CustomerDto>> =
        callbackFlow {
            collection
                .document(customerId)
                .addSnapshotListener { value, error ->

                    if (error != null) {
                        trySend(
                            Resource.Error(
                                message = error.message.toString()
                            )
                        )
                    }

                    if (value != null) {
                        val data = value.toObject(CustomerDtoData::class.java)

                        if (data != null) {
                            trySend(
                                Resource.Success(
                                    CustomerDto(
                                        data = data
                                    )
                                )
                            )
                        }else{
                            trySend(
                                Resource.Error(
                                    message = context.getString(R.string.cant_retrieve_customers)
                                )
                            )
                        }

                    }

                }

            awaitClose()
        }

    override suspend fun getTotalCustomersCount(): Flow<Resource<CustomerCountDto>> = callbackFlow {
        collection
            .addSnapshotListener { value, error ->

                if (error != null) {
                    trySend(
                        Resource.Error(
                            message = error.message.toString()
                        )
                    )
                }

                if (value != null) {
                    trySend(
                        Resource.Success(
                            CustomerCountDto(
                                data = value.count()
                            )
                        )
                    )
                }

            }

        awaitClose()
    }

}