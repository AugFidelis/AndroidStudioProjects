package br.pro.mateus.galaxyweather


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.FlowRowOverflow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.pro.mateus.galaxyweather.ui.theme.GalaxyWeatherTheme

/**
 * No site da Apple temos os ícones usados pelo aplicativo. Consulte para saber mais:
 * @see { https://support.apple.com/guide/iphone/learn-the-weather-icons-iph4305794fb/ios }
 *
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GalaxyWeatherTheme {
                WeatherScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun WeatherScreen() {
    Box (modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.mars),
            contentDescription = stringResource(id = R.string.app_name),
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.matchParentSize()
        )
        Column (horizontalAlignment = Alignment.CenterHorizontally) {

            HeaderInfo("Tatoiin",27,"Céu limpo",10,40)

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.padding(20.dp)
            ){
                Card(
                    modifier = Modifier.weight(1f),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = .2f),
                    )
                ) {

                    Text(
                        text = "Condições ensolaradas pelo restante do dia.\nAs rajadas de vento estão a 17km/h",
                        modifier = Modifier
                            .padding(10.dp),
                        textAlign = TextAlign.Start,
                        fontSize = 12.sp,
                        maxLines = 2,
                        lineHeight = 16.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Normal
                    )
                    HorizontalDivider(color = Color.White, thickness = 0.5.dp)
                    Spacer(Modifier.size(10.dp))
                    FlowRow(
                        maxLines = 1,
                        overflow = FlowRowOverflow.Clip,
                        modifier = Modifier.horizontalScroll(rememberScrollState()),
                    ){
                        HourVariationInfo("Agora", R.drawable.icon_sunset, "21°")
                        HourVariationInfo("18h", R.drawable.icon_clear, "21°")
                        HourVariationInfo("19h", R.drawable.icon_rain, "21°")
                        HourVariationInfo("20h", R.drawable.icon_snow, "-10°")
                        HourVariationInfo("21h", R.drawable.icon_sunset, "21°")
                        HourVariationInfo("22h", R.drawable.icon_sunset, "21°")
                        HourVariationInfo("23h", R.drawable.icon_sunset, "21°")
                        HourVariationInfo("0h", R.drawable.icon_clear, "21°")
                    }
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.padding(20.dp, 0.dp)
            ){
                Card(
                    modifier = Modifier.weight(1f),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = .3f),
                    )
                ) {
                    Column {
                        Row(
                            modifier = Modifier.padding(5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Rounded.DateRange,
                                contentDescription = stringResource(id = R.string.app_name),
                                modifier = Modifier.size(20.dp, 20.dp),
                                tint = Color.White
                            )
                            Text(
                                text = "PREVISÃO PARA 10 DIAS",
                                textAlign = TextAlign.Start,
                                fontSize = 12.sp,
                                maxLines = 2,
                                lineHeight = 16.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Normal
                            )
                        }
                        HorizontalDivider(color = Color.White, thickness = 0.5.dp)

                        Row(
                            Modifier.padding(10.dp,10.dp,10.dp,0.dp),
                            verticalAlignment = Alignment.CenterVertically){
                            Text(
                                modifier = Modifier.size(70.dp, 20.dp),
                                text = "Hoje",
                                textAlign = TextAlign.Start,
                                fontSize = 16.sp,
                                lineHeight = 16.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                            Image(
                                modifier = Modifier.padding(10.dp,0.dp,10.dp,0.dp).size(20.dp,20.dp),
                                painter = painterResource(id = R.drawable.icon_clear),
                                contentDescription = stringResource(id = R.string.app_name),
                                contentScale = ContentScale.Fit
                            )
                            Text(
                                text = "18°",
                                textAlign = TextAlign.End,
                                fontSize = 16.sp,
                                lineHeight = 14.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Normal
                            )
                            LinearProgressIndicator(
                                modifier = Modifier.padding(10.dp,0.dp,10.dp,0.dp).size(150.dp,4.dp)
                            )
                            Text(
                                text = "26°",
                                textAlign = TextAlign.Start,
                                fontSize = 16.sp,
                                lineHeight = 14.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Normal
                            )
                        }

                        Row(
                            Modifier.padding(10.dp,10.dp,10.dp,10.dp),
                            verticalAlignment = Alignment.CenterVertically){
                            Text(
                                text = "Seg",
                                modifier = Modifier.size(70.dp, 20.dp),
                                textAlign = TextAlign.Start,
                                fontSize = 16.sp,
                                lineHeight = 16.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Normal
                            )
                            Image(
                                modifier = Modifier.padding(10.dp,0.dp,10.dp,0.dp).size(20.dp,20.dp),
                                painter = painterResource(id = R.drawable.icon_clear),
                                contentDescription = stringResource(id = R.string.app_name),
                                contentScale = ContentScale.Fit
                            )
                            Text(
                                text = "18°",
                                textAlign = TextAlign.End,
                                fontSize = 16.sp,
                                lineHeight = 14.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Normal
                            )
                            LinearProgressIndicator(
                                modifier = Modifier.padding(10.dp,0.dp,10.dp,0.dp).size(150.dp,4.dp)
                            )
                            Text(
                                text = "26°",
                                textAlign = TextAlign.Start,
                                fontSize = 16.sp,
                                lineHeight = 14.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Normal
                            )
                        }
                    }
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.padding(20.dp, 10.dp)
            ){
                Card(
                    modifier = Modifier.weight(2f),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = .9f),
                    ),
                ) {
                    Text(
                        text = "Card 3...",
                        color = Color.Black,
                        modifier = Modifier
                            .padding(16.dp),
                        textAlign = TextAlign.Center,
                    )
                }
                Spacer(Modifier.size(10.dp))
                Card(
                    modifier = Modifier.weight(1f),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = .9f),
                    ),
                ) {
                    Text(
                        text = "Card 4...",
                        color = Color.Black,
                        modifier = Modifier
                            .padding(16.dp),
                        textAlign = TextAlign.Center,
                    )
                }

            }
        }
    }


}

@Composable
fun HeaderInfo(location: String, currentTemp: Int, status: String,
               min: Int, max: Int){

    val helveticaNeue = FontFamily(
        Font(R.font.helvetica_neue_thin, FontWeight.Light),
        Font(R.font.helvetica_neue_thin, FontWeight.Normal),
        Font(R.font.helvetica_neue_thin, FontWeight.Normal, FontStyle.Italic),
        Font(R.font.helvetica_neue_thin, FontWeight.Medium),
        Font(R.font.helvetica_neue_thin, FontWeight.Bold)
    )
    Spacer(Modifier.height(50.dp))
    Text(
        location,
        fontSize = 30.sp,
        fontFamily = helveticaNeue,
        fontWeight = FontWeight.Normal,
        color = Color(255,255,255, 255)
    )
    Text(
        currentTemp.toString() + "°",
        fontSize = 120.sp,
        fontFamily = helveticaNeue,
        fontWeight = FontWeight.Normal,
        letterSpacing = (-5).sp,
        color = Color(255,255,255, 255)
    )

    Text(
        status,
        fontSize = 19.sp,
        fontWeight = FontWeight.Bold,
        color = Color(255,255,255, 255)
    )

    Text(
        "Max.: $max°  Min.: $min°",
        fontSize = 20.sp,
        fontFamily = helveticaNeue,
        fontWeight = FontWeight.Normal,
        color = Color(255,255,255, 255)
    )


}

@Composable
fun HourVariationInfo(hour: String, @DrawableRes res: Int, temp: String){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.size(49.dp, 80.dp)
    ) {
        Text(
            color = Color.White,
            text = hour,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
        )
        Image(
            modifier = Modifier.size(20.dp,20.dp),
            painter = painterResource(id = res),
            contentDescription = stringResource(id = R.string.app_name),
            contentScale = ContentScale.Fit
        )
        Text(
            text = temp,
            color = Color.White,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GalaxyWeatherTheme {
        WeatherScreen()
    }
}