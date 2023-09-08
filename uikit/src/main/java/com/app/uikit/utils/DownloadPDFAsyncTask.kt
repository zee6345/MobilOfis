package com.app.uikit.utils

import android.os.AsyncTask
import android.os.Environment
import android.util.Base64
import android.util.Log
import android.widget.Toast
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class DownloadPDFAsyncTask : AsyncTask<String?, Void?, Boolean>() {

    private val storageDir: File = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)

    override fun onPostExecute(success: Boolean) {
        if (success) {
            // PDF downloaded successfully
            // You can open the PDF file here or show a success message


        } else {
            // Handle the case where the download failed
            // You can show an error message to the user
        }
    }

    override fun doInBackground(vararg params: String?): Boolean {
        if (params.size < 2) {
            return false // Invalid parameters
        }
        val base64PDF = params[0]
        val fileName = params[1]
        return try {
            // Decode Base64 to binary
            val pdfData = Base64.decode(base64PDF, Base64.DEFAULT)

            // Check if external storage is writable
            val state = Environment.getExternalStorageState()
            if (Environment.MEDIA_MOUNTED != state) {
                return false // External storage is not available
            }

            // Get the external storage directory


            // Create a file for the PDF
            val pdfFile = File(storageDir, fileName)
            FileOutputStream(pdfFile).use { fos -> fos.write(pdfData) }
            true // File downloaded successfully
        } catch (e: IOException) {
            Log.e("DownloadPDFAsyncTask", "Error downloading PDF", e)
            false // Error occurred
        }
    }
}
