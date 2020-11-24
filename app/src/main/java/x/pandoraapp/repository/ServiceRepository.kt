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

    fun getServiceByWord(controller: ServiceController, filtro: String) {
        disposable.add(
            api.getServiceData()
                .getServiceByWord(filtro)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    controller.defineServices(it)
                }, {
                    controller.defineErr("Deu Ruim")
                })
        )
    }

    fun getServiceById(controller: ServiceController, id : Int){
        disposable.add(
            api.getServiceData()
                .getServiceById(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    controller.defineService(it)
                }, {
                    controller.defineErr("Deu Ruim")
                })
        )
    }


    fun clearDisposable() {
        disposable.clear()
    }

}