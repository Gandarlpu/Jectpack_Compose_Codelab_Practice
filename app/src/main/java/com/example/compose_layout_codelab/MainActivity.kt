package com.example.compose_layout_codelab

import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.compose_layout_codelab.ui.theme.Compose_layout_codelabTheme
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose_layout_codelabTheme {
                //layoutsCodelab()
                MySnackbar()
            }
        }
    }
}


@Composable
fun MySnackbar(){

    val snackbarHostState = remember {SnackbarHostState()}

    val coroutineScope = rememberCoroutineScope()

    val buttonTitle : (SnackbarData?) -> String = { snackbarData ->
        if(snackbarData != null){
            "스낵바 숨기기"
        }else{
            "스낵바 보여주기"
        }
    }

    val buttonColor : (SnackbarData?) -> Color = { snackbarData ->
        if(snackbarData != null){
            androidx.compose.ui.graphics.Color.Black
        }else{
            androidx.compose.ui.graphics.Color.Blue
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center

    ){
        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = buttonColor(snackbarHostState.currentSnackbarData),
                contentColor = androidx.compose.ui.graphics.Color.White
            ),
            onClick = {
            Log.d("TAG" , "MySnackBar : 스낵바 버튼 클릭")
            if(snackbarHostState.currentSnackbarData != null){
                Log.d("TAG" , "이미 스낵바가 있다.")
                snackbarHostState.currentSnackbarData?.dismiss()
                return@Button
            }

            coroutineScope.launch {

                //Toast메시지 띄우듯이
                snackbarHostState.showSnackbar(
                    "오늘도 빡코딩",
                    "확인",
                    SnackbarDuration.Short
                ).let { //let와 it을 통해 결과값을 바로 받을 수 있음 변수없이
                    when(it){
                        SnackbarResult.Dismissed -> {
                            Log.d("TAG" , "MySnackbar : 스낵바 닫아짐")
                        }
                        SnackbarResult.ActionPerformed ->{
                            Log.d("TAG" , "Mysnackbar : 스낵바 확인 버튼 클릭")
                        }
                    }
                }
            }

        }){
            Text(buttonTitle(snackbarHostState.currentSnackbarData))
        }
    
        //스낵바가 보여지는 부분
        SnackbarHost(hostState = snackbarHostState , modifier = Modifier.align(Alignment.BottomCenter))
        
    }

}




@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Compose_layout_codelabTheme {
        MySnackbar()
    }
}