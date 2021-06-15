package baltic.amadeus.task.utils

import android.util.Log
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import baltic.amadeus.task.data.entities.posts.Post
import baltic.amadeus.task.ui.posts.PostFragmentDirections

class BindingAdapter {
    companion object {

        @BindingAdapter("onPostClick")
        @JvmStatic
        fun onPostClick(postRowLayout: ConstraintLayout, post: Post) {
            postRowLayout.setOnClickListener {
                try {
                    val action =
                        PostFragmentDirections.actionPostFragmentToPostDetailsFragment(post)

                    postRowLayout.findNavController().navigate(action)
                } catch (e: Exception) {
                    Log.d("OnPostClick", e.message.toString())
                }
            }
        }
    }
}