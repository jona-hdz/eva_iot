package com.example.estacionamiento.vistas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

data class EstacionamientoItem(
    val id: String,
    val ubicacion: String,
    var ocupado: Boolean,
    var reservadoPorUsuario: Boolean
)

@Composable
fun EstacionamientoView(navController: NavHostController) {
    // Lista de estacionamientos
    var listaEspacios by remember {
        mutableStateOf(
            listOf(
                EstacionamientoItem("E1", "Patio", ocupado = false, reservadoPorUsuario = false),
                EstacionamientoItem("E2", "Patio", ocupado = false, reservadoPorUsuario = false),
                EstacionamientoItem("E3", "Subterráneo P1", ocupado = false, reservadoPorUsuario = false),
                EstacionamientoItem("E4", "Subterráneo P1", ocupado = true, reservadoPorUsuario = false),
                EstacionamientoItem("E5", "Subterráneo P2", ocupado = false, reservadoPorUsuario = false),
                EstacionamientoItem("E6", "Subterráneo P2", ocupado = false, reservadoPorUsuario = false)
            )
        )
    }

    var estadoBarreraEntrada by remember { mutableStateOf("Cerrada") }
    var estadoBarreraSalida by remember { mutableStateOf("Cerrada") }

    val espacioOcupadoActual = listaEspacios.find { it.reservadoPorUsuario }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Cabecera superior
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = "Panel Estacionamiento", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text(text = "Zonas: Patio y Subterráneos", fontSize = 12.sp, color = Color.Gray)
            }
            Button(onClick = {
                navController.navigate("login") { popUpTo("login") { inclusive = true } }
            }) {
                Text("Salir")
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedButton(
            onClick = { navController.navigate("wifi_config") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Configurar Conexión Wi-Fi", fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Control de Barreras
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(text = "Control de Barreras", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Entrada: $estadoBarreraEntrada", fontSize = 12.sp)
                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        Button(
                            onClick = { estadoBarreraEntrada = "Abierta" },
                            modifier = Modifier.height(30.dp),
                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp)
                        ) {
                            Text("Abrir", fontSize = 11.sp)
                        }
                        OutlinedButton(
                            onClick = { estadoBarreraEntrada = "Cerrada" },
                            modifier = Modifier.height(30.dp),
                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp)
                        ) {
                            Text("Cerrar", fontSize = 11.sp)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Salida: $estadoBarreraSalida", fontSize = 12.sp)
                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        Button(
                            onClick = { estadoBarreraSalida = "Abierta" },
                            modifier = Modifier.height(30.dp),
                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp)
                        ) {
                            Text("Abrir", fontSize = 11.sp)
                        }
                        OutlinedButton(
                            onClick = { estadoBarreraSalida = "Cerrada" },
                            modifier = Modifier.height(30.dp),
                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp)
                        ) {
                            Text("Cerrar", fontSize = 11.sp)
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        // Botones de acceso
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            OutlinedButton(
                onClick = { navController.navigate("alertas") },
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text("Alertas", fontSize = 11.sp)
            }
            OutlinedButton(
                onClick = { navController.navigate("historial") },
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text("Historial", fontSize = 11.sp)
            }
            OutlinedButton(
                onClick = { navController.navigate("perfil") },
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text("Perfil", fontSize = 11.sp)
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = if (espacioOcupadoActual != null) Color(0xFFF8D7DA) else Color(0xFFD1E7DD)
            )
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                Text(
                    text = if (espacioOcupadoActual != null)
                        "Estacionamiento Activo: ${espacioOcupadoActual.id} (${espacioOcupadoActual.ubicacion})"
                    else
                        "Estado: Sin Estacionamiento seleccionado",
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "Seleccione un Estacionamiento:", fontWeight = FontWeight.Medium, fontSize = 13.sp)

        Spacer(modifier = Modifier.height(8.dp))

        // Cuadrícula de Estacionamiento
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(listaEspacios) { espacio ->
                val esMio = espacio.reservadoPorUsuario
                val ocupadoPorOtro = espacio.ocupado && !espacio.reservadoPorUsuario

                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = when {
                            esMio -> Color(0xFFCFF4FC)
                            ocupadoPorOtro -> Color(0xFFF8D7DA)
                            else -> Color(0xFFE2E3E5)
                        }
                    ),
                    modifier = Modifier.fillMaxWidth().height(125.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize().padding(10.dp),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Estacionamiento ${espacio.id}", fontWeight = FontWeight.Bold, fontSize = 15.sp)

                        // Muestra la ubicación específica en la tarjeta
                        Text(text = espacio.ubicacion, fontSize = 11.sp, color = Color.DarkGray)

                        Text(
                            text = if (esMio) "Tu uso activo" else if (ocupadoPorOtro) "Ocupado" else "Disponible",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                        Button(
                            onClick = {
                                listaEspacios = listaEspacios.map { item ->
                                    if (item.id == espacio.id) {
                                        if (esMio) {
                                            item.copy(ocupado = false, reservadoPorUsuario = false)
                                        } else if (!item.ocupado && espacioOcupadoActual == null) {
                                            item.copy(ocupado = true, reservadoPorUsuario = true)
                                        } else {
                                            item
                                        }
                                    } else {
                                        item
                                    }
                                }
                            },
                            enabled = esMio || (!espacio.ocupado && espacioOcupadoActual == null),
                            modifier = Modifier.fillMaxWidth().height(32.dp),
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Text(text = if (esMio) "Desocupar" else "Seleccionar", fontSize = 11.sp)
                        }
                    }
                }
            }
        }
    }
}