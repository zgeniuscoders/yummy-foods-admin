package cd.zgeniuscoders.yummyfoodsadmin.util

data class ValidationResponse(
    val isSuccess:Boolean,
    val error: String? = null
)