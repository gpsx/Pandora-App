package x.pandoraapp.repository

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import x.pandoraapp.config.RetrofitConfig
import x.pandoraapp.controllers.LoginController
import x.pandoraapp.controllers.ServiceController
import x.pandoraapp.models.UserRequest

object UserRepository {

    private val api by lazy {
        RetrofitConfig()
    }

    private var disposable = CompositeDisposable()

    fun logIn(controller: LoginController, userRequest: UserRequest) {
        disposable.add(
            api.getUserData()
                .logIn(userRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    controller.login(it)
                }, {
                    controller.defineErr(it.message)
                })
        )
    }


    fun clearDisposable() {
        disposable.clear()
    }

}