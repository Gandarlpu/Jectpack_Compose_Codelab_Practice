package com.example.compose_layout_codelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Color
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
                StaggeredGridGoogleExample()
            }
        }
    }
}



fun Modifier.firstBaselineToTop(
    firstBaselineToTop : Dp
) = this.then(
    layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)

        check(placeable[FirstBaseline] != AlignmentLine.Unspecified)
        val firstBaseline = placeable[FirstBaseline]

        val placeableY = firstBaselineToTop.roundToPx() - firstBaseline
        val height = placeable.height + placeableY
        layout(placeable.width , height){

        }
    }
)

@Composable
fun layoutsCodelab(){

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "LayoutCodelab")
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Filled.Favorite, contentDescription = null)
                    }
                }
            )
        }
    ) { innerPadding ->
        //BodyContent()
        ScrollingList()
    }

}


@Composable
fun ScrollingList(){

    val listSize = 100
    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Column {
        Row (horizontalArrangement = Arrangement.Center) {
            Button(onClick = {
                coroutineScope.launch {
                    scrollState.animateScrollToItem(0)
                }
            }) {
                Text("Scroll to the top")
            }

            Button(
                onClick = {
                coroutineScope.launch {
                    scrollState.animateScrollToItem(listSize-1)
                }
            }) {
                Text("Scroll to the end")
            }
        }

        LazyColumn(state = scrollState){
            items(listSize){
                ImageListItem(it)
            }
        }
    }

}


@Composable
fun ImageListItem(index : Int){

    Row(verticalAlignment = Alignment.CenterVertically){

        Image(
            painter = rememberImagePainter(
                data = "https://developer.android.com/images/brand/Android_Robot.png"
            ),
            contentDescription = "Android Logo",
            modifier = Modifier.size(50.dp)
        )
        Spacer(Modifier.width(10.dp))
        Text("Item #$index" , style= MaterialTheme.typography.subtitle1)
    }
}



@Composable
fun BodyContent(){
    val scrollState = rememberScrollState()

    //Column은 기본적으로 스크롤을 처리하지 않기 때문에
    //일부 항목은 화면 외부에 표시되지 않는다.
    Column(Modifier.verticalScroll(scrollState)) {
        repeat(100){
            Text("Item $it")
        }
    }

}


/*@Composable
fun PhotographerCard(modifier: Modifier = Modifier){

    Row(modifier
        .clip(RoundedCornerShape(4.dp))
        .background(MaterialTheme.colors.surface)
        .padding(8.dp)
        .clickable(onClick = {})
    ){
        Surface(
            modifier.size(50.dp),
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        ){
            //Image goes here
        }
        Column (
            modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically) // 여기가 글자를 정렬하는곳인듯
        ) {
            Text("Alfred Sisley", fontWeight = FontWeight.Bold)
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text("3 minutes ago" , style = MaterialTheme.typography.body2)
            }
        }// Column
    }// Row

}*/

val topics = listOf(
    "Arts & Crafts", "Beauty", "Books", "Business", "Comics", "Culinary",
    "Design", "Fashion", "Film", "History", "Maths", "Music", "People", "Philosophy",
    "Religion", "Social sciences", "Technology", "TV", "Writing"
)


@Composable
fun StaggeredGridGoogleExample(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .background(color = androidx.compose.ui.graphics.Color.LightGray)
            .size(200.dp)
            .padding(16.dp)
            .background(androidx.compose.ui.graphics.Color.Yellow)
            .horizontalScroll(rememberScrollState()) //rememberScrollState가 마지막에 들어가야 스크롤 가능
    ) {
        StaggeredGrid {
            for (topic in topics) {
                Chip(modifier = Modifier.padding(8.dp), text = topic)
            }
        }
    }

//    Row(modifier = modifier.horizontalScroll(rememberScrollState())) {
//        StaggeredGrid {
//            for (topic in topics) {
//                Chip(modifier = Modifier.padding(8.dp), text = topic)
//            }
//        }
//    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Compose_layout_codelabTheme {
        StaggeredGridGoogleExample()
    }
}