package x.pandoraapp.services

import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST
import x.pandoraapp.models.User
import x.pandoraapp.models.UserRequest

interface UserService {
    @POST("user/logar")
    fun logIn(@Body userRequest: UserRequest) : Single<User>
}