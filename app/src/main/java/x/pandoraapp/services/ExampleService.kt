package x.pandoraapp.services

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import x.pandoraapp.models.CEP

interface ExampleService {
    @GET("01001000/json/")
    fun pickCep(): Single<CEP>
}