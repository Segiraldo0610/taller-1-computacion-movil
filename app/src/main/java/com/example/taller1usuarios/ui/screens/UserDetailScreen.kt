package com.example.taller1usuarios.ui.screens

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.taller1usuarios.data.model.User
import com.example.taller1usuarios.ui.components.UserAvatar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailScreen(
    user: User,
    onBack: () -> Unit,
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle del usuario") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver",
                        )
                    }
                },
            )
        },
    ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
        ) {
            UserAvatar(
                imageUrl = user.image,
                fullName = user.fullName,
                size = 120.dp,
            )
            Text(
                text = user.fullName,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 12.dp),
            )
            Text(
                text = user.company.name,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Spacer(Modifier.height(20.dp))

            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    DetailRow("Nombre", user.firstName)
                    DetailRow("Apellido", user.lastName)
                    DetailRow("Empresa", user.company.name)
                    TextButton(
                        onClick = {
                            val intent = Intent(
                                Intent.ACTION_DIAL,
                                Uri.parse("tel:${user.phone}"),
                            )
                            try {
                                context.startActivity(intent)
                            } catch (_: ActivityNotFoundException) {
                                Toast.makeText(
                                    context,
                                    "No hay una aplicación de teléfono disponible.",
                                    Toast.LENGTH_LONG,
                                ).show()
                            }
                        },
                    ) {
                        Text("Teléfono: ${user.phone}")
                    }
                    DetailRow("Correo", user.email)
                    DetailRow("Edad", "${user.age} años")
                    DetailRow("Género", user.gender)
                    DetailRow("Altura", formatMeasure(user.height, "cm"))
                    DetailRow("Peso", formatMeasure(user.weight, "kg"))
                    DetailRow("Universidad", user.university)
                }
            }
        }
    }
}

@Composable
private fun DetailRow(label: String, value: String) {
    Text(
        text = "$label: $value",
        modifier = Modifier.padding(vertical = 5.dp),
    )
}

private fun formatMeasure(value: Double, unit: String): String =
    String.format(Locale.getDefault(), "%.1f %s", value, unit)
