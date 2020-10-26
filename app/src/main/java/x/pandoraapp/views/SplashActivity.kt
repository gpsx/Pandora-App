package x.pandoraapp.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import x.pandoraapp.R
import x.pandoraapp.constraints.USER_ID_BUNDLE
import x.pandoraapp.models.User
import x.pandoraapp.repository.SharedPreferencesRepositoryImplementation

class SplashActivity : AppCompatActivity() {
    private val pref by lazy { SharedPreferencesRepositoryImplementation<User>(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()
        val userData = pref.getValue(USER_ID_BUNDLE, User::class.java)
        Handler().postDelayed({
            userData?.email?.let {
                Log.d("user data", "$userData")
                startActivity(MainActivity::class.java)
            } ?: startActivity(LoginActivity::class.java)
        }, 2000)
    }
    private fun<T> startActivity(activity : Class<T>){
        val intent = Intent(this@SplashActivity, activity)
        this@SplashActivity.startActivity(intent)
        this@SplashActivity.finish()
    }
}