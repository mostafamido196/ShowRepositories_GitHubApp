package com.example.odcgithubrepoapp.presentation.utils

import java.net.InetAddress

object Utils {

    fun isInternetAvailable(): Boolean {
        return try {
            val ipAddr: InetAddress = InetAddress.getByName("google.com")
            !ipAddr.equals("")
        } catch (e: Exception) {
            false
        }
    }
}