package com.app.network.models.errorResponse

import android.content.Context
import android.content.Intent
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.app.network.helper.Converter

private const val SESSION = "SESSION_EVENTS"

class ErrorState(private val context: Context, error: String) {

    private val errorResponse: ErrorResponse = Converter.fromJson(error, ErrorResponse::class.java)

    fun handleError() {
        try {

            if (errorResponse.code.equals(
                    "ERROR.SESSION_EXPIRE",
                    true
                ) or errorResponse.code.equals("ERROR.TOKEN_USERNAME_MISMATCH", true)
            ) {

                val intent = Intent()
                intent.action = SESSION
                intent.putExtra("data", "expire")
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent)

            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}