package com.example.piedrapapeltijera

import android.os.Bundle
import android.text.style.BackgroundColorSpan
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.piedrapapeltijera.ui.theme.PiedraPapelTijeraTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PiedraPapelTijeraTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,

                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
/*https://medium.com/@squonk-/learn-creating-a-desktop-app-with-jetpack-compose-rock-paper-scissors-54a55bf4fbf5*/
    //hay una columna.
    Column (
        modifier=Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween

    ) {
        Row (modifier=Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center){
            Text(text = "Recuento")
        }
        //Tenemos una fila con tres imágenes.
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.height(70.dp)



        ) {


            Image(
                painter = painterResource(id = R.drawable.piedra),
                contentDescription = "piedra",
                modifier= Modifier.weight(1F,true)
                    .clickable {  }

            )



            Image(
                painter = painterResource(id = R.drawable.papel),
                contentDescription = "papel",
                modifier= Modifier.weight(1F, true) .clickable {  }
            )



            Image(
                painter = painterResource(id = R.drawable.tijeras),
                contentDescription = "tijeras",
                modifier= Modifier.weight(1F, true) .clickable {  }
            )

        }

        Row ( horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()

                ){

            Column (horizontalAlignment = Alignment.CenterHorizontally){

                Text(text = "Jugador 1", modifier = Modifier.padding(24.dp))
                Image(painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = "whatever",
                    modifier= Modifier.size(150.dp))

            }

            Column(horizontalAlignment = Alignment.CenterHorizontally){
                Text(text = "Jugador 2", modifier = Modifier.padding(24.dp))
                Image(painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = "whatever",
                    modifier= Modifier.size(150.dp))
            }


        }

        //Tenemos una fila con tres imágenes.
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.height(70.dp)

        ) {


            Image(
                painter = painterResource(id = R.drawable.piedra),
                contentDescription = "piedra",
                modifier= Modifier.clip(CircleShape).weight(1F,true).clickable {  }

            )



            Image(
                painter = painterResource(id = R.drawable.papel),
                contentDescription = "papel",
                modifier= Modifier.weight(1F, true) .clickable {  }
            )



            Image(
                painter = painterResource(id = R.drawable.tijeras),
                contentDescription = "tijeras",
                modifier= Modifier.weight(1F, true) .clickable {  }
            )

        }

    }



}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PiedraPapelTijeraTheme {
        Greeting("Android")
    }
}