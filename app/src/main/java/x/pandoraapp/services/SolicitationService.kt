package x.pandoraapp.services

import io.reactivex.Single
import retrofit2.http.GET
import x.pandoraapp.models.Solicitation

interface SolicitationService {
    @GET("/solicitacoes/buscar/7?isSolicitante=true")
    fun getSolicitationsById(): Single<List<Solicitation>>
}