package com.example.estacionamiento.vistas

import androidx.compose.runtime.mutableStateMapOf

// Repositorio global temporal para guardar los usuarios registrados
object AuthRepository {
    val usuarios = mutableStateMapOf(
        "usuario@test.cl" to "123456",
        "admin@correo.com" to "admin123"
    )
}