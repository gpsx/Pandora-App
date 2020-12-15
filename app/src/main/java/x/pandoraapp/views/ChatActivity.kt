package x.pandoraapp.views

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_chat.*
import x.pandoraapp.R
import x.pandoraapp.adapters.MessagesAdapter
import x.pandoraapp.models.Message
import x.pandoraapp.utils.TopSpacingItemDecorations

class ChatActivity : AppCompatActivity() {

    private lateinit var messagesAdapter: MessagesAdapter

    val data0 = listOf(
        Message("oi", false),
        Message("ola", true),
        Message("tudo bem?", false)
    )

    val data1 = listOf(
        Message("boa tarde", false),
        Message("olá", true),
        Message("Vc está disponivel na sexta?", false),
        Message("Sim", true),
        Message("Pode vir apartir da 8h da manhã", true)
    )

    val data2 = listOf(
        Message("opa", false),
        Message("oiii", true),
        Message("vc aceita cartão?", false)
    )

    val data3 = listOf(
        Message("bom dia!", true),
        Message("meu filho quer aulas de skate.", true),
        Message("ele tem qts anos?", false)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        supportActionBar?.title = this.intent.getStringExtra("name")

        initRecyclerView(this)
        addDataSet()
    }

    private fun initRecyclerView(context: Context) {
        messages.apply {
            layoutManager = LinearLayoutManager(context)
            messagesAdapter = MessagesAdapter()
            adapter = messagesAdapter
            val topSpacing = TopSpacingItemDecorations(12)
            addItemDecoration(topSpacing)
        }

    }

    private fun addDataSet() {
        var data = listOf<Message>()
        when (this.intent.getIntExtra("id", 0)){
            0 -> data = data0
            1 -> data = data1
            2 -> data = data2
            3 -> data = data3
        }
        messagesAdapter.submitList(data)
    }
}