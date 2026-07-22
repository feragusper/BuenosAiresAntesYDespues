package com.feragusper.buenosairesantesydespues.feature.records.detail

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.feragusper.buenosairesantesydespues.core.model.HistoricalRecord
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecordDetailScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RecordDetailViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val record = uiState.record

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(record?.title ?: "") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                },
                actions = {
                    if (record != null) {
                        IconButton(onClick = { shareRecord(context, record) }) {
                            Icon(Icons.Filled.Share, contentDescription = "Compartir")
                        }
                    }
                },
            )
        },
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center,
        ) {
            when {
                uiState.isLoading -> CircularProgressIndicator()
                uiState.errorMessage != null -> Text(uiState.errorMessage!!)
                record != null -> RecordDetailContent(record)
            }
        }
    }
}

@Composable
private fun RecordDetailContent(record: HistoricalRecord) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        BeforeAfterSlider(
            beforeUrl = record.imageUrlBefore,
            afterUrl = record.imageUrlAfter,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(4f / 3f),
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            val yearNeighborhood = listOf(record.year, record.neighborhood)
                .filter { it.isNotBlank() }
                .joinToString(" - ")
            if (yearNeighborhood.isNotBlank()) {
                Text(yearNeighborhood, style = MaterialTheme.typography.titleMedium)
            }
            if (record.address.isNotBlank()) {
                Text(record.address, style = MaterialTheme.typography.bodyLarge)
            }
            if (record.description.isNotBlank()) {
                Text(record.description, style = MaterialTheme.typography.bodyMedium)
            }
            if (record.credits.isNotBlank()) {
                Text(
                    record.credits,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }

        if (record.lat != 0.0 || record.lng != 0.0) {
            val position = LatLng(record.lat, record.lng)
            val cameraPositionState = rememberCameraPositionState {
                this.position = CameraPosition.fromLatLngZoom(position, 14f)
            }
            GoogleMap(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp),
                cameraPositionState = cameraPositionState,
            ) {
                Marker(
                    state = MarkerState(position = position),
                    title = record.address,
                )
            }
        }
    }
}

private fun shareRecord(context: android.content.Context, record: HistoricalRecord) {
    val sendIntent = Intent(Intent.ACTION_SEND).apply {
        putExtra(
            Intent.EXTRA_TEXT,
            "Mira el antes y el después de ${record.address} en => ${record.shareUrl.orEmpty()}",
        )
        putExtra(Intent.EXTRA_SUBJECT, "Buenos Aires Antes y Despues - ${record.title}")
        type = "text/plain"
    }
    context.startActivity(Intent.createChooser(sendIntent, null))
}
