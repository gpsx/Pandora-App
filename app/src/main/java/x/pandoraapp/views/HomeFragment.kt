package x.pandoraapp.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*
import x.pandoraapp.R
import x.pandoraapp.adapters.HomeRecyclerAdapter
import x.pandoraapp.data.DataSourceHome
import x.pandoraapp.utils.TopSpacingItemDecorations

class HomeFragment : Fragment() {

    private lateinit var homeAdapter: HomeRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        initRecyclerView();
        addDataSet();

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    private fun addDataSet() {
        //dados de verdade devem vir aqui depois
        val data = DataSourceHome.createDataSet()
        homeAdapter.submitList(data)
    }

    private fun initRecyclerView() {
        recycler_view_home.apply {
            //layoutManager = LinearLayoutManager(this@HomeFragment)
            homeAdapter = HomeRecyclerAdapter()
            adapter = homeAdapter
            val topSpacing = TopSpacingItemDecorations(12)
            addItemDecoration(topSpacing)
        }

    }
}