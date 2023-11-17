package com.example.exercise2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel


class HalamanFormulir : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExerciseApp {
                Surface(
                    Modifier.fillMaxSize(), color = colorScheme.background
                ) {
                    TampilLayout()
                }
            }
        }
    }
}
@Composable
fun SelectJK(
    options: List<String>,
    oneSelectionChanged: (String) -> Unit = {}
){
    var selectedValue by rememberSaveable { mutableStateOf("") }
    Column(modifier = Modifier.padding(16.dp)){

        Text(
            text = "Dosen Pembimbing 1:",
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.Bold
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            options.forEach { item ->
                Row(
                    modifier = Modifier.selectable(
                        selected = selectedValue == item,
                        onClick = {
                            selectedValue = item
                            oneSelectionChanged(item)
                        }
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(selected = selectedValue == item, onClick = {
                        selectedValue = item
                        oneSelectionChanged(item)
                    })
                    Text(item)
                }
            }
        }


        Text(
            text = "Dosen Pembimbing 2:",
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.Bold
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val statusOptions = listOf("Haris Setiawan", "Dwi Djoko P, Aprilia Kurnianti")
            statusOptions.forEach { item ->
                Row(
                    modifier = Modifier.selectable(
                        selected = selectedValue == item,
                        onClick = {
                            selectedValue = item
                            oneSelectionChanged(item)
                        }
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(selected = selectedValue == item, onClick = {
                        selectedValue = item
                        oneSelectionChanged(item)
                    })
                    Text(item)
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TampilForm(cobaViewModel:CobaViewModel = viewModel()){
    var textNama by remember {
        mutableStateOf("")
    }
    var textNIM by remember {
        mutableStateOf("")
    }
    var textKonsentrasi by remember {
        mutableStateOf("")
    }
    var textjudul by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current
    OutlinedTextField(
        value = textNama,
        singleLine = true,
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Nama Mahasiswa")},
        onValueChange = {
            textNama = it
        }
    )
    OutlinedTextField(
        value = textNIM,
        singleLine = true,
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "NIM")},
        onValueChange = {
            textNIM = it
        }
    )
    OutlinedTextField(
        value = textKonsentrasi,
        singleLine = true,
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "NIM")},
        onValueChange = {
            textKonsentrasi = it
        }
    )
    OutlinedTextField(
        value = textjudul,
        singleLine = true,
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Judul Skripsi")},
        onValueChange = {
            textjudul = it
        }
    )
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = {
            cobaViewModel.insertData(textNama,textjudul, textKonsentrasi, textjudul)
        }
    ) {
        Text(
            text = stringResource(R.string.submit),
            fontSize = 16.sp
        )
    }
    Spacer(modifier = Modifier.height(100.dp))
    TextHasil(
        namanya = cobaViewModel.nama,
        nimnya = cobaViewModel.nim,
        konsentrasinya = cobaViewModel.konsentrasi,
        judulnya = cobaViewModel.judul
    )
}
@Composable
fun TampilLayout(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(20.dp)
        ) {
            Text(text = "Formulir Pengajuan Skripsi", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            TampilForm()
        }
    }
}
