package x.pandoraapp.views

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.preference.PreferenceManager.getDefaultSharedPreferences
import android.view.View
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

        val pref = getDefaultSharedPreferences(this);
        pref.apply {
            val username = getString("USERNAME", "")
            val password = getString("PASSWORD", "")
            emailEditText.setText(username)
            passwordEditText.setText(password)
        }
    }
    private fun<T> startActivity(activity : Class<T>){
        val intent = Intent(this, activity)
        this.startActivity(intent)
        this.finish()
    }


    fun login(componente: View){
        val pref = getDefaultSharedPreferences(this);

        val editor = pref.edit();
        editor
            .putString("USERNAME", emailEditText.text.toString())
            .putString("PASSWORD", passwordEditText.text.toString())
            .apply()
    }

}