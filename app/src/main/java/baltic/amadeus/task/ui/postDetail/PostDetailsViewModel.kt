package baltic.amadeus.task.ui.postDetail

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import baltic.amadeus.task.data.repository.PostRepository

class PostDetailsViewModel @ViewModelInject constructor(
        private val postRepository: PostRepository
) : ViewModel() {
}