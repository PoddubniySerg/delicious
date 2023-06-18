package vac.test.delicious.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import vac.test.delicious.domain.entities.CardFood

@JsonClass(generateAdapter = true)
data class CardFoodDto(
    @Json(name = "id") override val id: Long,
    @Json(name = "title") override val title: String,
    @Json(name = "description") override val description: String,
    @Json(name = "poster_url") override val posterUrl: String,
    @Json(name = "price_from") override val priceFrom: String
) : CardFood
