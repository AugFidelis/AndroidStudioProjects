package br.com.tasklist

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.tasklist.ui.theme.TasklistTheme
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
           TasklistTheme {
               TasklistApp()
           }
        }
    }
}

//Responsabilidade de guardar a tarefa no firebase firestore
fun addNewTaskFirestore(name: String, description: String){
    val db = Firebase.firestore
    // preparar o documento que representa uma tarefa
    val task = hashMapOf(
        "name" to name,
        "description" to description
    )
    // gravar um novo documento tarefa, na coleção tarefas.
    db.collection("tasks").add(task)
}

@Preview(showBackground = true)
@Composable
fun TasklistApp() {
    FormAddNewTask(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
    )
}

@SuppressLint("DefaultLocale")
@Composable
fun FormAddNewTask(modifier: Modifier = Modifier){
    var name by remember { mutableStateOf("")}
    var description by remember { mutableStateOf("")}

    Column(modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Text(
            text = "Adicionar uma tarefa",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        OutlinedTextField(modifier = Modifier.padding(10.dp),value = name,
            onValueChange = { name = it },
            label = { Text("Nome")})

        OutlinedTextField(modifier = Modifier.padding(10.dp),value = description,
            onValueChange = { description = it },
            label = { Text("Descrição")})

        Button(
            onClick = {
                // guardar a tarefa no firestore.
                addNewTaskFirestore(name, description)
            }
        )
        {
            Text("Cadastrar")
        }



    }
}