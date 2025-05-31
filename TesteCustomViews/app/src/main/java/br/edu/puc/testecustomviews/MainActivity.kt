package br.edu.puc.testecustomviews

import android.os.Bundle
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
import br.edu.puc.testecustomviews.ui.theme.TesteCustomViewsTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.geometry.Size
import androidx.compose.foundation.Canvas
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TesteCustomViewsTheme {
                MusicKnob()
            }
        }
    }
}

@Composable
fun MusicKnob(){
    Canvas(modifier = Modifier.fillMaxSize()) {

        val halfScreenWidth = size.width * .5f
        val radius = halfScreenWidth - (halfScreenWidth * .25f)

        drawCircle(
            color = Color.LightGray,
            style = Stroke(
                width = (radius * .1f)
            ),
            radius = radius,
            center = size.center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MusicKnowPreview(){
    MusicKnob()
}