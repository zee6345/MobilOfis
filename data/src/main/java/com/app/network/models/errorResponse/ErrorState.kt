package com.app.network.models.errorResponse

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.app.network.helper.Converter

private const val SESSION = "SESSION_EVENTS"

class ErrorState(private val context: Context, private val error: String) {



    fun handleError() {
        try {

            val errorResponse: ErrorResponse = Converter.fromJson(error, ErrorResponse::class.java)

            if (errorResponse.code.equals(
                    "ERROR.SESSION_EXPIRE",
                    true
                ) or errorResponse.code.equals("ERROR.TOKEN_USERNAME_MISMATCH", true)
            ) {

                val intent = Intent()
                intent.action = SESSION
                intent.putExtra("data", "expire")
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent)

            } else if (errorResponse.code.equals("ERROR.TOTP_2FA_VERIFICATION_NOT_MATCH", true)){

                Toast.makeText(context, "Incorrect Google Authenticator Code", Toast.LENGTH_SHORT).show()

            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}