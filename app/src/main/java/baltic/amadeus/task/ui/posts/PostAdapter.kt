package baltic.amadeus.task.ui.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import baltic.amadeus.task.data.entities.posts.Post
import baltic.amadeus.task.databinding.ItemPostBinding

class PostAdapter : RecyclerView.Adapter<PostAdapter.MyViewHolder>() {

    private val items = ArrayList<Post>()

    fun setItems(items: ArrayList<Post>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostAdapter.MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: PostAdapter.MyViewHolder, position: Int) {
        return holder.bind(items[position])
    }

    class MyViewHolder(private val binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) {
            binding.result = post
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemPostBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }
    }
}