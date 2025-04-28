package br.com.experimento_auth_firestore

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.experimento_auth_firestore.ui.theme.Experimento_auth_firestoreTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Experimento_auth_firestoreTheme {
                WelcomeApp()
            }
        }
    }
}

@Composable
fun WelcomeApp(){
    PaginaWelcome(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center))
}

@Preview(showBackground = true)
@Composable
fun PaginaWelcome(modifier: Modifier = Modifier){
    //Por fim na MainActivity, coloque o texto apenas: Bem-vindo.

    var nome by remember { mutableStateOf<String?>("") }
    var email by remember { mutableStateOf<String?>("") }
    var cpf by remember { mutableStateOf<String?>("") }
    var rg by remember { mutableStateOf<String?>("") }
    var sucesso by remember { mutableStateOf(false) }

    Column(modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Text(text = "Bem-vindo!", modifier = Modifier.fillMaxWidth().padding(50.dp),
            fontSize = 20.sp, fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center )

        val db = FirebaseFirestore.getInstance()
        val uid = FirebaseAuth.getInstance().currentUser?.uid

        if (uid != null) {
            db.collection("Usuários").document(uid).get()
                .addOnSuccessListener { documento ->
                    nome = documento.getString("nome")
                    email = documento.getString("email")
                    cpf = documento.getString("cpf")
                    rg = documento.getString("rg")

                    sucesso = true

                } .addOnFailureListener {
                    Log.e("DOCUMENTOS","ERRO AO CARREGAR DOCUMENTO")
                }
        }

        if(sucesso) {
            Text("Nome: $nome")
            Text("E-mail: $email")
            Text("CPF: $cpf")
            Text("RG: $rg")
        }
    }
}