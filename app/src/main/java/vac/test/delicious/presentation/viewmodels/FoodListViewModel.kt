package vac.test.delicious.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import vac.test.delicious.domain.State
import vac.test.delicious.domain.repositories.Repository
import vac.test.delicious.domain.usecases.GetBannersUseCase
import vac.test.delicious.domain.usecases.GetComboUseCase
import vac.test.delicious.domain.usecases.GetDessertsUseCase
import vac.test.delicious.domain.usecases.GetDrinksUseCase
import vac.test.delicious.domain.usecases.GetPizzaUseCase
import vac.test.delicious.domain.usecases.GetTopMenuUseCase

class FoodListViewModel(
    repository: Repository,
    private val getPizzaUseCase: GetPizzaUseCase,
    private val getComboUseCase: GetComboUseCase,
    private val getDessertsUseCase: GetDessertsUseCase,
    private val getDrinksUseCase: GetDrinksUseCase,
    private val getTopMenuUseCase: GetTopMenuUseCase,
    private val getBannersUseCase: GetBannersUseCase
) : ViewModel() {

    val cardFoodFlow = repository.cardFoodFlow

    val topMenuFlow = repository.topMenuFlow

    val bannersFlow = repository.bannersFlow

    private val _stateMutableStateFlow = MutableStateFlow<State>(State.Complete)
    val stateFlow = _stateMutableStateFlow.asStateFlow()

    fun getPizza() {
        getData { getPizzaUseCase.execute() }
    }

    fun getCombo() {
        getData { getComboUseCase.execute() }
    }

    fun getDesserts() {
        getData { getDessertsUseCase.execute() }
    }

    fun getDrinks() {
        getData { getDrinksUseCase.execute() }
    }

    fun getTopMenuItems() {
        getData { getTopMenuUseCase.execute() }
    }

    fun getBanners() {
        getData { getBannersUseCase.execute() }
    }

    private fun getData(executor: suspend () -> Unit) {
        viewModelScope.launch {
            try {
                _stateMutableStateFlow.value = State.Loading
                executor.invoke()
            } catch (ex: Exception) {
                _stateMutableStateFlow.value = State.Error(ex.message.toString())
            } finally {
                _stateMutableStateFlow.value = State.Complete
            }
        }
    }
}