package x.pandoraapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.CircleCropTransformation
import kotlinx.android.synthetic.main.conversation_card.view.*
import x.pandoraapp.R
import x.pandoraapp.models.Conversation

class ConversationAdapter(
    private val onItemClicked: (Conversation) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<Conversation> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder =  LayoutInflater.from(parent.context).inflate(R.layout.conversation_card, parent, false)
        return ConversationViewHolder(viewHolder){
            onItemClicked(items[it])
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ConversationViewHolder -> {
                holder.bind(items[position])
            }

        }
    }

    fun submitList(conversationList: List<Conversation>) {
        items = conversationList
        notifyDataSetChanged()
    }

    //ViewHolder
    class ConversationViewHolder constructor(
        itemView: View,
        onItemClicked: (Int) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        private val name: TextView = itemView.name
        private val profilePicture: ImageView = itemView.profileImage

        init{
            itemView.setOnClickListener{
                onItemClicked(this.position)
            }
        }

        fun bind(conversation: Conversation) {
            name.text = conversation.name
            profilePicture.load(conversation.profileImage){
                crossfade(true)
                placeholder(R.drawable.ic_user_profile)
                transformations(CircleCropTransformation())
            }
        }

    }
}