package com.example.composeexample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeexample.model.Book

class InfoActivity : ComponentActivity() {
    companion object{
        private const val BookItem = "bookid"
        fun intent(context: Context, tvShow: Book)=
            Intent(context,InfoActivity::class.java).apply {
                putExtra(BookItem,tvShow)
            }
    }

    private val book : Book by lazy {
        intent?.getSerializableExtra(BookItem) as Book
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViewMoreInfo(book = book)
        }
    }
}

@Composable
fun ViewMoreInfo(book: Book) {
    val scrollState = rememberScrollState()
    Box(Modifier.background(Color(0xFFE6E2E2))){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .verticalScroll(scrollState)
                .padding(5.dp)
        ) {
            Text(
                text = book.title,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = book.content,
                fontSize = 22.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = book.year,
                fontSize = 20.sp
            )
        }
    }
}






