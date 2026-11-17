package com.remo.penjadwalan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.remo.penjadwalan.penjadwalan.JadwalScreen
import com.remo.penjadwalan.ui.theme.PenjadwalanTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PenjadwalanTheme {
                Start()
            }
        }
    }
}

@Composable
@Preview(
    device = Devices.PIXEL_8,
    showSystemUi = true
)
fun Start(){
    JadwalScreen()
}