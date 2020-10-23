package x.pandoraapp.repository

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import x.pandoraapp.config.RetrofitConfig
import x.pandoraapp.controllers.ExampleController

object ExampleRepository {

//    private val api by lazy {
//        RetrofitConfig()
//    }
//    private var disposable = CompositeDisposable()
//
//    fun getInfo(controller: ExampleController) {
//        disposable.add(
//            api.getExampleService()
//                .pickCep()
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe({
//                    controller.defineData(it)
//                }, {
//                    controller.defineErr("Deu Ruim")
//                })
//        )
//    }
//
//
//    fun clearDisposable() {
//        disposable.clear()
//    }

}