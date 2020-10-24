package x.pandoraapp.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import x.pandoraapp.R
import x.pandoraapp.constraints.USER_ID_BUNDLE
import x.pandoraapp.controllers.LoginController
import x.pandoraapp.models.User
import x.pandoraapp.models.UserRequest
import x.pandoraapp.repository.SharedPreferencesRepositoryImplementation
import x.pandoraapp.utils.observe

class LoginActivity : AppCompatActivity() {
    private val controller by lazy { LoginController() }
    private val sharedRepository by lazy {
        SharedPreferencesRepositoryImplementation<User>(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide();
        setContentView(R.layout.activity_login)
        bindObservers()
        enterButton.setOnClickListener {
            if ( passwordEditText.text.toString().isEmpty() ||
                 emailEditText.text.toString().isEmpty()
            ){
                makeToast("Preencha os campos!!!")
            } else {
                login()
                loadingProgress.visibility = View.VISIBLE
            }
        }
    }

    private fun bindObservers() = with(controller) {
        observe(error) {
            makeToast(it)
        }
        observe(data) {
            success(it)
        }

    }

    private fun login(){
        controller.logIn(
            UserRequest(
                emailEditText.text.toString(),
                passwordEditText.text.toString()
            )
        )
    }

    private fun makeToast(text: String) {
        loadingProgress.visibility = View.GONE
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }

    private fun success(user : User) {
        saveCredentials(user)
        startActivity(MainActivity::class.java)
    }

    private fun saveCredentials(user: User) {
        sharedRepository.saveValue(USER_ID_BUNDLE, user)
    }

    private fun<T> startActivity(activity : Class<T>){
        val intent = Intent(this, activity)
        this.startActivity(intent)
        this.finish()
    }

}