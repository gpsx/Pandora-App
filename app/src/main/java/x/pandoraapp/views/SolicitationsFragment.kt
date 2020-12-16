package x.pandoraapp.views

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.AppCompatRatingBar
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

    private fun bindObservers() = with(solicitationController) {
        observe(error) {
            Toast.makeText(context, "Nenhuma solicitação encontrada", Toast.LENGTH_SHORT).show()
        }
        observe(data) {
            addDataSet(it)
            Toast.makeText(context, "Filtrado", Toast.LENGTH_SHORT).show()
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

        rbSolicitado.setOnClickListener { strFilter = "solicitado" }
        rbAprovado.setOnClickListener { strFilter = "aprovado" }
        rbExecucao.setOnClickListener { strFilter = "execucao" }
        rbFinalizado.setOnClickListener { strFilter = "finalizado" }

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
            solicitationController.getFilteredSolicitation(
                this.getUserId(), strFilter
            )
            bindObservers()
            alertDialog.dismiss()
        }

    }


    private fun addDataSet(data: List<Solicitation>) {
        loadingProgress_solicitations.visibility = View.GONE
        solicitationAdapter.submitList(data)
    }

    private fun initRecyclerView() {
        recycler_view_solicitations.apply {
            layoutManager = LinearLayoutManager(requireContext())
            solicitationAdapter = SolicitationRecyclerAdapter {
                startAvaliationPopUp(it)
            }
            adapter = solicitationAdapter
            val topSpacing = TopSpacingItemDecorations(12)
            addItemDecoration(topSpacing)
        }
    }

    private fun startAvaliationPopUp(id: Int) {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.popup_form_solicitation, null)
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setView(view)

        val etAvaliacao = (view.findViewById(R.id.et_descr_avaliation) as EditText)
        val ratingBar = (view.findViewById(R.id.ratingBar) as RatingBar)
        var rating: Int = 0;

        ratingBar.setOnRatingBarChangeListener { ratingBar: RatingBar, fl: Float, b: Boolean ->
            rating = ratingBar.numStars
        }

        alertDialogBuilder.setCancelable(false)
            .setPositiveButton(
                "AVALIAR"
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
            Toast.makeText(context, "Avaliação enviada ao prestador!", Toast.LENGTH_SHORT).show()
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