package ru.acousticksan.common.component

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.DataProvider
import data.DirectionType
import data.Speaker
import ru.acousticksan.common.imageResource
import ui.blueColor

@Composable
fun MainScreenComponent(selectedSpeaker: MutableState<Int>){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Kolesa Conf 2020") }
            )
        },
        bodyContent = {
            TabsComponent(selectedSpeaker)
        }
    )
}

@Composable
fun TabsComponent(selectedSpeaker: MutableState<Int>){
    val directionList = remember { DataProvider.directionList }
    val selectedIndex = remember { mutableStateOf(0) }
    val speakerList =
        remember { mutableStateOf(DataProvider.speakerList.filter { it.directionType == DirectionType.ANDROID }) }

    Column() {
        ScrollableTabRow(
            selectedTabIndex = selectedIndex.value,
            divider = emptyContent(), /* Disable the built-in divider */
            edgePadding = 8.dp,
            indicator = noIndicator,
            backgroundColor = Color.Transparent,
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            directionList.forEachIndexed { index, direction ->
                Tab(
                    selected = index == selectedIndex.value,
                    onClick = {
                        selectedIndex.value = index
                        speakerList.value =
                            DataProvider.speakerList.filter { it.directionType == direction.directionType }
                    }
                ) {
                    CustomImageChip(
                        text = direction.name,
                        imageId = direction.image,
                        selected = index == selectedIndex.value,
                        modifier = Modifier.fillMaxWidth()
                            .padding(horizontal = 4.dp, vertical = 8.dp)
                    )
                }
            }
        }
        LazyColumnFor(items = speakerList.value) { speaker ->
            SpeakerComponent(speaker = speaker, selectedSpeaker = selectedSpeaker)
        }
    }
}

@Composable
private fun CustomImageChip(
    text: String,
    imageId: String,
    selected: Boolean,
    modifier: Modifier = Modifier
) {
    Surface(
        color = when {
            selected -> MaterialTheme.colors.primary
            else -> Color.Transparent
        },
        contentColor = when {
            selected -> MaterialTheme.colors.onPrimary
            else -> Color.White
        },
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(
            width = 1.dp,
            color = when {
                selected -> MaterialTheme.colors.primary
                else -> Color.White
            }
        ),
        modifier = modifier
    ) {
        Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
            Image(
                asset = imageResource(imageId),
                modifier = Modifier.padding(8.dp).preferredSize(20.dp).clip(CircleShape)
            )
            Text(
                text = text,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(end = 8.dp, top = 8.dp, bottom = 8.dp)
            )
        }
    }
}

private val noIndicator: @Composable (List<TabPosition>) -> Unit = {}

@Composable
private fun SpeakerComponent(speaker: Speaker, selectedSpeaker: MutableState<Int>) {

    Card(
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = {
                selectedSpeaker.value = speaker.id
            })
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                asset = imageResource(speaker.avatar),
                modifier = Modifier
                    .padding(8.dp)
                    .preferredSize(80.dp)
                    .clip(CircleShape)
                    .background(blueColor)
            )
            Column(
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text(
                    text = speaker.name,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                )
                if (!speaker.fromKolesa) {
                    Surface(
                        shape = RoundedCornerShape(4.dp),
                        color = Color.White,
                        modifier = Modifier.padding(vertical = 4.dp),
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
                    text = speaker.theme,
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                )
            }
        }
    }
}