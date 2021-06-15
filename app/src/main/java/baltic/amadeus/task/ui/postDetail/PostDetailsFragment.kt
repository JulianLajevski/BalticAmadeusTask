package baltic.amadeus.task.ui.postDetail

import android.content.ContentValues.TAG
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import baltic.amadeus.task.R
import baltic.amadeus.task.data.entities.postDetails.PostDetails
import baltic.amadeus.task.utils.Constants
import baltic.amadeus.task.utils.Resource
import coil.load
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_post.*
import kotlinx.android.synthetic.main.fragment_post_details.*
import kotlinx.android.synthetic.main.fragment_post_details.progress_bar
import java.lang.System.load

@AndroidEntryPoint
class PostDetailsFragment : Fragment() {

    private val args by navArgs<PostDetailsFragmentArgs>()
    private lateinit var detailsViewModel: PostDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailsViewModel =
            ViewModelProvider(requireActivity()).get(PostDetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
    }

    private fun setupObservers() {
        detailsViewModel.start(args.result.userId)
        detailsViewModel.postDetails.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    it.data?.let { it1 -> bindPost(it1) }
                    progress_bar.visibility = View.GONE
                }
                Resource.Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                Resource.Status.LOADING -> {
                    progress_bar.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun bindPost(postDetails: PostDetails) {
        userNameTextView.text = postDetails.name
        postTitleTextView.text = args.result.title
        postBodyTextView.text = args.result.body
        Glide.with(this)
            .load(Constants.IMAGE_URL + postDetails.id)
            .placeholder(R.drawable.ic_error_placeholder)
            .into(userPicture)
    }

}