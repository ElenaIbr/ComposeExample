package com.example.composeexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import com.example.composeexample.data.BookList
import com.example.composeexample.model.Book
import com.example.composeexample.ui.theme.ComposeExampleTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeExampleTheme{
                DisplayNotes {
                    startActivity(InfoActivity.intent(this,it))
                }
            }
        }
    }
}

@Composable
fun DisplayNotes(item: (Book) -> Unit) {
    val books = remember { BookList.books }
    Box(Modifier.background(Color.Black)){
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 8.dp,vertical = 4.dp),
        ) {
            items(
                items = books,
                itemContent = {
                    BookList(book = it, item)
                }
            )
        }
    }

}

@Composable
fun BookList(book: Book, selectedItem: (Book) -> Unit) {
    Card(
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth(),
        elevation = 3.dp,
        shape = RoundedCornerShape(corner = CornerSize(10.dp))
    ) {
        Row(
            modifier = Modifier
                .padding(3.dp)
                .fillMaxWidth()
                .clickable { selectedItem(book) },
            verticalAlignment = Alignment.CenterVertically
        ) {
            BookImage(book = book)
            Column {
                Text(text = book.title, style = MaterialTheme.typography.h5)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = book.content,
                    style = MaterialTheme.typography.body1,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = book.year, style = MaterialTheme.typography.h5)
                }

            }

        }

    }
}

@Composable
fun BookImage(book: Book) {
    Image(
        painter = painterResource(id = book.imageId),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(4.dp)
            .height(140.dp)
            .width(100.dp)
            .clip(RoundedCornerShape(corner = CornerSize(10.dp)))

    )
}
