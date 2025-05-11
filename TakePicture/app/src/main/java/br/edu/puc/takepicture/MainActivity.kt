package br.edu.puc.takepicture

import android.Manifest
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
import br.edu.puc.takepicture.ui.permission.WithPermission
import br.edu.puc.takepicture.ui.theme.TakePictureTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TakePictureTheme {
                Scaffold(modifier = Modifier.fillMaxSize())
                { innerPadding ->
                    WithPermission(
                        modifier = Modifier.padding(innerPadding),
                        permission = Manifest.permission.CAMERA
                    ) {
                        CameraAppScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun CameraAppScreen(){
    //TO-DO: implementar...
}