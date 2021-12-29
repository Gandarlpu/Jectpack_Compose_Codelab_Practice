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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_oh_practice.ui.theme.Compose_oh_practiceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isFavorite by rememberSaveable{
                mutableStateOf(false)
            }
            ImageCard(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(16.dp),
                isFavorite = isFavorite
            ){ favorite ->
                // onTabFavorite의 콜백함수의 반환값을 여기서 처리
                isFavorite = favorite
                // isFavorite를 갱신
            }
        }
    }
}


@Composable
fun ImageCard(
    // 재사용률을 높이기 위해서는 modifier를 받아오도록 설정
    modifier : Modifier = Modifier,
    // remembersavable을 사용한 시점에서 재사용은 불가능하다.
    // Composable을 사용하는 이유중 하나는 재사용을 하기위한 것
    // 따라서 isFavorite을 외부 값에서 받아오자 (상수 값)
    isFavorite : Boolean,
    // onClick에서 isFavorite는 상수이기 때문에 수정할 수 없다.
    // 외부에서 수정되서 가지고 와야한다.
    // 따라서, 콜백을 이용해서 가져오자
    onTabFavorite : (Boolean) -> Unit
){

    // by를 사용하면 set을 할 때 알아서 value에 값을 넣어줌
    // =을 사용했을 때 isFavorite의 자료형이 mutableStateOf
    // by를 사용했을 때는 boolean
    // //isFavorite.value = !isFavorite.value 에서 value를 없애줘도 됨.
//    var isFavorite by rememberSaveable{
//        mutableStateOf(false)
//    }

    // 화면을 회전할 때 안드로이드는 화면을 새로 구성하기 때문에 remember상태값이 초기화 된다.
    // 그래서 나온것이 rememberSaveble

    Card(
        modifier = modifier,
        // 모서리 굴곡 만들기
        shape = RoundedCornerShape(8.dp),
        // 그림자
        elevation = 5.dp,

    ) {
        Box(
            // 높이
            modifier = Modifier.height(200.dp),
        ){
            // Image에서 drawable에 있는 이미지를 사용하려면 painter를 사용해야함
            Image(painter = painterResource(id = R.drawable.poster),
                  contentDescription = "poster",
                  // 이미지 뒤쪽으로 배치
                  contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier.fillMaxSize(),
                //정렬
                contentAlignment = Alignment.TopEnd,
            ){
                IconButton(onClick = {
                    onTabFavorite.invoke(!isFavorite)
                    //isFavorite.value = !isFavorite.value
                }) {
                    Icon(imageVector = if(isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "favorite",
                        tint = Color.White
                    )
                }
            }
        }
    }
}
