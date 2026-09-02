package com.example.taller1usuarios.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.taller1usuarios.data.model.User
import com.example.taller1usuarios.ui.components.UserAvatar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(
    users: List<User>,
    isLoading: Boolean,
    errorMessage: String?,
    onUserClick: (User) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Directorio de usuarios") })
        },
    ) { paddingValues ->
        when {
            isLoading -> LoadingContent(Modifier.padding(paddingValues))
            errorMessage != null -> ErrorContent(
                message = errorMessage,
                modifier = Modifier.padding(paddingValues),
            )
            else -> UserList(
                users = users,
                onUserClick = onUserClick,
                modifier = Modifier.padding(paddingValues),
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun UserList(
    users: List<User>,
    onUserClick: (User) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        contentPadding = PaddingValues(bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp),
        modifier = modifier.fillMaxSize(),
    ) {
        stickyHeader {
            Surface(
                color = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.List,
                        contentDescription = null,
                    )
                    Text(
                        text = "Total de usuarios: ${users.size}",
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(start = 8.dp),
                    )
                }
            }
        }

        items(users, key = User::id) { user ->
            ListItem(
                headlineContent = {
                    Text(
                        text = user.fullName,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                },
                supportingContent = {
                    Text(
                        text = user.company.name,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                },
                leadingContent = {
                    UserAvatar(user.image, user.fullName)
                },
                trailingContent = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = "Ver detalle de ${user.fullName}",
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onUserClick(user) },
            )
        }
    }
}

@Composable
private fun LoadingContent(modifier: Modifier = Modifier) {
    Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator()
            Text("Cargando usuarios...", modifier = Modifier.padding(top = 12.dp))
        }
    }
}

@Composable
private fun ErrorContent(message: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "No se pudo cargar la lista.\n$message",
            color = MaterialTheme.colorScheme.error,
        )
    }
}
