package x.pandoraapp.services

import io.reactivex.Single
import retrofit2.http.GET
import x.pandoraapp.models.Service

interface ServiceService {
    @GET("servicos/todos")
    fun getAllServices() : Single<List<Service>>
}