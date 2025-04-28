//IDEALMENTE Temos uma classe por ARQUIVO.kt

// Pacote onde esta classe está.
package br.com.helloworld

//Importação dos recursos de esta classe usa.
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.helloworld.ui.theme.HelloWorldTheme

open class Animal{
    var peso = 0.0 //Atributo
}

class Cachorro: Animal(){
    fun latir(){ //Função dentro de uma classe é chamada de métod.o
        println("Au Au")
    }
}

class Gato: Animal(){
    fun miar(){
        println("Miau")
    }
}


//Definição da classe MainActivity
class MainActivity : ComponentActivity() {

//    var bella: Cachorro? = null //Atributo
//    var tom: Gato? = null

    // Atributo de exemplo:
    var estado: String = ""

    //Implementação obrigatória do onCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HelloWorldTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
        estado = "CREATED"
        // Log é uma classe que possui um mét_odo estático que pode ser chamado
        //diretamente sem ter uma instancia.
        Log.i("ESTADO", estado)
    }

    // Após a tela ser criada em memória pelo onCreate, será automaticamente
    //executado o onStart e aqui temos a tela pronta para ser exibida.
    override fun onStart() {
        super.onStart()

        estado = "STARTED"
        Log.i("ESTADO", estado)
    }

    // Após a tela ser construída, inicializada, o Android direcionará
    //para a chamada do onResume para que o usuário possa interagir.
    override fun onResume() {
        super.onResume()

        estado = "RESUMED"
        Log.i("ESTADO", estado)
    }

    // A tela entra em estado de "PAUSA" quando alguma tela de maior
    //prioridade surge na frente (exemplo: Smartphone recebe uma ligação)
    override fun onPause() {
        super.onPause()

        estado = "PAUSED"
        Log.i("ESTADO", estado)
    }

    // Quando a tela reaparece (depois da ligação encerrada a tela ressurge,
    //ou seja, restart)
    override fun onRestart() {
        super.onRestart()

        estado = "RESTARTED"
        Log.i("ESTADO", estado)
    }

    // Depois de um tempo se o usuário não fizer nada com aquela tela, ficará
    //em estado pré-encerramento (parada)
    override fun onStop() {
        super.onStop()

        estado = "STOPPED"
        Log.i("ESTADO", estado)
    }

    //
    override fun onDestroy() {
        super.onDestroy()

        estado = "DESTROYED"
        Log.i("ESTADO", estado)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HelloWorldTheme {
        Greeting("Android")
    }
}