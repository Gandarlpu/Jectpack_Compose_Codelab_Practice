package com.example.compose_oh_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_oh_practice.ui.theme.Compose_oh_practiceTheme
import kotlinx.coroutines.launch

@ExperimentalComposeUiApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            // 컴포넌트1,2가 text, setValue로 설정
            val (text, setValue) = remember{
                mutableStateOf("")
            }

            val keyboardController = LocalSoftwareKeyboardController.current
            val scaffoldState = rememberScaffoldState()
            val scope = rememberCoroutineScope()

            // Scaffold
            Scaffold(
                //
                scaffoldState = scaffoldState,

            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ){
                    TextField(

                        // 컴포넌트1,2사용
                        value = text,
                        onValueChange = setValue
                    )
                    Button(
                        onClick = {
                            keyboardController?.hide() //입력 후 버튼 누르면 키보드 자동으로 내려감
                            scope.launch {
                                scaffoldState.snackbarHostState.showSnackbar("Hello $text")
                            }
                        }
                    ) {
                        Text("클릭")
                    }
                }
            }


        }
    }
}

