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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    var isFormOpen by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = { isFormOpen = true },
                contentColor = Color.White,
                containerColor = Color.Blue,
                content = { Icon(imageVector = Icons.Default.Add, contentDescription = "Open Jadwal Form", tint = Color.White) }
            )
        }
    ) { innerPadding ->
        JadwalCatalog(
            data,
            onDelete = { id -> vm.delete(id) },
            Modifier.padding(innerPadding)
        )

        if(isFormOpen){
            JadwalForm(
                onDismiss = { isFormOpen = false },
                onSubmit = { jadwal -> vm.create(jadwal) },
                lastIndex = { data.lastIndex }
            )
        }
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
fun JadwalForm(onDismiss: () -> Unit, onSubmit: (Jadwal) -> Unit, lastIndex: () -> Int){
    var title by remember { mutableStateOf("") }
    var desc by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    AlertDialog(
        title = { Text("Create Jadwal") },
        onDismissRequest = onDismiss,
        dismissButton = {
            TextButton(onDismiss) {
                Text("Batal")
            }
        },
        confirmButton = {
            Button(
                {
                    if(title.isNotBlank()){
                        onSubmit(Jadwal(lastIndex() + 2, title, desc))
                    }
                    else{
                        isError = true
                    }
                    onDismiss()
                }
            ) { Text("Submit") }
        },
        text = {
            Column {
                OutlinedTextField(value = title, label = { Text("Judul") }, singleLine = true, onValueChange = {
                    title = it
                })

                OutlinedTextField(value = desc, label = { Text("Deskripsi") }, singleLine = false, onValueChange = {
                    desc = it
                })
            }
        }
    )
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
            Modifier
                .weight(1f)
                .padding(vertical = 8.dp, horizontal = 10.dp),
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
    /*
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
    }*/
    JadwalForm({}, {}, {0})
}