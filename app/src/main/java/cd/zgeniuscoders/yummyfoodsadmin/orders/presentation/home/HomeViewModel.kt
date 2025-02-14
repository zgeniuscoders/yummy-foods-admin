package cd.zgeniuscoders.yummyfoodsadmin.orders.presentation.home

import android.util.Log
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
                            _state.update {
                                it.copy(orders = res.data!!.toOrderList())
                            }
                        }
                    }

                }
                .launchIn(viewModelScope)
        }
    }
}