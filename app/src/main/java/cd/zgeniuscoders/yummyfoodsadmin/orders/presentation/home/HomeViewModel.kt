package cd.zgeniuscoders.yummyfoodsadmin.orders.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cd.zgeniuscoders.yummyfoodsadmin.orders.data.mappers.toOrderList
import cd.zgeniuscoders.yummyfoodsadmin.orders.domain.repository.OrderRepository
import cd.zgeniuscoders.yummyfoodsadmin.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val orderRepository: OrderRepository
) : ViewModel() {


    private val _state = MutableStateFlow(HomeState())
    var state = _state
        .onStart {
            getOrders()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )

    fun onTriggerEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.OnOrderMarkAsCanceled -> markOrderAsDelivered(event.orderId, event.userId)
            is HomeEvent.OnOrderMarkAsDelivered -> markOrderAsCanceled(event.orderId, event.userId)
        }
    }

    private fun markOrderAsCanceled(orderId: String, userId: String) {
        viewModelScope.launch {

            _state.update {
                it.copy(flashMessage = "")
            }

            orderRepository
                .cancelOrder(orderId, userId)
                .onEach { res ->
                    when (res) {
                        is Resource.Error -> {
                            _state.update {
                                it.copy(flashMessage = res.message.toString())
                            }
                        }

                        is Resource.Success -> {
                            _state.update {
                                it.copy(flashMessage = "Success")
                            }
                        }
                    }
                }.launchIn(viewModelScope)
        }
    }

    private fun markOrderAsDelivered(orderId: String, userId: String) {
        viewModelScope.launch {

            _state.update {
                it.copy(flashMessage = "")
            }

            orderRepository
                .markAsDelivered(orderId, userId)
                .onEach { res ->
                    when (res) {
                        is Resource.Error -> {
                            _state.update {
                                it.copy(flashMessage = res.message.toString())
                            }
                        }

                        is Resource.Success -> {
                            _state.update {
                                it.copy(flashMessage = "Success")
                            }
                        }
                    }
                }.launchIn(viewModelScope)
        }
    }

    private fun getOrders() {
        viewModelScope.launch {

            _state.update {
                it.copy(flashMessage = "")
            }

            orderRepository
                .getOrders()
                .onEach { res ->

                    when (res) {
                        is Resource.Error -> {
                            _state.update {
                                it.copy(flashMessage = res.message.toString())
                            }
                        }

                        is Resource.Success -> {
                            val orders = res.data?.toOrderList()
                            _state.update {
                                it.copy(orders = orders ?: emptyList())
                            }
                        }
                    }

                }
                .launchIn(viewModelScope)
        }
    }
}