package x.pandoraapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rating_card_ratings.view.*
import x.pandoraapp.models.Rating
import coil.api.load
import x.pandoraapp.R

class RatingRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<Rating> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ServiceViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.rating_card_ratings, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is RatingRecyclerAdapter.ServiceViewHolder -> {
                holder.bind(items[position])
            }
        }
    }

    fun submitList(ratingList: List<Rating>) {
        items = ratingList
    }

    class ServiceViewHolder constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.rating_title
        private val note: TextView = itemView.rating
        private val avaliation: TextView = itemView.avaliation
        private val image: ImageView = itemView.image_service

        fun bind(rating: Rating) {
            title.text = rating.title
            note.text = rating.rate.toString()
            avaliation.text = rating.description
            image.load(rating.image)
        }
    }
}