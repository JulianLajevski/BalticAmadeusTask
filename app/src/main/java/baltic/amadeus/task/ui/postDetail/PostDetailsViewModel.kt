package baltic.amadeus.task.ui.postDetail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import baltic.amadeus.task.data.entities.postDetails.PostDetails
import baltic.amadeus.task.data.repository.PostRepository
import baltic.amadeus.task.utils.Resource


class PostDetailsViewModel @ViewModelInject constructor(
    private val postRepository: PostRepository
) : ViewModel() {

    private val _id = MutableLiveData<Int>()

    private val _postDetails = _id.switchMap { id ->
        postRepository.getPostDetails(id)
    }
    val postDetails: LiveData<Resource<PostDetails>> = _postDetails

    fun start(id: Int) {
        _id.value = id
    }

}