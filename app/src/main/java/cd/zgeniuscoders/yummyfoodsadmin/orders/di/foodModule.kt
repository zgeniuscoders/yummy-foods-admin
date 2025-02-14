package cd.zgeniuscoders.yummyfoodsadmin.orders.di

import cd.zgeniuscoders.yummyfoodsadmin.orders.data.repository.CustomerRepositoryImpl
import cd.zgeniuscoders.yummyfoodsadmin.orders.data.repository.OrderRepositoryImpl
import cd.zgeniuscoders.yummyfoodsadmin.orders.domain.repository.CustomerRepository
import cd.zgeniuscoders.yummyfoodsadmin.orders.domain.repository.OrderRepository
import cd.zgeniuscoders.yummyfoodsadmin.orders.presentation.home.HomeViewModel
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val foodModule = module {

    single<FirebaseFirestore> {
        FirebaseFirestore.getInstance()
    }

    single<OrderRepository> {
        val context = androidContext()
        OrderRepositoryImpl(
            get(),
            context
        )
    }

    single<CustomerRepository> {
        val context = androidContext()
        CustomerRepositoryImpl(get(), context)
    }

    viewModelOf(::HomeViewModel)

}