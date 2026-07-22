package com.feragusper.buenosairesantesydespues.about

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toUri
import com.feragusper.buenosairesantesydespues.BuildConfig

private data class AboutLink(val title: String, val subtitle: String, val url: String)

private val links = listOf(
    AboutLink("Calificanos!", "Play Store", "https://play.google.com/store/apps/details?id=com.feragusper.buenosairesantesydespues"),
    AboutLink("Visitá nuestra Web", "bsasantesydespues.com.ar", "http://www.bsasantesydespues.com.ar"),
    AboutLink("Facebook", "facebook.com/bsasantesydespues", "https://www.facebook.com/bsasantesydespues"),
    AboutLink("Fork en GitHub", "github.com/feragusper", "https://github.com/feragusper/BuenosAiresAntesYDespues"),
    AboutLink("Releases", "Changelog", "https://github.com/feragusper/BuenosAiresAntesYDespues/releases"),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text("Acerca de") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                },
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState()),
        ) {
            links.forEach { link ->
                ListItem(
                    headlineContent = { Text(link.title) },
                    supportingContent = { Text(link.subtitle) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            context.startActivity(Intent(Intent.ACTION_VIEW, link.url.toUri()))
                        },
                )
                HorizontalDivider()
            }
            ListItem(
                headlineContent = { Text("Versión") },
                supportingContent = { Text("${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE})") },
            )
        }
    }
}
