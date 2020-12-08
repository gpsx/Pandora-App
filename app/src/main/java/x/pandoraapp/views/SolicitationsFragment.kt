package x.pandoraapp.views

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.connection_error.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_solicitations.*
import x.pandoraapp.R
import x.pandoraapp.adapters.SolicitationRecyclerAdapter
import x.pandoraapp.constraints.USER_ID_BUNDLE
import x.pandoraapp.controllers.SolicitationController
import x.pandoraapp.models.Solicitation
import x.pandoraapp.models.User
import x.pandoraapp.repository.SharedPreferencesRepositoryImplementation
import x.pandoraapp.utils.*

class SolicitationsFragment : Fragment() {

    private lateinit var filter: ImageView
    private lateinit var rbSolicitado: RadioButton
    private lateinit var rbAprovado: RadioButton
    private lateinit var rbExecucao: RadioButton
    private lateinit var rbFinalizado: RadioButton
    private lateinit var solicitationAdapter: SolicitationRecyclerAdapter
    private val solicitationController by lazy { SolicitationController() }
    private val pref by lazy { SharedPreferencesRepositoryImplementation<User>(requireContext()) }
    private var strFilter: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_solicitations, container, false)
        filter = view.findViewById(R.id.icon_filter) as ImageView
        return view
    }

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            val checked = view.isChecked
            when (view.getId()) {
                R.id.rb_solicitado ->
                    if (checked) {
                        strFilter = "SOLICITADO"
                    }
                R.id.rb_aprovado ->
                    if (checked) {
                        strFilter = "APROVADO"
                    }
                R.id.rb_execucao ->
                    if (checked) {
                        strFilter = "EXECUCAO"
                    }
                R.id.rb_finalizado ->
                    if (checked) {
                        strFilter = "FINALIZADO"
                    }
            }
        }
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

        filter.setOnClickListener {
            startPopUp()
        }

        solicitationController.getInfo(this.getUserId())
        bindObservers()
        tryAgainButton.setOnClickListener {
            solicitationController.getInfo(this.getUserId())
            connection_error_solicitations.visibility = View.GONE
            loadingProgress_solicitations.visibility = View.VISIBLE
        }
    }

    private fun startPopUp() {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.popup_solicitations_filter, null)
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setView(view)

        rbSolicitado = (view.findViewById(R.id.rb_solicitado) as RadioButton?)!!
        rbAprovado = (view.findViewById(R.id.rb_aprovado) as RadioButton?)!!
        rbExecucao = (view.findViewById(R.id.rb_execucao) as RadioButton?)!!
        rbFinalizado = (view.findViewById(R.id.rb_finalizado) as RadioButton?)!!

        rbSolicitado.setOnClickListener { onRadioButtonClicked(rbSolicitado) }
        rbAprovado.setOnClickListener { onRadioButtonClicked(rbAprovado) }
        rbExecucao.setOnClickListener { onRadioButtonClicked(rbExecucao) }
        rbFinalizado.setOnClickListener { onRadioButtonClicked(rbFinalizado) }

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
        theButton.setOnClickListener(
            CustomListenerFilter(
                alertDialog, context, strFilter, this.getUserId(), view
            )
        )

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

    private fun getUserId(): Int {
        var id = pref.getValue(USER_ID_BUNDLE, User::class.java)?.id
        if (id == 0) {
            id = 7
        } else if (id == null) {
            id = 7
        }
        return id;
    }

}