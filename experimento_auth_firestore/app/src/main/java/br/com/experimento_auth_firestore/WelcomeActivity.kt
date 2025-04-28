package br.com.experimento_auth_firestore

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import br.com.experimento_auth_firestore.ui.theme.Experimento_auth_firestoreTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

class WelcomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Experimento_auth_firestoreTheme {
                ExperimentoApp()
            }
        }
    }
}

@Composable
fun ExperimentoApp(){
    PaginaInicial(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center))
}

@Composable
fun PaginaInicial(modifier: Modifier = Modifier){

    val context = LocalContext.current

    Column(modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Text(text = "Bem-vindo ao programa experimental Firebase!",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth().padding(bottom = 50.dp),
            textAlign = TextAlign.Center
        )

        Button(onClick = {
            Intent(context, SignUpActivity::class.java).also {
                context.startActivity(it)
            }
        }) {
            Text("Faça seu cadastro")
        }

        Button(onClick = {
            Intent(context, SignInActivity::class.java).also {
                context.startActivity(it)
            }
        }) {
            Text("Entrar")
        }
    }
}