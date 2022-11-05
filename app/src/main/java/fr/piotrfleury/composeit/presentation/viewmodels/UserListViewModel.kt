package fr.piotrfleury.composeit.presentation.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.piotrfleury.composeit.domain.entities.User
import fr.piotrfleury.composeit.presentation.usecases.FetchNewUsers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserListViewModel(private val fetchNewUsers: FetchNewUsers) : ViewModel() {
    private val _userList = mutableStateListOf<User>()
    val userList: List<User>
        get() = _userList

    fun getUsers() {
        viewModelScope
            .launch {
            withContext(Dispatchers.IO) {
                _userList.addAll(fetchNewUsers())
            }
        }
    }

}