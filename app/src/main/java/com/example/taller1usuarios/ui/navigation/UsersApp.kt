package com.example.taller1usuarios.ui.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.taller1usuarios.data.model.User
import com.example.taller1usuarios.data.remote.UsersApi
import com.example.taller1usuarios.ui.screens.UserDetailScreen
import com.example.taller1usuarios.ui.screens.UserListScreen
import kotlinx.serialization.Serializable

@Serializable
private data object UserListRoute : NavKey

@Serializable
private data class UserDetailRoute(val userId: Int) : NavKey

@Composable
fun UsersApp() {
    val backStack = rememberNavBackStack(UserListRoute)

    var users by remember { mutableStateOf<List<User>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        try {
            users = UsersApi.fetchUsers()
        } catch (error: Exception) {
            errorMessage = error.message ?: "No fue posible cargar los usuarios."
        } finally {
            isLoading = false
        }
    }

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<UserListRoute> {
                UserListScreen(
                    users = users,
                    isLoading = isLoading,
                    errorMessage = errorMessage,
                    onUserClick = { user ->
                        backStack.add(UserDetailRoute(user.id))
                    },
                )
            }

            entry<UserDetailRoute> { route ->
                val selectedUser = users.find { it.id == route.userId }

                if (selectedUser != null) {
                    UserDetailScreen(
                        user = selectedUser,
                        onBack = { backStack.removeLastOrNull() },
                    )
                } else {
                    UserNotFoundScreen(
                        onBack = { backStack.removeLastOrNull() },
                    )
                }
            }
        },
    )
}

@Composable
private fun UserNotFoundScreen(onBack: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
    ) {
        Text("No se encontró el usuario seleccionado.")
        Button(
            onClick = onBack,
            modifier = Modifier.padding(top = 16.dp),
        ) {
            Text("Volver a la lista")
        }
    }
}
