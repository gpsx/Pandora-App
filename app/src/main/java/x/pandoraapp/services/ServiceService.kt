package x.pandoraapp.services

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import x.pandoraapp.models.Service

interface ServiceService {
    @GET("servicos/todos")
    fun getAllServices(): Single<List<Service>>

    @GET("/servicos/busca-palavra")
    fun getServiceByWord(@Query("filtro") filtro: String): Single<List<Service>>

    @GET("/servicos/por-id/{id}")
    fun getServiceById(@Path("id") id : Int) : Single<List<Service>>

    @GET("/servicos/buscar-categoria/{id}")
    fun getServiceByCategory(@Path("id") id : Int): Single<List<Service>>
}