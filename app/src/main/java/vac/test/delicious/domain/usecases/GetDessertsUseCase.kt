package vac.test.delicious.domain.usecases

import vac.test.delicious.domain.repositories.Repository

class GetDessertsUseCase(private val repository: Repository) {

    suspend fun execute() {
        repository.getDesserts()
    }
}