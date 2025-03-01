package cd.zgeniuscoders.yummyfoodsadmin.orders.domain.services

import cd.zgeniuscoders.yummyfoodsadmin.util.ValidationResponse

interface ValidationServices {

    fun require(field: String, label: String): ValidationResponse

    fun email(email: String, label: String): ValidationResponse

    fun number(number: String, label: String): ValidationResponse

    fun url(url: String, label: String): ValidationResponse

    fun minLength(length: Int, field: String): ValidationResponse

    fun maxLength(length: Int, field: String): ValidationResponse


}