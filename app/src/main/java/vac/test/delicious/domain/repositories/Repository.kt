package vac.test.delicious.domain.repositories

import kotlinx.coroutines.flow.Flow
import vac.test.delicious.domain.entities.CardFood
import vac.test.delicious.domain.entities.TopMenu

interface Repository {

    val cardFoodFlow: Flow<List<CardFood>>

    val topMenuFlow: Flow<List<TopMenu>>

    val bannersFlow: Flow<List<String>>

    suspend fun getPizza()

    suspend fun getCombo()

    suspend fun getDesserts()

    suspend fun getDrinks()

    suspend fun getTopMenuItems()

    suspend fun getBanners()
}