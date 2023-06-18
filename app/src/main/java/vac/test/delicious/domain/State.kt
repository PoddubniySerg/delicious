package vac.test.delicious.domain

sealed class State {
    object Loading : State()
    object Complete : State()
    data class Error(val message: String) : State()
}
