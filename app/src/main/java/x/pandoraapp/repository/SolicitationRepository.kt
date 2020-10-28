package x.pandoraapp.repository

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import x.pandoraapp.config.RetrofitConfig
import x.pandoraapp.controllers.SolicitationController

object SolicitationRepository {

    private val api by lazy {
        RetrofitConfig()
    }

    private var disposable = CompositeDisposable()

    fun getSolicitations(controller: SolicitationController) {
        disposable.add(
            api.getSolicitationData()
                .getSolicitationsById()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    controller.defineSolicitations(it)
                }, {
                    controller.defineErr("Deu Ruim")
                })
        )
    }

    fun clearDisposable() {
        disposable.clear()
    }
}