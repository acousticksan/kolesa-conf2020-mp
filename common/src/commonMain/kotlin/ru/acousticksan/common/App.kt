package ru.acousticksan.common
import androidx.compose.material.Text
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import ru.acousticksan.common.component.MainScreenComponent
import ru.acousticksan.common.component.SpeakerScreenComponent
import ui.KolesaConf2020Theme

@Composable
fun App() {
    val selectedSpeaker = remember { mutableStateOf(0) }

    KolesaConf2020Theme {
        if(selectedSpeaker.value == 0){
            MainScreenComponent(selectedSpeaker)
        }else{
            SpeakerScreenComponent(selectedSpeaker)
        }
    }
}
