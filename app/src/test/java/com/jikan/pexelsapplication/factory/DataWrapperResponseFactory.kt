package com.jikan.pexelsapplication.factory

import com.jikan.pexelsapplication.framework.network.response.DataWrapperResponse
import com.jikan.pexelsapplication.framework.network.response.Photo
import com.jikan.pexelsapplication.framework.network.response.Src

class DataWrapperResponseFactory {

    fun create() = DataWrapperResponse(
        nextPage = "",
        page = 1,
        perPage = 40,
        photos = List(1) { photo }
    )

    private val photo = Photo(
        alt = "Free stock photo of agriculture, architecture, bright",
        avgColor = "#56543B",
        height = 6240,
        id = 15575287,
        liked = false,
        photographer = "medinegurbet",
        photographerId = 21435626,
        photographerUrl = "https://www.pexels.com/@medinegurbet-21435626",
        src = Src(
            landscape = "https://images.pexels.com/photos/15575287/pexels-photo-15575287.jpeg?auto=compress&cs=tinysrgb&fit=crop&h=627&w=1200",
            large = "https://images.pexels.com/photos/15575287/pexels-photo-15575287.jpeg?auto=compress&cs=tinysrgb&h=650&w=940",
            large2x = "https://images.pexels.com/photos/15575287/pexels-photo-15575287.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
            medium = "https://images.pexels.com/photos/15575287/pexels-photo-15575287.jpeg?auto=compress&cs=tinysrgb&h=350",
            original = "https://images.pexels.com/photos/15575287/pexels-photo-15575287.jpeg",
            portrait = "https://images.pexels.com/photos/15575287/pexels-photo-15575287.jpeg?auto=compress&cs=tinysrgb&fit=crop&h=1200&w=800",
            small = "https://images.pexels.com/photos/15575287/pexels-photo-15575287.jpeg?auto=compress&cs=tinysrgb&h=130",
            tiny = "https://images.pexels.com/photos/15575287/pexels-photo-15575287.jpeg?auto=compress&cs=tinysrgb&dpr=1&fit=crop&h=200&w=280"
        ),
        url = "https://www.pexels.com/photo/wood-summer-garden-house-15575287/",
        width = 4160
    )
}