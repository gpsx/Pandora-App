package x.pandoraapp.views

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import coil.api.load
import kotlinx.android.synthetic.main.activity_service.*
import kotlinx.android.synthetic.main.service_card_home.*
import kotlinx.android.synthetic.main.service_card_home.view.*
import x.pandoraapp.R
import x.pandoraapp.adapters.RatingRecyclerAdapter
import x.pandoraapp.controllers.ServiceController
import x.pandoraapp.utils.TopSpacingItemDecorations
import x.pandoraapp.utils.observe


class ServiceActivity : AppCompatActivity() {

    private val serviceController by lazy { ServiceController() }
    private var id: Int = 0;
    private lateinit var ratingAdapter: RatingRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)

        initPage()

//        initRecyclerView()
//        addDataSet()

    }

    private fun initPage(){
        id = this.intent.getIntExtra("id", 0)
        serviceController.getServiceById(id)
        bindObservers()
    }

    private fun bindObservers() = with(serviceController) {
        observe(error) {
            connectionError()
        }
        observe(single) {
            println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAA")
            val service = it
            sv_title.text = service.title
            sv_description.text = service.description
            val imageService: ImageView = img_service
            imageService.load(service.image)
        }
    }

    private fun connectionError() {
        print("BBBBBBBBBBBBBBBBBBB")
    }

    private fun initRecyclerView() {
        recycler_view_ratings.apply {
            layoutManager = LinearLayoutManager(this@ServiceActivity)
            ratingAdapter = RatingRecyclerAdapter()
            adapter = ratingAdapter
            val topSpacing = TopSpacingItemDecorations(12)
            addItemDecoration(topSpacing)
        }
    }

    private fun addDataSet() {
//        val data = DataSourceRating.createDataSet()
//        ratingAdapter.submitList(data)
    }
}