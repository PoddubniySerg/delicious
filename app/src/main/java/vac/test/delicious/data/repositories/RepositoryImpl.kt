package vac.test.delicious.data.repositories

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import vac.test.delicious.data.network.NetworkClient
import vac.test.delicious.domain.entities.CardFood
import vac.test.delicious.domain.entities.TopMenu
import vac.test.delicious.domain.repositories.Repository

class RepositoryImpl : Repository {

    private val dataApi = NetworkClient.dataApi

    private val _cardFoodChannel = Channel<List<CardFood>>()
    override val cardFoodFlow: Flow<List<CardFood>> = _cardFoodChannel.receiveAsFlow()

    private val _topMenuChannel = Channel<List<TopMenu>>()
    override val topMenuFlow: Flow<List<TopMenu>> = _topMenuChannel.receiveAsFlow()

    private val _bannersChannel = Channel<List<String>>()
    override val bannersFlow: Flow<List<String>> = _bannersChannel.receiveAsFlow()

    override suspend fun getPizza() {
        val cardsFood = dataApi.getPizza().body() ?: return
        _cardFoodChannel.send(cardsFood)
    }

    override suspend fun getCombo() {
        val cardsFood = dataApi.getCombo().body() ?: return
        _cardFoodChannel.send(cardsFood)
    }

    override suspend fun getDesserts() {
        val cardsFood = dataApi.getDesserts().body() ?: return
        _cardFoodChannel.send(cardsFood)
    }

    override suspend fun getDrinks() {
        val cardsFood = dataApi.getDrinks().body() ?: return
        _cardFoodChannel.send(cardsFood)
    }

    override suspend fun getTopMenuItems() {
        val topMenu = dataApi.getTopMenuItems().body() ?: return
        _topMenuChannel.send(topMenu)
    }

    override suspend fun getBanners() {
        val banners = dataApi.getBanners().body() ?: return
        _bannersChannel.send(banners)
    }
}