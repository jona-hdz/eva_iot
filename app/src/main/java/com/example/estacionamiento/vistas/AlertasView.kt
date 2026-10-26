package com.example.estacionamiento.vistas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

data class AlertaItem(
    val titulo: String,
    val descripcion: String,
    val hora: String,
    val esCritica: Boolean
)

@Composable
fun AlertasView(navController: NavHostController) {
    val listaAlertas = remember {
        listOf(
            AlertaItem("Obstáculo detectado", "Sensor IR detectó un objeto cerca de la barrera de entrada.", "18:20", true),
            AlertaItem("Tiempo límite", "Has superado las 2 horas de uso en el Estacionamiento E1.", "17:45", false),
            AlertaItem("Error de conexión ESP8266", "Se perdió brevemente la señal Wi-Fi con el Arduino.", "15:10", true)
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Alertas del Sistema IoT", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text("Monitoreo de seguridad y sensores", fontSize = 12.sp, color = Color.Gray)

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(listaAlertas) { alerta ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = if (alerta.esCritica) Color(0xFFF8D7DA) else Color(0xFFFFF3CD)
                    )
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = alerta.titulo,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = if (alerta.esCritica) Color(0xFF842029) else Color(0xFF664D03)
                            )
                            Text(text = alerta.hora, fontSize = 11.sp, color = Color.Gray)
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = alerta.descripcion, fontSize = 12.sp, color = Color.DarkGray)
                    }
                }
            }
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