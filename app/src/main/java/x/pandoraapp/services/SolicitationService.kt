package x.pandoraapp.services

import io.reactivex.Single
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.*
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

    @GET("/solicitacoes/solicitante/buscar/{id}")
    fun getFilteredSolicitation(
        @Path("id") id : Int,
        @Query("status") status : String
    ) : Single<List<Solicitation>>
}