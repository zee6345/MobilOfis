package com.app.network.helper

import android.content.Context
import android.content.SharedPreferences
import android.util.Base64
import com.app.network.models.responseModels.LoginVerifyResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Session @Inject constructor(@ApplicationContext context: Context) {
    init {
        try {
            val sharedPreferences = context.getSharedPreferences(ENC_PREFERENCE, Context.MODE_PRIVATE)

            // use the shared preferences and editor as you normally would
            editor = sharedPreferences.edit()
            savedSession = sharedPreferences
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun put(key: String?, value: Int): Boolean {
        require(!(key == null || key == "")) { WRONG_PAIR }
        editor!!.putInt(key, value)
        return editor!!.commit()
    }

    fun put(key: String?, value: Boolean): Boolean {
        require(!(key == null || key == "")) { WRONG_PAIR }
        editor!!.putBoolean(key, value)
        return editor!!.commit()
    }

    fun put(key: String?, value: String?): Boolean {
        require(!(key == null || value == null || key == "")) { WRONG_PAIR }
        editor!!.putString(key, value)
        return editor!!.commit()
    }

    fun put(key: String?, value: Long): Boolean {
        require(!(key == null || key == "")) { WRONG_PAIR }
        editor!!.putLong(key, value)
        return editor!!.commit()
    }

    fun put(key: String?, value: Boolean, sharedPreferenceName: String?): Boolean {
        require(!(key == null || key == "")) { WRONG_PAIR }
        editor!!.putBoolean(key, value)
        return editor!!.commit()
    }

//    fun toJson(obj:Any): String?{
//        return Gson().toJson(obj)
//    }
//
//    fun <T> fromJson(json: String, classOfT: Class<T>): T {
//        return Gson().fromJson(json, classOfT)
//    }

    operator fun get(key: String?): String? {
        return savedSession!!.getString(key, BLANK_STRING_KEY)
    }

    operator fun get(key: String?, defaultValue: String?): String? {
        return savedSession!!.getString(key, defaultValue)
    }

    fun getInt(key: String?): Int {
        return savedSession!!.getInt(key, 0)
    }

    fun getInt(key: String?, defaultValue: Int): Int {
        return savedSession!!.getInt(key, defaultValue)
    }

    fun getLong(key: String?): Long {
        return savedSession!!.getLong(key, 0)
    }

    fun getBoolean(key: String?): Boolean {
        return savedSession!!.getBoolean(key, false)
    }

    fun getBoolean(key: String?, defaultValue: Boolean): Boolean {
        return savedSession!!.getBoolean(key, defaultValue)
    }

    fun delete(key: String?): Boolean {
        return editor!!.remove(key).commit()
    }

    fun putNonce(key: String?, value: ByteArray?): Boolean {
        require(!(key == null || key == "")) { WRONG_PAIR }
        editor!!.putString(key, Base64.encodeToString(value, Base64.NO_WRAP))
        return editor!!.commit()
    }

    fun getNonce(key: String?): ByteArray {
        val encodedNonce = savedSession!!.getString(key, "")
        return Base64.decode(encodedNonce, Base64.NO_WRAP)
    }

    fun fetchUserDetails(session:Session): LoginVerifyResponse {
        val str = session[Keys.KEY_USER_DETAILS]
        return Converter.fromJson(str!!, LoginVerifyResponse::class.java)
    }

    companion object {
        const val BLANK_STRING_KEY = ""
        const val WRONG_PAIR = "Key-Value pair cannot be blank or null"
        private const val ENC_PREFERENCE = "ENC_PREF"
        private var INSTANCE: Session? = null
        private var editor: SharedPreferences.Editor? = null
        private var savedSession: SharedPreferences? = null

//        fun with(context: Context): Session? {
//            if (INSTANCE == null) {
////            INSTANCE = new Session(MainApp.Companion.getContext());
//                INSTANCE = Session(context)
//            }
//            return INSTANCE
//        }

    }
}