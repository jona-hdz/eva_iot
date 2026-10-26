package com.example.estacionamiento.vistas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun Registrar(navController: NavHostController) {
    var correo by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }
    var esError by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Registro de Usuario",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = correo,
                onValueChange = { correo = it; mensaje = "" },
                label = { Text("Correo Electrónico (.cl o .com)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it; mensaje = "" },
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            if (mensaje.isNotBlank()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    mensaje,
                    color = if (esError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (correo.isBlank() || password.isBlank()) {
                        mensaje = "Complete todos los campos"
                        esError = true
                        return@Button
                    }

                    val esCorreoValido = correo.contains("@") && (correo.endsWith(".cl") || correo.endsWith(".com"))
                    if (!esCorreoValido) {
                        mensaje = "El correo debe tener '@' y terminar en .cl o .com"
                        esError = true
                        return@Button
                    }

                    if (AuthRepository.usuarios.containsKey(correo)) {
                        mensaje = "Este correo ya está registrado"
                        esError = true
                        return@Button
                    }

                    // Guardar en el repositorio global
                    AuthRepository.usuarios[correo] = password
                    esError = false
                    mensaje = "¡Registrado con éxito! Ya puedes iniciar sesión."
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Registrar")
            }

            TextButton(
                onClick = { navController.popBackStack() }
            ) {
                Text("Volver al Login")
            }
        }
    }
}