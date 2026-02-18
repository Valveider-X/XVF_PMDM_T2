package com.example.XVF_PMDM_T2.ui.screens

import com.example.XVF_PMDM_T2.data.model.ItemDto
import com.example.XVF_PMDM_T2.presentation.viewmodel.MainViewModel
import com.example.XVF_PMDM_T2.presentation.viewmodel.UiState

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductosScreen(vm: MainViewModel) {
    val state = vm.state

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Listado API") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->

        Box(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
            when (state) {
                is UiState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }

                is UiState.Error -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("Error al cargar datos: ${state.msg}")
                        Button(onClick = { vm.loadProducts() }, modifier = Modifier.padding(top = 16.dp)) {
                            Text("Reintentar")
                        }
                    }
                }

                is UiState.Success -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(state.data) { producto ->
                            ProductoItemSimple(producto)
                            HorizontalDivider()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ProductoItemSimple(producto: ItemDto) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = producto.image,
            contentDescription = null,
            modifier = Modifier
                .size(80.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = producto.name,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = producto.category,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Precio: ${producto.price}€",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}