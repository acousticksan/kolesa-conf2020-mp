package ru.acousticksan.common.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.DataProvider
import ru.acousticksan.common.imageResource
import ui.blueColor

@Composable
fun SpeakerScreenComponent(selectedSpeaker: MutableState<Int>) {
    val speaker = remember { DataProvider.speakerList.find { it.id == selectedSpeaker.value } }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Kolesa Conf 2020") },
                navigationIcon = {
                    IconButton(onClick = {
                        selectedSpeaker.value = 0
                    }) {
                        Icon(Icons.Default.ArrowBack)
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                icon = {
                    Icon(Icons.Default.Share)
                }
            )
        },
        bodyContent = {
            ScrollableColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        asset = imageResource(speaker!!.avatar),
                        modifier = Modifier
                            .padding(8.dp)
                            .preferredSize(180.dp)
                            .clip(CircleShape)
                            .background(blueColor)
                    )

                    Text(
                        text = speaker!!.name,
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                    )
                    if (!speaker!!.fromKolesa) {
                        Surface(
                            shape = RoundedCornerShape(4.dp),
                            color = Color.White,
                            modifier = Modifier.padding(vertical = 6.dp)
                        ) {
                            Text(
                                text = "Приглашённый спикер".toUpperCase(),
                                textAlign = TextAlign.Center,
                                color = Color.Black,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.padding(4.dp)
                            )
                        }
                    }
                    Text(
                        text = speaker.info,
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        asset = imageResource("baseline_access_time_white_24dp"),
                        tint = Color.White,
                        modifier = Modifier.preferredSize(32.dp)
                    )
                    Text(
                        modifier = Modifier.padding(start = 4.dp),
                        color = Color.White,
                        text = speaker!!.time,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Text(
                    modifier = Modifier.padding(bottom = 12.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.White,
                    text = speaker!!.theme
                )
                Text(
                    modifier = Modifier.padding(bottom = 8.dp),
                    color = Color.White,
                    text = speaker.description
                )

            }
        }
    )
}