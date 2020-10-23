package x.pandoraapp.repository

import android.content.Context
import com.google.gson.Gson

interface SharedPreferencesRepository<T> {
    fun saveValue(key: String, value: T)
    fun getValue(key: String, type: Class<T>): T?
    fun removeValue(key: String): Boolean
}

class SharedPreferencesRepositoryImplementation<T>(private val context: Context) : SharedPreferencesRepository<T> {

    private val pref by lazy {
        context.getSharedPreferences("SHARED_FOLDER", Context.MODE_PRIVATE)
    }

    override fun saveValue(key: String, value: T) = run {
        pref.edit().putString(key, Gson().toJson(value)).apply()
    }

    override fun getValue(key: String, type: Class<T>): T? = Gson().fromJson(pref.getString(key, "{}"), type)

    override fun removeValue(key: String) = pref.edit().remove(key).commit()

}
