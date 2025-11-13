package com.cornellappdev.chatter.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cornellappdev.chatter.data.model.Post
import com.cornellappdev.chatter.data.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PostUiState(
    val isLoading: Boolean = false,
    val posts: List<Post> = emptyList(),
    val error: String? = null
)

@OptIn(FlowPreview::class)
@HiltViewModel
class PostViewModel @Inject constructor(
    private val postRepository: PostRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(PostUiState(isLoading = true))
    val uiState = _uiState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    init {
        loadPosts()

        viewModelScope.launch {
            _searchQuery
                .debounce(300)
                .distinctUntilChanged()
                .collectLatest { query ->
                    if (query.isBlank()) {
                        loadPosts()
                    } else {
                        searchPosts(query)
                    }
                }
        }
    }

    private fun loadPosts() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)

            postRepository.getAllPosts()
                .onSuccess { posts ->
                    _uiState.value = PostUiState(
                        isLoading = false,
                        posts = posts,
                        error = null
                    )
                }
                .onFailure { error ->
                    _uiState.value = PostUiState(
                        isLoading = false,
                        posts = emptyList(),
                        error = error.message ?: "Unknown error occurred"
                    )
                }
        }
    }

    private fun searchPosts(query: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)

            postRepository.searchPosts(query)
                .onSuccess { posts ->
                    _uiState.value = PostUiState(
                        isLoading = false,
                        posts = posts,
                        error = null
                    )
                }
                .onFailure { error ->
                    _uiState.value = PostUiState(
                        isLoading = false,
                        posts = emptyList(),
                        error = error.message ?: "Unknown error occurred"
                    )
                }
        }
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }
}

