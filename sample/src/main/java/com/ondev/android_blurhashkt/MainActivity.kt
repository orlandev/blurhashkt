package com.ondev.android_blurhashkt

import android.content.res.Resources
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toDrawable
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.ondev.android_blurhashkt.ui.theme.Android_blurhashktTheme
import com.ondev.blurhashkt_lib.BlurhashDecoder

class MainActivity : ComponentActivity() {
    @ExperimentalCoilApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Android_blurhashktTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    App(res = resources)
                }
            }
        }
    }
}

const val imageURL = "https://blurha.sh/assets/images/img1.jpg"


@ExperimentalCoilApi
@Composable
fun App(res: Resources) {
    val blur =
        "LEHV6nWB2yk8pyo0adR*.7kCMdnj"
    val bitmap = BlurhashDecoder.decode(blur, 4, 3)
    Card(
        modifier = Modifier
            .height(300.dp)
            .fillMaxWidth()
            .padding(8.dp),
        shape = MaterialTheme.shapes.small
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            painter = rememberImagePainter(imageURL, builder = {
                when {
                    bitmap != null -> placeholder(bitmap.toDrawable(res))
                    else -> placeholder(R.drawable.no_image)
                }
                error(R.drawable.no_image)
                crossfade(700)
            }),
            contentDescription = null
        )
    }
}
