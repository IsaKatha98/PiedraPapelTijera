package com.example.piedrapapeltijera

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.piedrapapeltijera.ui.theme.PiedraPapelTijeraTheme
import kotlin.random.Random


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
                    juego()
                }
            }
        }
    }
}

/**
 * Función que diseña la interfaz.
 */
@Composable
fun juego(modifier: Modifier = Modifier) {

    //Declaración de variables
    var jugador = remember{mutableStateOf("")}//Guarda la tirada del jugador
    var maquina = remember { mutableStateOf("")}//Guarda la tirada de la máquina
    var puntosJugador = remember {mutableStateOf(0) }//Guarda los puntos del jugador
    var puntosMaquina =remember { mutableStateOf(0) }//Guarda los puntos de la máquina
    val recuento="${puntosJugador.value}-${puntosMaquina.value}"//Guarda el recuento de los puntos.

    //El context para el toast
    val context= LocalContext.current
    // Hacemos el toast.
    val toast: Toast = Toast.makeText(context, "undefined!", Toast.LENGTH_SHORT)

    //Empezamos con una columna principal que contendrá el resto de elementos.
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        )
        {
            //Fila que enseña el recuento total de las puntuaciones.
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .weight(1F, true)
                    .fillMaxWidth(),

                horizontalArrangement = Arrangement.SpaceEvenly

            ) {
                Text(text = "$recuento", fontSize = 36.sp)
            }
            //Fila que indica los nombres de los jugadores.
            Row(verticalAlignment = Alignment.Top,
                modifier = Modifier
                    .weight(2F, true)
                    .fillMaxWidth(),

                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {

                        Text(text = "Jugador", modifier = Modifier.padding(24.dp))
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "Máquina", modifier = Modifier.padding(24.dp))
                    }

            }
            //Fila que contiene el tablero del juego.
            Row(verticalAlignment = Alignment.Top,
                modifier = Modifier
                    .weight(2F, true)
                    .fillMaxWidth(),

                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                //Hacemo una condición que se rompe en el momento que la puntuación o de la
                //máquina o del jugador llega a 5. Esto en realidad sería un bucle.
                if (puntosJugador.value<5&&puntosMaquina.value<5) {
                    //Llama a la función que pinta la imagen del movimiento del jugador
                    movimiento(jugador.value)

                    //LLama a la función que pinta la imagen del movimiento de la máquina,
                    //Esta imagen dependerá de la función que randomiza el movimiento de la
                    //máquina.
                    movimiento(maquina.value)

                //Cuando se rompe la condición, bloqueamos el tablero y preguntamos si quiere volver a jugar
                } else {
                    Column {
                        //Se pone al ganador
                        if (puntosJugador.value > puntosMaquina.value) {
                            Text(text = "Ha ganado el jugador", fontSize = 24.sp)
                        } else if (puntosJugador.value < puntosMaquina.value) {
                            Text(text = "Ha ganado la máquina", fontSize = 24.sp)
                        }
                        Text(fontWeight = FontWeight.Bold,
                            text = "Si quiere volver a jugar, haga click sobre este texto!!",
                            fontSize = 24.sp,

                            modifier = Modifier.clickable
                            {
                                puntosJugador.value=0
                                puntosMaquina.value=0

                            }
                        )
                        jugador.value=""
                        movimiento(jugador.value)

                        maquina.value=""
                        movimiento(maquina.value)

                    }
                }
            }

            //Tenemos una fila con tres imágenes que hacen de botón.
            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .height(70.dp)
                    .weight(2F, true)

            ) {
                Image(
                    painter = painterResource(id = R.drawable.piedra),
                    contentDescription = "piedra",
                    modifier = Modifier
                        .clip(CircleShape)
                        .weight(1F, true)
                        .clickable {
                            //Pone el movimiento del jugador a Piedra.
                            jugador.value = "Piedra"

                            //Llama a la función que devuelve un String con la tirada
                            //de la máquina.
                            maquina.value = tiradaMaquina()

                            //Hacemos el toast
                            if (jugador.value == maquina.value) {
                                toast.setText("Empate")

                            } else if (jugador.value == "Piedra" && maquina.value == "Papel") {
                                toast.setText("Ha ganado la máquina")
                                //Sumamos un punto a la máquina.
                                puntosMaquina.value++
                            } else if (jugador.value == "Piedra" && maquina.value == "Tijeras") {
                                toast.setText("Ha ganado el jugador")
                                //Sumamos un punto al jugador
                                puntosJugador.value++
                            }
                            toast.show()
                        }
                )
                Image(
                    painter = painterResource(id = R.drawable.papel),
                    contentDescription = "papel",
                    modifier = Modifier
                        .weight(1F, true)
                        .clickable {
                            //Pone el movimiento del jugador a Papel.
                            jugador.value = "Papel"

                            //Llama a la función que devuelve un String con la tirada
                            //de la máquina.
                            maquina.value = tiradaMaquina()

                            //Hacemos el toast
                            if (jugador.value == maquina.value) {
                                toast.setText("Empate")

                            } else if (jugador.value == "Papel" && maquina.value == "Tijeras") {
                                toast.setText("Ha ganado la máquina")
                                //Sumamos un punto a la máquina.
                                puntosMaquina.value++
                            } else if (jugador.value == "Papel" && maquina.value == "Piedra") {
                                toast.setText("Ha ganado el jugador")
                                //Sumamos un punto al jugador
                                puntosJugador.value++
                            }
                            toast.show()
                        }
                )
                Image(
                    painter = painterResource(id = R.drawable.tijeras),
                    contentDescription = "tijeras",
                    modifier = Modifier
                        .weight(1F, true)
                        .clickable {
                            //Pone el movimiento del jugador a Tijeras.
                            jugador.value = "Tijeras"

                            //Llama a la función que devuelve un String con la tirada
                            //de la máquina.
                            maquina.value = tiradaMaquina()

                            //Hacemos el toast
                            if (jugador.value == maquina.value) {
                                toast.setText("Empate")

                            } else if (jugador.value == "Tijeras" && maquina.value == "Piedra") {
                                toast.setText("Ha ganado la máquina")
                                //Sumamos un punto a la máquina.
                                puntosMaquina.value++
                            } else if (jugador.value == "Tijeras" && maquina.value == "Papel") {
                                toast.setText("Ha ganado el jugador")
                                //Sumamos un punto al jugador
                                puntosJugador.value++
                            }
                            toast.show()
                        }
                )

            }
        }
        }

/**
 * Método que pinta la imagen según el movimiento del jugador o de la máquina.
 * Hace un switch según el string de entrada.
 * @param imagen cadena que indica de qué tipo es el movimiento
 */
@Composable
fun movimiento(imagen:String) {
    when (imagen) {
        "Piedra"-> Image (painter = painterResource(id = R.drawable.piedra), contentDescription = "Piedra")
        "Papel"-> Image(painter = painterResource(id = R.drawable.papel), contentDescription = "Papel")
        "Tijeras"-> Image(painter = painterResource(id = R.drawable.tijeras), contentDescription = "Tijeras")
    }

}

/**
 * Función que elige por la máquina. Saca un valor aleatorio de una lista.
 * Según el index, se asigna uno de los valores de la lista.
 * @return devuelve un String con el movimiento de la máquina
*/
fun tiradaMaquina ():String {
    var movimiento=""
    val list= listOf("Piedra", "Papel", "Tijeras")
    val index= Random.nextInt(list.size)
    movimiento=list[index]

    return movimiento
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PiedraPapelTijeraTheme {
        juego()
    }
}