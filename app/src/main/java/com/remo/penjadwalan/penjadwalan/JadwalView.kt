package com.remo.penjadwalan.penjadwalan

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun JadwalScreen(){
    val vm = JadwalViewModel()
    val data by vm.jadwals.collectAsState()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        JadwalCatalog(
            data,
            onDelete = { id -> vm.delete(id) },
            Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun JadwalCatalog(data: List<Jadwal>, onDelete: (Int) -> Unit, modifier: Modifier){
    Column(modifier = modifier){
        data.forEach {
            JadwalCard(it) { onDelete(it.id) }
        }
    }
}

@Composable
fun JadwalCard(
    jadwal: Jadwal,
    onDelete: () -> Unit,
){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 8.dp, vertical = 3.dp)
        .clip(RoundedCornerShape(15))
        .background(Color.Black)
    ){
        Column(
            Modifier.weight(1f).padding(vertical = 8.dp, horizontal = 10.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(jadwal.title, color = Color.White, fontWeight = FontWeight.Bold)
            Text(jadwal.desc, color = Color.White)
        }
        IconButton(onClick = onDelete) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Jadwal", tint = Color.Red)
        }
    }
}

@Composable
@Preview(
    device = Devices.PIXEL_8,
    showSystemUi = true
)
fun Preview(){
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        JadwalCatalog(
            listOf(
                Jadwal(1, "Nub", "Test"),
                Jadwal(2, "Nub2", "Test2"),
                Jadwal(3, "Nub3", "Test3")
            ),
            {},
            Modifier.padding(innerPadding),
        )
    }
}