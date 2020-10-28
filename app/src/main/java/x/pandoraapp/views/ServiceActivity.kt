package x.pandoraapp.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_service.*
import x.pandoraapp.R
import x.pandoraapp.adapters.RatingRecyclerAdapter
import x.pandoraapp.utils.TopSpacingItemDecorations

class ServiceActivity : AppCompatActivity() {

    private lateinit var ratingAdapter: RatingRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)

        initRecyclerView()
        addDataSet()

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