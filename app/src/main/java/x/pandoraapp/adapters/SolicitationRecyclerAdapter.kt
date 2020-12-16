package x.pandoraapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import kotlinx.android.synthetic.main.solicitation_card.view.*
import x.pandoraapp.R
import x.pandoraapp.models.Service
import x.pandoraapp.models.Solicitation

class SolicitationRecyclerAdapter(
    private val onItemClicked: (Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<Solicitation> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder =
            LayoutInflater.from(parent.context).inflate(R.layout.solicitation_card, parent, false)
        return SolicitationViewHolder(viewHolder) {
            items[it].id?.let { it1 -> onItemClicked(it1) }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SolicitationViewHolder -> {
                holder.bind(items[position])
            }
        }
    }

    fun submitList(solicitationsList: List<Solicitation>) {
        items = solicitationsList
        notifyDataSetChanged()
    }


    class SolicitationViewHolder constructor(
        itemView: View,
        onItemClicked: (Int) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.solicitation_title
        private val status: TextView = itemView.status
        private val image: ImageView = itemView.image_solicitation

        init {
            itemView.setOnClickListener {
                onItemClicked(it.id);
            }
        }

        fun bind(solicitation: Solicitation) {
            title.text = solicitation.description;
            status.text = solicitation.status

            image.load(solicitation.provider.image)
        }

    }


}