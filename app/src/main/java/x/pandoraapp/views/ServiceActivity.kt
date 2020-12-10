package x.pandoraapp.views

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import coil.api.load
import kotlinx.android.synthetic.main.activity_service.*
import x.pandoraapp.R
import x.pandoraapp.adapters.RatingRecyclerAdapter
import x.pandoraapp.controllers.ServiceController
import x.pandoraapp.controllers.SolicitationController
import x.pandoraapp.data.DataSourceRating
import x.pandoraapp.utils.CustomListener
import x.pandoraapp.utils.TopSpacingItemDecorations
import x.pandoraapp.utils.observe

class ServiceActivity : AppCompatActivity() {

    private val serviceController by lazy { ServiceController() }
    private var id: Int = 0;
    private lateinit var ratingAdapter: RatingRecyclerAdapter
    private lateinit var arrow: ImageView
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_service)
        loadingProgressService.visibility = View.VISIBLE

        arrow = findViewById<ImageView>(R.id.left_arrow)
        arrow.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }

        button = findViewById<Button>(R.id.btn_contratar)
        button.setOnClickListener {
            startPopUp()
        }

        id = this.intent.getIntExtra("id", 0)
        serviceController.getServiceById(id)

        bindObservers()
        initRecyclerView()
        addDataSet()
    }

    private fun startPopUp() {
        val inflater = LayoutInflater.from(this)
        val view = inflater.inflate(R.layout.popup_form_solicitation, null)
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setView(view)

        alertDialogBuilder.setCancelable(false)
            .setPositiveButton(
                "SOLICITAR"
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
            CustomListener(
                alertDialog, this, id, view
            )
        )

    }

    private fun bindObservers() = with(serviceController) {
        observe(error) {
            connectionError()
        }
        observe(single) {
            sv_title.text = it.title
            sv_description.text = it.description
            val imageService: ImageView = img_service
            imageService.load(it.image)
            loadingProgressService.visibility = View.GONE
        }
    }

    private fun connectionError() {
        btn_contratar.visibility = View.GONE
        connection_error_service.visibility = View.VISIBLE
        loadingProgressService.visibility = View.GONE
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
        val data = DataSourceRating.createDataSet()
        ratingAdapter.submitList(data)
    }

}