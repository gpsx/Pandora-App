package x.pandoraapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.CircleCropTransformation
import kotlinx.android.synthetic.main.messages.view.*
import x.pandoraapp.R
import x.pandoraapp.models.Conversation
import x.pandoraapp.models.Message

class MessagesAdapter()
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<Message> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder =  LayoutInflater.from(parent.context).inflate(R.layout.messages, parent, false)
        return MessageViewHolder(viewHolder){}
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MessageViewHolder -> {
                holder.bind(items[position])
            }

        }
    }

    fun submitList(conversationList: List<Message>) {
        items = conversationList
        notifyDataSetChanged()
    }

    //ViewHolder
    class MessageViewHolder constructor(
        itemView: View,
        onItemClicked: (Int) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        private val user: TextView = itemView.user
        private val other: TextView = itemView.other

        init{
            itemView.setOnClickListener{
                onItemClicked(this.position)
            }
        }

        fun bind(message: Message) {
            if (message.isFromUser){
                user.visibility = View.VISIBLE
                user.text = message.text
            } else {
                other.visibility = View.VISIBLE
                other.text = message.text
            }
        }

    }
}