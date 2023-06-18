package vac.test.delicious.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import vac.test.delicious.domain.entities.TopMenu

@JsonClass(generateAdapter = true)
data class TopMenuDto(
    @Json(name = "id") override val id: Long,
    @Json(name = "title") override val title: String
) : TopMenu
