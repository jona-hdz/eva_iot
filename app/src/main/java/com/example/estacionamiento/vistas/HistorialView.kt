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

data class EventoItem(
    val evento: String,
    val detalle: String,
    val hora: String
)

@Composable
fun HistorialView(navController: NavHostController) {
    val listaHistorial = remember {
        listOf(
            EventoItem("Estacionamiento E1 Seleccionado", "Reserva realizada por la app móvil", "18:15"),
            EventoItem("Barrera Entrada Abierta", "Orden manual enviada desde el panel", "18:10"),
            EventoItem("Barrera Salida Cerrada", "Barrera retornada a posición inicial", "16:50")
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Historial de Actividad", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text("Registro de eventos y sensores", fontSize = 12.sp, color = Color.Gray)

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(listaHistorial) { item ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = item.evento, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                            Text(text = item.hora, fontSize = 11.sp, color = Color.Gray)
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = item.detalle, fontSize = 12.sp, color = Color.DarkGray)
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