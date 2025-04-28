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
import com.google.firebase.auth.auth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Experimento_auth_firestoreTheme {
                SignUpApp()
            }
        }
    }
}

fun RegistrarUsuarioNaFirestore(nome: String, email: String, rg: String, cpf: String, uid: String){
    val db = Firebase.firestore

    val registro = hashMapOf(
        "nome" to nome,
        "email" to email,
        "rg" to rg,
        "cpf" to cpf
    )

    db.collection("Usuários").document(uid).set(registro)
}

@Composable
fun SignUpApp(){
    PaginaSignUp(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center))
}

@Preview(showBackground = true)
@Composable
fun PaginaSignUp(modifier: Modifier = Modifier){
    //Na SingUpActivity construa um layout composto dos campos:
    //Nome, E-mail, Senha, RG e CPF e um botão Criar Minha Conta.

    var nome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var rg by remember { mutableStateOf("") }
    var cpf by remember { mutableStateOf("") }

    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()

    Column(modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        OutlinedTextField(modifier = Modifier.padding(10.dp),
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Nome") })

        OutlinedTextField(modifier = Modifier.padding(10.dp),
            value = email,
            onValueChange = { email = it },
            label = { Text("E-mail") })

        OutlinedTextField(modifier = Modifier.padding(10.dp),
            value = senha,
            onValueChange = { senha = it },
            label = { Text("Senha") })

        OutlinedTextField(modifier = Modifier.padding(10.dp),
            value = rg,
            onValueChange = { rg = it },
            label = { Text("RG") })

        OutlinedTextField(modifier = Modifier.padding(10.dp),
            value = cpf,
            onValueChange = { cpf = it },
            label = { Text("CPF") })

        Button(onClick = {
            auth.createUserWithEmailAndPassword(email,senha)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        Log.d("AUTH","USUÁRIO REGISTRADO COM SUCESSO")
                        val uid = FirebaseAuth.getInstance().currentUser?.uid

                        if (uid != null) {
                            RegistrarUsuarioNaFirestore(nome, email, rg, cpf, uid)
                        }

                        Intent(context, SignInActivity::class.java).also {
                            context.startActivity(it)
                        }
                    } else{
                        Log.e("AUTH","ERRO AO REGISTRAR USUÁRIO", task.exception)
                    }
                }

        }, modifier = Modifier.padding(top = 50.dp)) {
            Text("Criar Minha Conta")
        }
    }
}
