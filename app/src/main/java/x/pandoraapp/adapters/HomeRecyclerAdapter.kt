package x.pandoraapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.service_card_home.view.*
import x.pandoraapp.R
import x.pandoraapp.models.ServiceHome

class HomeRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<ServiceHome> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HomeViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.service_card_home, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HomeViewHolder -> {
                holder.bind(items.get(position))
            }
        }
    }

    fun submitList(servicesList: List<ServiceHome>) {
        items = servicesList
    }

    //ViewHolder
    class HomeViewHolder constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        val serviceName: TextView = itemView.service_name
        val rating: TextView = itemView.rating
        val distancy: TextView = itemView.distancy
        val imageService: ImageView = itemView.image_service

        fun bind(serviceHome: ServiceHome) {
            serviceName.setText(serviceHome.title)
            rating.setText(serviceHome.rate.toString())
            distancy.setText(serviceHome.distance)

            Glide.with(itemView.context)
                .load(serviceHome.image)
                .into(imageService)
        }

    }
}