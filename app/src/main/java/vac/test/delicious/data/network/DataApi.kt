package vac.test.delicious.data.network

import retrofit2.Response
import retrofit2.http.GET
import vac.test.delicious.data.dto.CardFoodDto
import vac.test.delicious.data.dto.TopMenuDto

interface DataApi {

    @GET("/PoddubniySerg/e240b7611472faca248d0001c5e7a685/raw/e83ff147eed84fa1ff33ffb3c0abfaa1f220816f/cards_food.json")
    suspend fun getPizza(): Response<List<CardFoodDto>>

    @GET("/PoddubniySerg/83b9df323218f409ad27328f3bfef488/raw/ba6f212b2ab9854cf63d0de332ac9c9482bd919b/combo.json")
    suspend fun getCombo(): Response<List<CardFoodDto>>

    @GET("/PoddubniySerg/83b9df323218f409ad27328f3bfef488/raw/ba6f212b2ab9854cf63d0de332ac9c9482bd919b/desserts.json")
    suspend fun getDesserts(): Response<List<CardFoodDto>>

    @GET("/PoddubniySerg/83b9df323218f409ad27328f3bfef488/raw/ba6f212b2ab9854cf63d0de332ac9c9482bd919b/drinks.json")
    suspend fun getDrinks(): Response<List<CardFoodDto>>

    @GET("/PoddubniySerg/e240b7611472faca248d0001c5e7a685/raw/e83ff147eed84fa1ff33ffb3c0abfaa1f220816f/top_menu.json")
    suspend fun getTopMenuItems(): Response<List<TopMenuDto>>

    @GET("/PoddubniySerg/e240b7611472faca248d0001c5e7a685/raw/e83ff147eed84fa1ff33ffb3c0abfaa1f220816f/food_posters.json")
    suspend fun getBanners(): Response<List<String>>
}