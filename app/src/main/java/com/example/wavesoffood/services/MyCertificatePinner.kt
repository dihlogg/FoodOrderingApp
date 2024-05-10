package com.example.wavesoffood.services

import okhttp3.CertificatePinner
import okhttp3.OkHttpClient

class MyCertificatePinner {
    companion object {
        private const val HOSTNAME = "192.168.1.27" // Replace with your server's hostname
        private val PINS = listOf(
            "sha256/your_public_key_hash1",
            "sha256/your_public_key_hash2"
        )

        fun createOkHttpClient(): OkHttpClient {
            val certificatePinner = CertificatePinner.Builder()
                .add(HOSTNAME, *PINS.toTypedArray())
                .build()

            return OkHttpClient.Builder()
                .certificatePinner(certificatePinner)
                .build()
        }
    }
}