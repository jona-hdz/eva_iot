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
fun Login(navController: NavHostController) {
    var correo by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var mensajeError by remember { mutableStateOf("") }

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
                text = "Acceso a Estacionamiento \n\n Inicio de Sesión",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = correo,
                onValueChange = { correo = it; mensajeError = "" },
                label = { Text("Correo Electrónico (.cl o .com)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it; mensajeError = "" },
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            if (mensajeError.isNotBlank()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(mensajeError, color = MaterialTheme.colorScheme.error, textAlign = TextAlign.Center)
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (correo.isBlank() || password.isBlank()) {
                        mensajeError = "Complete todos los campos"
                        return@Button
                    }

                    // Validación súper fácil: que tenga '@' y termine en .cl o .com
                    val esCorreoValido = correo.contains("@") && (correo.endsWith(".cl") || correo.endsWith(".com"))
                    if (!esCorreoValido) {
                        mensajeError = "El correo debe tener '@' y terminar en .cl o .com"
                        return@Button
                    }

                    // Validar si el usuario está registrado en AuthRepository
                    if (AuthRepository.usuarios[correo] == password) {
                        navController.navigate("estacionamiento") {
                            popUpTo("login") { inclusive = true }
                        }
                    } else {
                        mensajeError = "Correo no registrado o contraseña incorrecta"
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Ingresar")
            }

            TextButton(
                onClick = { navController.navigate("registrar") }
            ) {
                Text("¿No tienes cuenta?, regístrate")
            }
        }
    }
}