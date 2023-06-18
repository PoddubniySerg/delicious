package vac.test.delicious.domain.usecases

import vac.test.delicious.domain.repositories.Repository

class GetDrinksUseCase(private val repository: Repository) {

    suspend fun execute() {
        repository.getDrinks()
    }
}