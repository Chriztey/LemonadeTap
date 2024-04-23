package com.chris.lemonadetap

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chris.lemonadetap.ui.theme.LemonadeTapTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTapTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold (topBar = { CenterAlignedTopAppBar(
                        title = {Text(text = "Lemonade") },
                        colors = TopAppBarDefaults.largeTopAppBarColors(
                            containerColor = Color.Yellow
                        )


                        ) }) {

                    }

                    Lemonade(modifier = Modifier
                        .fillMaxSize(),
                    )

                }
            }
        }
    }
}

@Composable
fun Lemonade(modifier: Modifier) {

    var squeezeLemon by remember {
        mutableStateOf(1)
    }
    var squeezeNeeded by remember {
        mutableStateOf((2..4).random())
    }
    
    var imageResource = when (squeezeLemon) {
        1 -> R.drawable.lemon_tree
        //2 -> R.drawable.lemon_squeeze
        2 + squeezeNeeded -> R.drawable.lemon_drink
        2 + squeezeNeeded + 1 -> R.drawable.lemon_restart
        else -> {R.drawable.lemon_squeeze}

    }

    var imageResourceDesc = when (imageResource) {
        R.drawable.lemon_tree ->"Tap the lemon tree to select a lemon"
        R.drawable.lemon_squeeze -> "Keep tapping the lemon to squeeze it"
        R.drawable.lemon_restart -> "Tap the empty glass to start again"
        else -> {"Tap the lemonade to drink it"}

    }




    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            if (imageResource == R.drawable.lemon_restart) {
                squeezeLemon = 1
                squeezeNeeded = (2..4).random()
            } else squeezeLemon ++ },
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(2.dp, Color(105,205,216)),
            colors = buttonColors(containerColor = Color(142, 218, 146, 255))
            ) {
            Image(painter = painterResource(id = imageResource), contentDescription = "")
        }

        Spacer(Modifier.height(16.dp))

        Text(
            text = "$imageResourceDesc",
            textAlign = TextAlign.Center,
            fontSize = 18.sp
            )
    }

}

@Preview(showBackground = true)
@Composable
fun LemonadePreview() {
    LemonadeTapTheme {
        Lemonade(modifier = Modifier
            .fillMaxSize(),
            )
    }
}