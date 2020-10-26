package x.pandoraapp.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.connection_error.*
import kotlinx.android.synthetic.main.fragment_home.*
import x.pandoraapp.R
import x.pandoraapp.adapters.HomeRecyclerAdapter
import x.pandoraapp.controllers.ServiceController
import x.pandoraapp.models.Service
import x.pandoraapp.utils.TopSpacingItemDecorations
import x.pandoraapp.utils.observe

class HomeFragment : Fragment() {

    private lateinit var homeAdapter: HomeRecyclerAdapter
    private val serviceController by lazy { ServiceController() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    private fun bindObservers() = with(serviceController) {
        observe(error) {
            connectionError()
        }
        observe(data) {
            addDataSet(it)
        }

    }

    private fun connectionError() {
        connection_error.visibility = View.VISIBLE
        loadingProgress.visibility = View.GONE
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        serviceController.getInfo()
        bindObservers()
        tryAgainButton.setOnClickListener {
            serviceController.getInfo()
            connection_error.visibility = View.GONE
            loadingProgress.visibility = View.VISIBLE
        }
    }

    private fun addDataSet(data: List<Service>) {
        loadingProgress.visibility = View.VISIBLE
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