package x.pandoraapp.config

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import x.pandoraapp.services.ExampleService
import x.pandoraapp.services.ServiceService

class RetrofitConfig {
    lateinit var retrofit: Retrofit

    init {
        retrofitConfig()
    }

    private fun retrofitConfig() {
        retrofit = Retrofit.Builder()
            .client(CustomHttpClient().getOkHttpClient())
            .baseUrl("http://ec2-54-211-82-37.compute-1.amazonaws.com:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

//    fun getExampleService(): ExampleService {
//        return this.retrofit.create(ExampleService::class.java)
//    }

    fun getServiceData(): ServiceService{
        return this.retrofit.create(ServiceService::class.java)
    }
}