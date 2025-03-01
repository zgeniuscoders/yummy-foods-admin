package cd.zgeniuscoders.yummyfoodsadmin.orders.presentation.home

sealed interface HomeEvent {

    data class OnOrderMarkAsDelivered(val orderId: String,val userId: String): HomeEvent
    data class OnOrderMarkAsCanceled(val orderId: String,val userId: String): HomeEvent

}