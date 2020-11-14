package ru.acousticksan.common

import android.graphics.BitmapFactory
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageAsset
import androidx.compose.ui.graphics.asImageAsset
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import java.io.InputStream
import java.net.URL

@Composable
actual fun imageResource(res: String): ImageAsset {
    val context = ContextAmbient.current
    val id = context.resources.getIdentifier(res, "drawable", context.packageName)
    return androidx.compose.ui.res.imageResource(id)
}