package x.pandoraapp.repository

import com.google.gson.JsonElement
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import org.json.JSONObject
import x.pandoraapp.config.RetrofitConfig
import x.pandoraapp.constraints.USER_ID_BUNDLE
import x.pandoraapp.controllers.SolicitationController
import x.pandoraapp.models.User

object SolicitationRepository {

    private val api by lazy {
        RetrofitConfig()
    }

    private var disposable = CompositeDisposable()

    fun getSolicitations(controller: SolicitationController, id: Int) {
        disposable.add(
            api.getSolicitationData()
                .getSolicitationsById(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    controller.defineSolicitations(it)
                }, {
                    controller.defineErr("Deu Ruim")
                })
        )
    }

    fun getFilteredSolicitation(controller: SolicitationController, id: Int, filter: String) {
        disposable.add(
            api.getSolicitationData()
                .getFilteredSolicitation(id, filter)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    controller.defineSolicitations(it)
                }, {
                    controller.defineErr(it.message)
                })
        )
    }

    fun createSolicitation(
        controller: SolicitationController,
        idPres: Int,
        idSol: Int?,
        description: String
    ) {
        val json = "{\"descricao\": \"$description\"}"
        disposable.add(
            api.getSolicitationData()
                .createSolicitation(
                    idPres,
                    idSol,
                    RequestBody.create(
                        "application/json; charset=utf-8".toMediaTypeOrNull(),
                        json
                    )
                )
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                }, {
                    controller.defineErr("Deu Ruim")
                })
        )
    }

    fun clearDisposable() {
        disposable.clear()
    }

}