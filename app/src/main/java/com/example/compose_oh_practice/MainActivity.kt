package com.example.compose_oh_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose_oh_practice.ui.theme.Compose_oh_practiceTheme
import kotlinx.coroutines.launch

@ExperimentalComposeUiApi
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen()
        }
    }
}


@Composable
fun HomeScreen(viewModel: MainViewModel = viewModel()){

    // text1 타입은 mutableState
    // 따라서 접근해서 수정할 때 text1.value = "변경"
    val text1 : MutableState<String> = remember {
        mutableStateOf("Hello World")
    }

    //  by는 3번째 방법에서 text의 효과를 가져온다.
    // text2의 값을 변경할 때 by는 setter getter를 가져와서 활용
    var text2 : String by remember {
        mutableStateOf("Hello World")
    }

    // setText에 접근해서 데이터 변경
    // 구조 분해 방법
    val (text : String, setText : (String) -> Unit) = remember{
        mutableStateOf("Hello World")
    }
    // mutableStateOf의 값을 text가 취하고 setter의 역할을 할애를 꺼내놓고 활용함.

    // LiveData
    val text3 : State<String> = viewModel.liveData.observeAsState("Hello World")
    
    Column() {

        Text("Hello World")
        Button(
            onClick = {
                text1.value = "변경"
                print(text1.value)

                text2 = "변경"
                print(text2)
            
                setText("변경")

                // ViewModel안에 있는 값 변경
                //viewModel.value.value = "변경" => error 왜냐면 val value가 읽기전용이기 때문
                // 따라서 함수를 사용해서 바꿔줘야함.
                viewModel.changeValue("변경")
            }
        ) {
            Text("클릭")
        }
        // 그래서 text를 TextField에 넣고, 받아온 값은 setText로 받아와서 text에 넣어줌.
        // 이러한 것을 리컴포지션 이라고 한다. (그림을 다시 그려줌.)
        TextField(
            value = text,
            onValueChange = setText
        )
    }

}

class MainViewModel : ViewModel(){

    private val _value : MutableState<String> = mutableStateOf("Hello World")
    val value : State<String> = _value

    // LiveData
    // _liveData에 접근해서 값을 변경 후 liveData로 UI를 그림
    private val _liveData = MutableLiveData<String>()
    val liveData : LiveData<String> = _liveData

    fun changeValue(value : String){
        _value.value = value
    }

}