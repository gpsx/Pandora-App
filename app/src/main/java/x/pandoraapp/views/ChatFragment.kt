package x.pandoraapp.views

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_chat.*
import kotlinx.android.synthetic.main.fragment_home.*
import x.pandoraapp.R
import x.pandoraapp.adapters.ConversationAdapter
import x.pandoraapp.adapters.HomeRecyclerAdapter
import x.pandoraapp.models.Conversation
import x.pandoraapp.models.Service
import x.pandoraapp.utils.TopSpacingItemDecorations

class ChatFragment : Fragment() {

    private lateinit var conversationAdapter: ConversationAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        addDataSet()
    }

    private fun initRecyclerView() {
        convesations.apply {
            layoutManager = LinearLayoutManager(requireContext())
            conversationAdapter = ConversationAdapter {
                val intent = Intent(context, ChatActivity::class.java).apply {
                    putExtra("profile", it.profileImage)
                    putExtra("name", it.name)
                    putExtra("id", it.id)
                }
                startActivity(intent)
            }
            adapter = conversationAdapter
            val topSpacing = TopSpacingItemDecorations(12)
            addItemDecoration(topSpacing)
        }

    }

    private fun addDataSet() {
        val data = listOf(
            Conversation(0,"https://exitoina.uol.com.br/media/_versions/homem_pateta_facebook_widelg.jpg","Joelson"),
            Conversation(1,"https://exitoina.uol.com.br/media/_versions/homem_pateta_facebook_widelg.jpg","Eduardo"),
            Conversation(2,"https://exitoina.uol.com.br/media/_versions/homem_pateta_facebook_widelg.jpg","Roberto"),
            Conversation(3,"https://exitoina.uol.com.br/media/_versions/homem_pateta_facebook_widelg.jpg","Emanuela")
        )
        conversationAdapter.submitList(data)
    }

}