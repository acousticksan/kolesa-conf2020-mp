package ru.acousticksan.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageAsset
import androidx.compose.ui.graphics.asImageAsset
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.skija.Image
import java.io.InputStream
import java.net.URL

@Composable
actual fun imageResource(res: String) = androidx.compose.ui.res.imageResource("$res.png")