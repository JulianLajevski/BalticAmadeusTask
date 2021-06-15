package baltic.amadeus.task.ui.postDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import baltic.amadeus.task.R
import baltic.amadeus.task.data.entities.postDetails.PostDetails
import baltic.amadeus.task.utils.Resource
import kotlinx.android.synthetic.main.fragment_post_details.*


class PostDetailsFragment : Fragment() {

    private val args by navArgs<PostDetailsFragmentArgs>()
    private lateinit var detailsViewModel: PostDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailsViewModel = ViewModelProvider(requireActivity()).get(PostDetailsViewModel::class.java)
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

    private fun setupObservers(){
        detailsViewModel.start(args.result.userId)
        detailsViewModel.postDetails.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Resource.Status.SUCCESS -> {
                    bindPost(it.data!!)
                }
                Resource.Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                Resource.Status.LOADING -> {
                    Toast.makeText(requireContext(), "Loading...", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun bindPost(postDetails: PostDetails){
        userNameTextView.text = postDetails.name
        postTitleTextView.text = args.result.title
        postBodyTextView.text = args.result.body
    }

}