package cd.zgeniuscoders.yummyfoodsadmin.orders.domain.usecases

import android.util.Patterns
import cd.zgeniuscoders.yummyfoodsadmin.util.ValidationResponse
import java.util.regex.Pattern

class ValidateName {

    fun execute(name: String): ValidationResponse{

        if (name.isEmpty()){
            return ValidationResponse(
                isSuccess = false,
                error = "name field is require"
            )
        }

        return ValidationResponse(isSuccess = true)

    }


}