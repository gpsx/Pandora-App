package x.pandoraapp.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import x.pandoraapp.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()
        Handler().postDelayed({
            startActivity(LoginActivity::class.java)
        }, 2000)
    }
    private fun<T> startActivity(activity : Class<T>){
        val intent = Intent(this@SplashActivity, activity)
        this@SplashActivity.startActivity(intent)
        this@SplashActivity.finish()
    }
}