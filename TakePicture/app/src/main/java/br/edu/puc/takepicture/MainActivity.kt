package br.edu.puc.takepicture

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.camera.core.CameraControl
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.lifecycle.awaitInstance
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowColumnScopeInstance.align
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import br.edu.puc.takepicture.ui.permission.WithPermission
import br.edu.puc.takepicture.ui.theme.TakePictureTheme
import java.io.File

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

    val localContext = LocalContext.current

    //Variaveis de controle de configuracoes da camera
    var lensFacing by remember { mutableStateOf(CameraSelector.LENS_FACING_FRONT) }

    var zoomLevel by remember { mutableFloatStateOf(0.0f) }
    var imageCaptureUseCase = remember {
        ImageCapture.Builder().build()
    }

    //Precisamos abrir o preview aqui
    //Temos que construir um composable chamado CameraPreview para facilitar o trabalho
    //quando sempre precisarmos usar um preview camera.

    CameraPreview(
        lensFacing = lensFacing,
        zoomLevel = zoomLevel,
        imageCaptureUseCase = imageCaptureUseCase
    )

    //Layout da tela que vai mostrar o preview
    Column(
        modifier = Modifier.align(Alignment.BottomCenter)
    ) {
        Row {
            Button(onClick = {
                lensFacing = CameraSelector.LENS_FACING_FRONT
            }) {
                Text("Front Camera")
            }

            Button(onClick = {
                lensFacing = CameraSelector.LENS_FACING_BACK
            }) {
                Text("Back Camera")
            }
        }

        Row {
            Button(onClick = {zoomLevel = 0.0f}) {
                Text("Zoom 0.0")
            }

            Button(onClick = {zoomLevel = 0.5f}) {
                Text("Zoom 0.5")
            }

            Button(onClick = {zoomLevel = 1.0f}) {
                Text("Zoom 1.0")
            }
        }

        Button(onClick = {
            //Definir as opcoes de saida de arquivo (onde a camera vai jogar a foto)
            val outputFileOptions = ImageCapture
                .OutputFileOptions
                .Builder(File(localContext.externalCacheDir, "image.jpg"))

            //Resultado do salvamento (callback se o ImageCapture conseguiu gravar no sistema de arquivos)
            val callback = object : ImageCapture.OnImageSavedCallback{
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    Log.i("Camera", "Imagem salva...")
                }

                override fun onError(exception: ImageCaptureException) {
                    Log.e("Camera", "Imagem não foi gravada...")
                }
            }
            imageCaptureUseCase.takePicture(outputFileOptions,
                ContextCompat.getMainExecutor(localContext), callback
            )

        }) {
            Text("Take photo")
        }
    }
}

@Composable
fun CameraPreview(
    modifier: Modifier = Modifier,
    lensFacing: Int,
    zoomLevel: Float,
    imageCaptureUseCase: ImageCapture
){
    //Constante que representa o use case de preview
    val previewUseCase = {
        //Exemplo de referencia absoluta (começando no pacote raiz)
        androidx.camera.core.Preview.Builder().build()
    }

    var cameraProvider by remember { mutableStateOf<ProcessCameraProvider?>(null) }
    var cameraControl by remember { mutableStateOf<CameraControl?>(null) }

    val localContext = LocalContext.current

    fun rebindCameraProvider(){
        cameraProvider?.let { cameraProvider ->
            val cameraSelector = CameraSelector
                .Builder()
                .requireLensFacing(lensFacing)
                .build()
            cameraProvider.unbindAll()

            val camera = cameraProvider.bindToLifecycle(
                localContext as LifecycleOwner,
                cameraSelector,
                previewUseCase,
                imageCaptureUseCase
            )

            cameraControl = camera.cameraControl
        }

    }

    LaunchedEffect(Unit){
        cameraProvider = ProcessCameraProvider.awaitInstance(localContext)
        rebindCameraProvider()
    }

    LaunchedEffect(lensFacing){
        rebindCameraProvider()
    }

    LaunchedEffect(zoomLevel) {
        cameraControl?.setLinearZoom(zoomLevel)
    }

    AndroidView(
        modifier = modifier.fillMaxSize(),
        factory = {context ->
            PreviewView(context).also {
                previewUseCase.surfaceProvider = it.surfaceProvider
                rebindCameraProvider()
            }
        }
    )

}