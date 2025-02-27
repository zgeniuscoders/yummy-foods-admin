package cd.zgeniuscoders.yummyfoodsadmin.orders.data.services

import android.util.Patterns
import cd.zgeniuscoders.yummyfoodsadmin.orders.domain.services.ValidationServices
import cd.zgeniuscoders.yummyfoodsadmin.util.ValidationResponse

class ValidationServiceImpl : ValidationServices {
    override fun require(field: String, label: String): ValidationResponse {
        if (field.isEmpty()) {
            return ValidationResponse(isSuccess = false, error = "The $label is  required")
        }
        return ValidationResponse(isSuccess = true)
    }

    override fun email(email: String, label: String): ValidationResponse {

        if (email.isEmpty()) {
            return ValidationResponse(
                isSuccess = false,
                error = "The email field is require"
            )
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResponse(
                isSuccess = false,
                error = "Enter a validate email"
            )
        }

        return ValidationResponse(isSuccess = true)
    }

    override fun number(number: String, label: String): ValidationResponse {
        if (number.toIntOrNull() == null && number.toDoubleOrNull() == null) {
            return ValidationResponse(
                isSuccess = false,
                error = "The $label field  must be a number"
            )
        }
        return ValidationResponse(isSuccess = true)
    }

    override fun url(url: String, label: String): ValidationResponse {

        if (url.isEmpty()) {
            return ValidationResponse(
                isSuccess = false,
                error = "The $label field is require"
            )
        }

        if (Patterns.WEB_URL.matcher(url).matches()) {
            return ValidationResponse(
                isSuccess = false,
                error = "The $label must be a validate web uri"
            )
        }
        return ValidationResponse(isSuccess = true)
    }

    override fun minLength(length: Int, field: String): ValidationResponse {
        if (field.length <= length) {
            return ValidationResponse(
                isSuccess = false,
                error = "The $field must be less then $length characters"
            )
        }
        return ValidationResponse(isSuccess = true)
    }

    override fun maxLength(length: Int, field: String): ValidationResponse {
        if (field.length >= length) {
            return ValidationResponse(
                isSuccess = false,
                error = "The $field must be grate then $length characters"
            )
        }
        return ValidationResponse(isSuccess = true)
    }
}