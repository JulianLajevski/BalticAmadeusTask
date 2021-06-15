package baltic.amadeus.task.ui.posts

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import baltic.amadeus.task.data.repository.PostRepository

class PostViewModel @ViewModelInject constructor(
    private val repository: PostRepository
) : ViewModel() {
    val posts = repository.getPosts()
}