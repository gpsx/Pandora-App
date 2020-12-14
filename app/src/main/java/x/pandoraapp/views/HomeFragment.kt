package x.pandoraapp.views

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import androidx.fragment.app.Fragment
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

    private val serviceController by lazy { ServiceController() }
    private lateinit var source: ImageView
    private lateinit var filter: ImageView
    private lateinit var homeAdapter: HomeRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        source = view.findViewById(R.id.searchButton) as ImageView
        filter = view.findViewById(R.id.icon_filter) as ImageView
        return view
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

        source.setOnClickListener {
            val filter = searchEditText.text.toString()
            serviceController.getServicesByWord(filter)
            bindObservers()
            tryAgainButton.setOnClickListener {
                serviceController.getServicesByWord(filter)
            }
        }


        filter.setOnClickListener {
            startPopUp();
        }
    }

    private fun startPopUp() {
        var category = 77
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.popup_home_filter, null)

        val rb77 = view.findViewById<RadioButton>(R.id.rb_77)
        val rb80 = view.findViewById<RadioButton>(R.id.rb_80)

        rb77.setOnClickListener { category = 77 }
        rb80.setOnClickListener { category = 80 }

        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setView(view)

        alertDialogBuilder.setCancelable(false)
            .setPositiveButton(
                "FILTRAR"
            ) { dialogInterface: DialogInterface, i: Int ->
                //onClick
            }
            .setNegativeButton(
                "CANCELAR"
            ) { dialogInterface: DialogInterface, i: Int ->
                dialogInterface.cancel()
            }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
        val theButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE)
        theButton.setOnClickListener {


            serviceController.getServicesByCategory(category)
            bindObservers()
            tryAgainButton.setOnClickListener {
                serviceController.getServicesByCategory(category)
            }

            alertDialog.dismiss()

        }

    }

    private fun addDataSet(data: List<Service>) {
        loadingProgress.visibility = View.GONE
        homeAdapter.submitList(data)
    }

    private fun initRecyclerView() {
        recycler_view_home.apply {
            layoutManager = LinearLayoutManager(requireContext())
            homeAdapter = HomeRecyclerAdapter { service ->
                val intent = Intent(context, ServiceActivity::class.java).apply {
                    putExtra("id", service.id)
                    putExtra("idPrestador", service.providerData.id)
                }
                startActivity(intent);
            }
            adapter = homeAdapter
            val topSpacing = TopSpacingItemDecorations(12)
            addItemDecoration(topSpacing)
        }

    }


}