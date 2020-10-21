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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        addDataSet()
    }

    private fun addDataSet() {
        //dados de verdade devem vir aqui depois
        val data = DataSourceHome.createDataSet()
        homeAdapter.submitList(data)
    }

    private fun initRecyclerView() {
        recycler_view_home.apply {
            layoutManager = LinearLayoutManager(requireContext())
            homeAdapter = HomeRecyclerAdapter()
            adapter = homeAdapter
            val topSpacing = TopSpacingItemDecorations(12)
            addItemDecoration(topSpacing)
        }

    }


}