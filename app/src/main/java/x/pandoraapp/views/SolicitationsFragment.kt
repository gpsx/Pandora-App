package x.pandoraapp.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.connection_error.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_solicitations.*
import x.pandoraapp.R
import x.pandoraapp.adapters.SolicitationRecyclerAdapter
import x.pandoraapp.controllers.SolicitationController
import x.pandoraapp.models.Solicitation
import x.pandoraapp.utils.TopSpacingItemDecorations
import x.pandoraapp.utils.observe

class SolicitationsFragment : Fragment() {

    private lateinit var solicitationAdapter: SolicitationRecyclerAdapter
    private val solicitationController by lazy { SolicitationController() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_solicitations, container, false)
    }

    private fun bindObservers() = with(solicitationController) {
        observe(error) {
            connectionError()
        }
        observe(data) {
            addDataSet(it)
        }

    }

    private fun connectionError() {
        connection_error_solicitations.visibility = View.VISIBLE
        loadingProgress_solicitations.visibility = View.GONE
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        solicitationController.getInfo()
        bindObservers()
        tryAgainButton.setOnClickListener {
            solicitationController.getInfo()
            connection_error_solicitations.visibility = View.GONE
            loadingProgress_solicitations.visibility = View.VISIBLE
        }
    }


    private fun addDataSet(data: List<Solicitation>) {
        loadingProgress_solicitations.visibility = View.GONE
        solicitationAdapter.submitList(data)
    }

    private fun initRecyclerView() {
        recycler_view_solicitations.apply {
            layoutManager = LinearLayoutManager(requireContext())
            solicitationAdapter = SolicitationRecyclerAdapter()
            adapter = solicitationAdapter
            val topSpacing = TopSpacingItemDecorations(12)
            addItemDecoration(topSpacing)
        }
    }

}