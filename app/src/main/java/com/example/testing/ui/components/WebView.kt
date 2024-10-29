package com.example.testing.ui.components

import android.graphics.Bitmap
import android.util.Log
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.kevinnzou.web.AccompanistWebViewClient
import com.kevinnzou.web.LoadingState
import com.kevinnzou.web.rememberWebViewNavigator
import com.kevinnzou.web.rememberWebViewState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WebView(modifier: Modifier = Modifier) {
    var url by remember { mutableStateOf("https://google.com") }
    val state = rememberWebViewState(url = url)
    val navigator = rememberWebViewNavigator()
    var textFieldValue by remember(state.lastLoadedUrl) {
        mutableStateOf(state.lastLoadedUrl ?: "")

    }

    Column {
        TopAppBar(
            title = { Text("Chrome Web", color = MaterialTheme.colorScheme.onSurface) },
            navigationIcon = {
                IconButton(onClick = { navigator.navigateBack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            },
            actions = {
                IconButton(onClick = { navigator.reload() }) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = null
                    )
                }
                IconButton(onClick = { url = textFieldValue }) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null
                    )
                }

            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background,
                actionIconContentColor = MaterialTheme.colorScheme.onSurface,
                navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
            )
        )
        Row(
            modifier = modifier
                .padding(6.dp)
                .clip(MaterialTheme.shapes.medium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                modifier = modifier
                    .fillMaxWidth()
                    .height(26.dp)
                    .background(Color.White)
                    .padding(vertical = 3.dp),
                value = textFieldValue,
                onValueChange = { textFieldValue = it },
                maxLines = 1,
                singleLine = true,
                textStyle = TextStyle(textAlign = TextAlign.Center)
            )
            if (state.errorsForCurrentRequest.isNotEmpty()) {
                Icon(
                    modifier = modifier.weight(1f),
                    imageVector = Icons.Default.Warning,
                    contentDescription = null,
                    tint = Color.Red
                )
            }
        }

        val loadingState = state.loadingState
        if (loadingState is LoadingState.Loading) {
            LinearProgressIndicator(
                progress = { loadingState.progress },
                modifier = Modifier.fillMaxWidth(),
                color = Color.Green
            )
        }

        val webView = remember {
            object : AccompanistWebViewClient() {
                override fun onPageStarted(view: WebView, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    Log.d("WebView", "Page started loading for $url")
                }
            }
        }

        com.kevinnzou.web.WebView(
            state = state,
            modifier = Modifier.weight(1f),
            navigator = navigator,
            onCreated = { webView ->
                webView.settings.javaScriptEnabled = true
            },
            client = webView
        )
    }
}

@Composable
fun ClassicWebView(modifier: Modifier = Modifier) {
    var backEnable by remember { mutableStateOf(false) }
    var webView: WebView? = null

    AndroidView(
        modifier = modifier,
        factory = { context ->
            WebView(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                webViewClient = object : WebViewClient() {
                    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                        backEnable = view?.canGoBack() ?: false
                    }
                }
                settings.javaScriptEnabled = true
                loadUrl("https://google.com")
            }
        },
        update = { webView = it }
    )
    BackHandler(enabled = backEnable) {
        webView?.goBack()
    }
}