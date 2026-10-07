package io.github.initrc.dummy.ui.feature

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.initrc.dummy.data.Post
import io.github.initrc.dummy.data.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FeatureViewModel : ViewModel() {

    // Wrap it in a UiState to cover empty and error states.
    val posts: StateFlow<List<Post>>
        field = MutableStateFlow(emptyList())

    private val tag = javaClass.simpleName

    fun fetchPosts(id: String) {
        viewModelScope.launch {
            try {
                val service = RetrofitClient.dummyService
                posts.value = if (id.isEmpty()) {
                    service.getPosts()
                } else {
                    listOf(service.getPost(id))
                }
            } catch (e: Exception) {
                Log.e(tag, "${e.message}")
            }
        }
    }
}
