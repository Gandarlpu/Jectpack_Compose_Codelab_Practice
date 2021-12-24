package com.example.compose_oh_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_oh_practice.ui.theme.Compose_oh_practiceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose_oh_practiceTheme {
                practice()
            }
        }
    }
}


@Composable
fun practice(){
    Surface(color = MaterialTheme.colors.background) {
        Column(modifier = Modifier
            .background(color = Color.Blue)
            .fillMaxSize()
            .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Text("Hello")
            Text("World")
        }

    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Compose_oh_practiceTheme {
        practice()
    }
}