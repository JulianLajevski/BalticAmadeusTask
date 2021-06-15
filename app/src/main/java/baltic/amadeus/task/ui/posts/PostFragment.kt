package baltic.amadeus.task.ui.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import baltic.amadeus.task.R
import baltic.amadeus.task.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_post.*

@AndroidEntryPoint
class PostFragment : Fragment() {

    private lateinit var viewModel: PostViewModel
    private val adapter by lazy { PostAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())
            .get(PostViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
        swipeRefreshLayout.setOnRefreshListener(refreshListener);
    }

    private val refreshListener = SwipeRefreshLayout.OnRefreshListener {
        setupObservers()
        Toast.makeText(
            requireContext(),
            "Refreshed!",
            Toast.LENGTH_SHORT
        ).show()
        swipeRefreshLayout.isRefreshing = false
    }

    private fun setupRecyclerView() {
        mainScreenRecyclerView.adapter = adapter
        mainScreenRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setupObservers() {
        viewModel.posts.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    progress_bar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) adapter.setItems(ArrayList(it.data))
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->{
                    progress_bar.visibility = View.VISIBLE
                }

            }
        })
    }
}
