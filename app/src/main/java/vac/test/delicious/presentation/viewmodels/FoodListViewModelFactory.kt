package vac.test.delicious.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import vac.test.delicious.data.repositories.RepositoryImpl
import vac.test.delicious.domain.usecases.GetBannersUseCase
import vac.test.delicious.domain.usecases.GetComboUseCase
import vac.test.delicious.domain.usecases.GetDessertsUseCase
import vac.test.delicious.domain.usecases.GetDrinksUseCase
import vac.test.delicious.domain.usecases.GetPizzaUseCase
import vac.test.delicious.domain.usecases.GetTopMenuUseCase

class FoodListViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (!modelClass.isAssignableFrom(FoodListViewModel::class.java)) {
            throw IllegalArgumentException("Unknown class name")
        }
        val repository = RepositoryImpl()
        val viewModel = FoodListViewModel(
            repository,
            GetPizzaUseCase(repository),
            GetComboUseCase(repository),
            GetDessertsUseCase(repository),
            GetDrinksUseCase(repository),
            GetTopMenuUseCase(repository),
            GetBannersUseCase(repository)
        )
        return viewModel as T
    }
}