package com.app.uikit.utils

import androidx.compose.ui.graphics.Color
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

object Utils {

    fun formattedDate(inputDateTimeString: String): String? {
        // Parse the input string using the given format
        val inputFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm", Locale.ENGLISH)
        val dateTime = LocalDateTime.parse(inputDateTimeString, inputFormatter)

        // Format the date in the desired format
        val outputDateFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ENGLISH)
        val formattedDate = dateTime.format(outputDateFormatter)

        // Extract the time separately
        val outputTimeFormatter = DateTimeFormatter.ofPattern("HH:mm", Locale.ENGLISH)
        val formattedTime = dateTime.format(outputTimeFormatter)

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