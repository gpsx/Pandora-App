package x.pandoraapp.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import x.pandoraapp.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide();
        setContentView(R.layout.activity_login)
        enterButton.setOnClickListener {
            startActivity(MainActivity::class.java)
        }
    }
    private fun<T> startActivity(activity : Class<T>){
        val intent = Intent(this, activity)
        this.startActivity(intent)
        this.finish()
    }
}