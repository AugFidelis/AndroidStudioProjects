package br.com.telanubank

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.telanubank.ui.theme.TelaNubankTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TelaNubankTheme {
                LimiteScreen()
            }
        }
    }
}

@Composable
fun LimiteScreen() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row() {
            Image(
                painter = painterResource(R.drawable.icon_close),
                contentDescription = "Ícone de fechar",
                modifier = Modifier.weight(1f)
            )

            Text("Ajuste de Limite",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(4f)
            )
        }

        Text("R$ 4.000,00",
            modifier = Modifier.padding(50.dp, 50.dp, 50.dp, 10.dp),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold)

        HorizontalDivider(modifier = Modifier.size(170.dp, 2.dp), thickness = 2.dp)

        Text("R$ 3.000,00 de limite disponível",
            fontSize = 14.sp,
            color = Color.Green,
            modifier = Modifier.padding(top = 10.dp)
        )

        //Sugestão 1: ver na documentação se você consegue mudar o slider padrão,
        //mudar a bolinha para a gota do nubank, menos trabalho

        //Pesquisar "How to create custom views with compose" e criar um slider custom


    }
}

@Preview(showBackground = true)
@Composable
fun LimitePreview() {
    TelaNubankTheme {
        LimiteScreen()
    }
}