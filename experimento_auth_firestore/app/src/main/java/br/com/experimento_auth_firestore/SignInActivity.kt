package br.com.experimento_auth_firestore

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.experimento_auth_firestore.ui.theme.Experimento_auth_firestoreTheme
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Experimento_auth_firestoreTheme {
                SignInApp()
            }
        }
    }
}

@Composable
fun SignInApp(){
    PaginaSignIn(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center))
}

val auth = FirebaseAuth.getInstance()

@Preview(showBackground = true)
@Composable
fun PaginaSignIn(modifier: Modifier = Modifier){
    //Em SignInActivity construa um layout
    //composto do campo e-mail, senha e o botão Entrar.

    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }

    val context = LocalContext.current

    Column(modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        OutlinedTextField(modifier = Modifier.padding(10.dp),
            value = email,
            onValueChange = { email = it },
            label = { Text("E-mail") })

        OutlinedTextField(modifier = Modifier.padding(10.dp),
            value = senha,
            onValueChange = { senha = it },
            label = { Text("Senha") })

        Button(onClick = {
            auth.signInWithEmailAndPassword(email,senha)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        Log.d("AUTH","LOGIN REALIZADO COM SUCESSO")

                        Intent(context, MainActivity::class.java).also {
                            context.startActivity(it)
                        }
                    }else{
                        Log.e("AUTH","LOGIN FALHO")
                    }
                }


        }) {
            Text("Entrar")
        }


    }
}