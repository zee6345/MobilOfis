package com.app.uikit.utils

import android.content.Context
import android.content.res.Resources
import android.os.Environment
import android.util.Base64
import android.util.Log
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.io.File
import java.io.IOException
import java.io.InputStreamReader
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale


object Utils {

    suspend fun downloadPDF(
        base64PDF: String,
        fileName: String,
        message: (String) -> Unit
    ): Boolean {
        if (base64PDF.isEmpty() || fileName.isEmpty()) {
            message("Invalid file type")
            return false // Invalid parameters
        }

        return try {
            // Decode Base64 to binary
            val pdfData = Base64.decode(base64PDF, Base64.DEFAULT)

            // Check if external storage is writable
            val state = Environment.getExternalStorageState()
            if (state != Environment.MEDIA_MOUNTED) {
                return false // External storage is not available
            }

            // Get the external storage directory
            val storageDir =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)

            // Create a file for the PDF
            val pdfFile = File(storageDir, fileName)

            // Check if the file already exists
            if (pdfFile.exists()) {
                message("File already exists")
                return false // File already exists
            }

            // Write the binary PDF data to the file
            withContext(Dispatchers.IO) {
                pdfFile.outputStream().use { fos ->
                    fos.write(pdfData)
                }
            }

            message("File saved to ${pdfFile.absolutePath}")
            true
        } catch (e: IOException) {
            Log.e("DownloadPDFCoroutine", "Error downloading PDF", e)
            message("Error downloading PDF")
            false // Error occurred
        }
    }

    private var currencyHashMap: Map<String, Map<String, Any>>? = null

    fun loadCurrencyData(context: Context) {
        val resources: Resources = context.resources

        // Get the resource ID of your JSON file (assuming it's named "currencies.json")
        val resourceId = resources.getIdentifier("currencies", "raw", context.packageName)

        // Check if the currencyHashMap is null or needs to be refreshed
        if (currencyHashMap == null) {
            // Read the JSON file
            val inputStream = resources.openRawResource(resourceId)
            val inputStreamReader = InputStreamReader(inputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            val stringBuilder = StringBuilder()
            var line: String?
            try {
                while (bufferedReader.readLine().also { line = it } != null) {
                    stringBuilder.append(line)
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }

            val jsonContent = stringBuilder.toString().trimIndent()

            val jsonObject = JSONObject(jsonContent)
            currencyHashMap = mutableMapOf()

            for (currencyCode in jsonObject.keys()) {
                val currencyInfo = jsonObjectToMap(jsonObject.getJSONObject(currencyCode))
                (currencyHashMap as MutableMap<String, Map<String, Any>>)[currencyCode] =
                    currencyInfo
            }
        }
    }

    fun formatCurrency(currency: String): String {
        val currencyInfo = currencyHashMap?.get(currency)
        return currencyInfo?.get("symbolNative").toString()
    }

    private fun jsonObjectToMap(jsonObject: JSONObject): Map<String, Any> {
        val map = mutableMapOf<String, Any>()

        jsonObject.keys().forEach { key ->
            val value = jsonObject[key]
            if (value is JSONObject) {
                map[key] = jsonObjectToMap(value)
            } else {
                map[key] = value
            }
        }

        return map
    }

    fun formatAmount(amount: Double): String {
        return "%.2f".format(amount)
    }

//    fun formatCurrency(currency: String, context: Context): String {
//
//        val resources: Resources = context.resources
//
//        // Get the resource ID of your JSON file (assuming it's named "my_json_file.json")
//        val resourceId = resources.getIdentifier("currencies", "raw", context.packageName)
//
//        // Read the JSON file
//        val inputStream = resources.openRawResource(resourceId)
//        val inputStreamReader = InputStreamReader(inputStream)
//        val bufferedReader = BufferedReader(inputStreamReader)
//        val stringBuilder = java.lang.StringBuilder()
//        var line: String?
//        try {
//            while (bufferedReader.readLine().also { line = it } != null) {
//                stringBuilder.append(line)
//            }
//        } catch (e: IOException) {
//            e.printStackTrace()
//        }
//
//        val jsonContent = stringBuilder.toString().trimIndent()
//
//        val jsonObject = JSONObject(jsonContent)
//        val currencyHashMap = mutableMapOf<String, Map<String, Any>>()
//
//        for (currencyCode in jsonObject.keys()) {
//            val currencyInfo = jsonObjectToMap(jsonObject.getJSONObject(currencyCode))
//            currencyHashMap[currencyCode] = currencyInfo
//        }
//
//        // Now you can access currency information using currency codes like this:
//        val currencyInfo = currencyHashMap[currency]
//
//        return currencyInfo?.get("symbol").toString()
//
//    }

    fun formatCardNumber(input: String): String {
        val formatted = StringBuilder()
        for (i in input.indices) {
            if (i > 0 && i % 4 == 0) {
                formatted.append(" ") // Add a space every 4 characters
            }
            formatted.append(input[i])
        }
        return formatted.toString()
    }


    fun formatAmountWithSpaces(amount: Double): String {

        val isNegative = amount < 0
        val absoluteAmount = if (isNegative) -amount else amount
        val amountString = String.format("%.2f", absoluteAmount) // Format with two decimal places
        val parts = amountString.split('.')
        val integerPart = parts[0]
        val decimalPart = parts.getOrElse(1) { "00" }

        val length = integerPart.length
        val result = StringBuilder()

        for (i in 0 until length) {
            val digit = integerPart[i]

            if (i > 0 && (length - i) % 3 == 0) {
                result.append(" ")
            }

            result.append(digit)
        }

        val formattedAmount = "$result.${decimalPart}"

        return if (isNegative) "-$formattedAmount" else formattedAmount
    }

    fun convertToHourMinute(inputTime: String): String {
        try {
            val inputFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
            val outputFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
            val time = inputFormat.parse(inputTime)
            return outputFormat.format(time)
        } catch (e: Exception) {
            // Handle parsing or formatting errors here
            return "Invalid Time"
        }
    }

    fun formatInputDate(inputDate: String): String {
        try {
            val inputDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val outputDateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
            val date = inputDateFormat.parse(inputDate)
            return outputDateFormat.format(date)
        } catch (e: Exception) {
            // Handle parsing or formatting errors here
            return "Invalid Date"
        }
    }

    fun formattedDate(inputDateTimeString: String): String? {
        // Parse the input string using the given format
        val inputFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm", Locale.ENGLISH)
        val dateTime = LocalDateTime.parse(inputDateTimeString, inputFormatter)

        // Format the date in the desired format
        val outputDateFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ENGLISH)
        val formattedDate = dateTime.format(outputDateFormatter)

        // Extract the time separately
//        val outputTimeFormatter = DateTimeFormatter.ofPattern("HH:mm", Locale.ENGLISH)
//        val formattedTime = dateTime.format(outputTimeFormatter)

        return formattedDate
    }

    fun extractDate(inputDateTimeString: String): String? {
        // Parse the input string using the given format
        val inputFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm", Locale.ENGLISH)
        val dateTime = LocalDateTime.parse(inputDateTimeString, inputFormatter)

        // Format the date in the desired format
        val outputDateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH)
        val formattedDate = dateTime.format(outputDateFormatter)

        // Extract the time separately
//        val outputTimeFormatter = DateTimeFormatter.ofPattern("HH:mm", Locale.ENGLISH)
//        val formattedTime = dateTime.format(outputTimeFormatter)

        return formattedDate
    }

    fun formattedTime(inputDateTimeString: String): String? {
        // Parse the input string using the given format
        val inputFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm", Locale.ENGLISH)
        val dateTime = LocalDateTime.parse(inputDateTimeString, inputFormatter)

        // Format the date in the desired format
        val outputDateFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ENGLISH)
        val formattedDate = dateTime.format(outputDateFormatter)

        // Extract the time separately
        val outputTimeFormatter = DateTimeFormatter.ofPattern("HH:mm", Locale.ENGLISH)
        val formattedTime = dateTime.format(outputTimeFormatter)

        return formattedTime
    }

    data class StatusInfo(
        val status: String,
        val color: Color
    )

    fun headerStatus(itemStatus: String): StatusInfo {
        var status = ""
        var color = Color(0xff268ED9)

        when (itemStatus) {
            "PENDING_SIGNER" -> {
                status = "For signing"
                color = Color(0xff268ED9)
            }

            "CLOSED" -> {
                status = "Executed"
                color = Color(0xff26D978)
            }

            "PENDING_ALL" -> {
                status = "Sign and confirmation"
                color = Color(0xFFC74375)
            }

            "BANK_SUCCESS" -> {
                status = "Sent to the bank"
                color = Color(0xFFF48A1D)
            }

            "BANK_ERROR" -> {
                status = "Not processed"
                color = Color(0xff2CCAD3)
            }

            "DELETED" -> {
                status = "Deleted"
                color = Color(0xFFE91E63)
            }

            "BANK_REJECTED" -> {
                status = "Rejected"
                color = Color(0xFFE91E63)
            }

            "PENDING_APPROVER" -> {
                status = "For confirmation"
                color = Color(0xFFFF5722)
            }

            "EXPIRED" -> {
                status = "Expired"
                color = Color(0xFFF80658)
            }

            "SEND_TO_BANK" -> {
                status = "In process"
                color = Color(0xFFCDDC39)
            }
        }

        return StatusInfo(status, color)
    }
}