package com.jikan.pexelsapplication.framework.downloader

interface Downloader {
    fun downloadFile(url: String, description: String): Long
}