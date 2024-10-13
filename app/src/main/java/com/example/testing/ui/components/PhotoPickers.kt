package com.example.testing.ui.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.testing.R

@Composable
fun PhotoPicker1(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var imageUri: Any? by remember { mutableStateOf(R.drawable.add_image) }
    val photoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            imageUri = uri
        }
    )
    AsyncImage(
        modifier = Modifier
            .size(220.dp)
            .clickable {
                photoPicker.launch(
                    PickVisualMediaRequest(
                        ActivityResultContracts.PickVisualMedia.ImageOnly
                    )
                )
            },
        model = ImageRequest.Builder(context).data(imageUri).crossfade(true).build(),
        contentScale = ContentScale.Crop,
        contentDescription = "",
    )
}

@Composable
fun PhotoPicker2(modifier: Modifier = Modifier) {
    var selectedImage by remember {
        mutableStateOf<Uri?>(null)
    }
    val photoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            selectedImage = uri
        })

    var selectedImages by remember {
        mutableStateOf<List<Uri>>(emptyList())
    }
    val multiplePhotoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(),
        onResult = { uris ->
            selectedImages = uris
        })

    LazyRow(
        modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        photoPicker.launch(
                            PickVisualMediaRequest(
                                ActivityResultContracts.PickVisualMedia.ImageOnly
                            )
                        )
                    }) {
                    Text(text = "Pick one image")
                }
                Button(
                    onClick = {
                        multiplePhotoPicker.launch(
                            PickVisualMediaRequest(
                                ActivityResultContracts.PickVisualMedia.ImageOnly
                            )
                        )
                    }) {
                    Text(text = "Pick multiple images")
                }
            }

        }
        item {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(selectedImage)
                    .crossfade(true).build(),
                contentDescription = "",
                modifier = Modifier.size(220.dp)
            )
        }
        items(selectedImages.size) { index ->
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(selectedImages[index])
                    .crossfade(true).build(),
                contentDescription = "",
                modifier = Modifier.size(220.dp)
            )
        }
    }
}



