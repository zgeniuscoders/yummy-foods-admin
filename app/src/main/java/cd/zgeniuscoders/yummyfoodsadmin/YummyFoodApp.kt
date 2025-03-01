package cd.zgeniuscoders.yummyfoodsadmin

import android.app.Application
import cd.zgeniuscoders.yummyfoodsadmin.orders.di.foodModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class YummyFoodApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(
                foodModule
            )
        }

    }

}