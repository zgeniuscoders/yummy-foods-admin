package cd.zgeniuscoders.yummyfoodsadmin.orders.domain.usecases

import android.util.Patterns
import cd.zgeniuscoders.yummyfoodsadmin.util.ValidationResponse

class ValidateEmail {

    fun execute(email: String): ValidationResponse {

        if (email.isEmpty()) {
            return ValidationResponse(
                isSuccess = false,
                error = "email field is require"
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


}