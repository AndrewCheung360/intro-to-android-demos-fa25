package com.cornellappdev.chatter.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.cornellappdev.chatter.data.model.Post
import com.cornellappdev.chatter.data.model.Reactions
import com.cornellappdev.chatter.ui.components.PostCard
import com.cornellappdev.chatter.ui.components.ChatterHeader
import com.cornellappdev.chatter.ui.viewmodels.PostUiState
import com.cornellappdev.chatter.ui.viewmodels.PostViewModel

@Composable
fun PostScreen(
    postViewModel: PostViewModel = hiltViewModel()
) {
    val uiState by postViewModel.uiState.collectAsState()
    val searchQuery by postViewModel.searchQuery.collectAsState()

    PostScreenContent(
        uiState = uiState,
        searchQuery = searchQuery,
        onSearchQueryChange = { postViewModel.setSearchQuery(it) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PostScreenContent(
    uiState: PostUiState,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ChatterHeader(searchQuery, onSearchQueryChange)

        when {
            uiState.isLoading -> {
                LoadingContent()
            }

            uiState.error != null -> {
                ErrorContent(uiState.error)
            }

            uiState.posts.isEmpty() -> {
                EmptyContent()
            }

            else -> {
                PostsContent(uiState)
            }
        }
    }
}

@Composable
private fun PostsContent(uiState: PostUiState) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(uiState.posts) { post ->
            PostCard(post = post)
        }
    }
}

@Composable
private fun EmptyContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("No posts found")
    }
}

@Composable
private fun ErrorContent(error: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Error: $error",
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
private fun LoadingContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Preview(showBackground = true)
@Composable
private fun PostScreenPreview() {
    PostScreenContent(
        uiState = PostUiState(
            isLoading = false,
            posts = listOf(
                Post(
                    id = 1,
                    title = "His mother had always taught him",
                    body = "His mother had always taught him not to ever think of himself as better than others.",
                    tags = listOf("history", "american", "crime"),
                    reactions = Reactions(likes = 192, dislikes = 25),
                    views = 305,
                    userId = 121
                )
            ),
            error = null
        ),
        searchQuery = "",
        onSearchQueryChange = {}
    )
}

