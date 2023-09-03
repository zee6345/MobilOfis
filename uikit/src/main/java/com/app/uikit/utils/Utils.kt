package com.app.uikit.utils

import androidx.compose.ui.graphics.Color
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

object Utils {

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

        val formattedAmount = "${result.toString()}.${decimalPart}"
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