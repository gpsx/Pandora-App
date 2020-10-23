package x.pandoraapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import kotlinx.android.synthetic.main.service_card_home.view.*
import x.pandoraapp.R
import x.pandoraapp.models.Service

class HomeRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<Service> = ArrayList()

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
                holder.bind(items[position])
            }
        }
    }

    fun submitList(servicesList: List<Service>) {
        items = servicesList
        notifyDataSetChanged()
    }

    //ViewHolder
    class HomeViewHolder constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private val serviceName: TextView = itemView.service_name
        private val rating: TextView = itemView.rating
        private val distance: TextView = itemView.distancy
        private val imageService: ImageView = itemView.image_service

        fun bind(serviceHome: Service) {
            serviceName.text = serviceHome.title
            rating.text = "3,0"
            distance.text = "300"
            imageService.load(serviceHome.image)
        }

    }
}