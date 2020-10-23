package x.pandoraapp.repository

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import x.pandoraapp.config.RetrofitConfig
import x.pandoraapp.controllers.ExampleController
import x.pandoraapp.controllers.ServiceController

object ServiceRepository {

    private val api by lazy {
        RetrofitConfig()
    }

    private var disposable = CompositeDisposable()

    fun getAllServices(controller: ServiceController) {
        disposable.add(
            api.getServiceData()
                .getAllServices()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    controller.defineServices(it)
                }, {
                    controller.defineErr("Deu Ruim")
                })
        )
    }


    fun clearDisposable() {
        disposable.clear()
    }

}