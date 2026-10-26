package com.example.estacionamiento.vistas

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun WifiConfigView(navController: NavHostController) {
    var ipAddress by remember { mutableStateOf("192.168.1.50") }
    var puerto by remember { mutableStateOf("80") }
    var estadoConexion by remember { mutableStateOf("Desconectado (Boceto)") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Conexión Wi-Fi ESP8266",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Configuración de red IoT",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = ipAddress,
            onValueChange = { ipAddress = it },
            label = { Text("Dirección IP del Módulo Wi-Fi") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = puerto,
            onValueChange = { puerto = it },
            label = { Text("Puerto") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Estado: $estadoConexion",
            fontWeight = FontWeight.Medium,
            color = if (estadoConexion.contains("Conectado")) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                estadoConexion = "¡Conectado exitosamente a $ipAddress:$puerto!"
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Conectar al Hardware")
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Regresar al Panel Principal")
        }
    }
}