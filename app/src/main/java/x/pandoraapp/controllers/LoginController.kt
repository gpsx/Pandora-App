package x.pandoraapp.controllers

import androidx.lifecycle.MutableLiveData
import x.pandoraapp.models.User
import x.pandoraapp.models.UserRequest
import x.pandoraapp.repository.UserRepository

class LoginController {
    val error: MutableLiveData<String> = MutableLiveData()

    val data: MutableLiveData<User> =  MutableLiveData()

    fun logIn(userRequest: UserRequest)
            = UserRepository.logIn(this, userRequest)

    fun defineErr(err: String?) {
        error.value = err
    }

    fun login(user: User) = this.data.postValue(user)
}