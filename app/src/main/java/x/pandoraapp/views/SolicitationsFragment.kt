package x.pandoraapp.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_solicitations.*
import x.pandoraapp.R
import x.pandoraapp.adapters.HomeRecyclerAdapter
import x.pandoraapp.adapters.SolicitationRecyclerAdapter
import x.pandoraapp.models.Solicitation
import x.pandoraapp.utils.TopSpacingItemDecorations

class SolicitationsFragment : Fragment() {

    private lateinit var solicitationAdapter: SolicitationRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_solicitations, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }


    private fun addDataSet(data: List<Solicitation>) {
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