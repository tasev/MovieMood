package com.two.coders.moviemood.presentation.ui.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import com.two.coders.moviemood.domain.model.AuthorDetails
import com.two.coders.moviemood.domain.model.MovieReview

@Composable
fun MovieReviewItem(movieReview: MovieReview) {
    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.Top
    ) {
        Log.d("movieReview.authorDetails?.avatarPath",""+movieReview.authorDetails?.avatarPath)
        AsyncImage(
            model = movieReview.authorDetails?.avatarPath,
            contentDescription = "author avatar",
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = movieReview.authorDetails.username.toString(),
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = movieReview.authorDetails.rating.toString(),
                style = MaterialTheme.typography.bodyLarge
            )

            Text(
                text = movieReview.content,
                style = MaterialTheme.typography.bodySmall,
                maxLines = if (expanded) Int.MAX_VALUE else 2,
                overflow = if (expanded) TextOverflow.Clip else TextOverflow.Ellipsis
            )

            if (!expanded && (movieReview.content?.length ?: 0) > 100) {
                Text(
                    text = "More",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .clickable { expanded = true }
                )
            }
        }
    }
}

@Preview
@Composable
fun MovieReviewItemPreview() {
    MovieReviewItem(
        MovieReview(
            id = "",
            author = "",
            authorDetails = AuthorDetails(avatarPath = "", name = "", rating = "", username = ""),
            content = "",
            updatedAt = ""
        )
    )
}