package x.pandoraapp.services

import io.reactivex.Single
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import x.pandoraapp.models.Solicitation

interface SolicitationService {
    @GET("/solicitacoes/buscar/{id}?isSolicitante=true")
    fun getSolicitationsById(@Path("id") id : Int): Single<List<Solicitation>>

    @POST("/solicitacoes/nova_solicitacao/{idPres}/{idSol}")
    fun createSolicitation(
        @Path("idPres") idPres: Int,
        @Path("idSol") idSol: Int?,
        @Body description: RequestBody
    ) : Single<Response<Any>>
}