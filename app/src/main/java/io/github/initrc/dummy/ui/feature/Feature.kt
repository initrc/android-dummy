package io.github.initrc.dummy.ui.feature

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.initrc.dummy.data.Post
import io.github.initrc.dummy.ui.theme.DummyTheme

@Composable
fun Feature(
    modifier: Modifier = Modifier,
    featureViewModel: FeatureViewModel = viewModel(),
) {
    val posts by featureViewModel.posts.collectAsStateWithLifecycle()
    Feature(
        modifier = modifier,
        posts = posts,
        onLoadClicked = featureViewModel::fetchPosts
    )
}

@Composable
fun Feature(
    modifier: Modifier = Modifier,
    posts: List<Post>,
    onLoadClicked: (String) -> Unit,
) {
    val input = rememberTextFieldState()
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(all = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TextField(
                modifier = Modifier.weight(1f),
                state = input
            )
            Button(
                modifier = Modifier,
                onClick = { onLoadClicked(input.text.toString()) }
            ) {
                Text("Load")
            }
        }
        LazyColumn(
            modifier = Modifier.fillMaxWidth().weight(1f)
        ) {
            itemsIndexed(
                items = posts,
                key = { _, post -> post.id },
            ) { index, post ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                ) {
                    Text(
                        text = post.title,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = post.body
                    )
                }
                if (index < posts.lastIndex) {
                    HorizontalDivider()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FeaturePreview() {
    val posts = (1..20).map { i ->
        Post(i, i, "title $i", "body i ".repeat(10))
    }
    DummyTheme {
        Feature(
            modifier = Modifier,
            posts = posts,
            onLoadClicked = {},
        )
    }
}
