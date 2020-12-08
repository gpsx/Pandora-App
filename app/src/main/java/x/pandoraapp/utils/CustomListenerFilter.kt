package x.pandoraapp.utils

import android.app.Dialog
import android.content.Context
import android.view.View
import android.widget.Toast
import x.pandoraapp.controllers.SolicitationController

class CustomListenerFilter(
    private val dialog: Dialog,
    private val context: Context?,
    private val type: String,
    private val id : Int,
    private val view : View
) : View.OnClickListener {

    private val solicitationController by lazy { SolicitationController() }

    override fun onClick(v: View?) {

        solicitationController.getFilteredSolicitation(
            id, type
        )
        Toast.makeText(context, "Filtrado", Toast.LENGTH_SHORT).show()
        dialog.dismiss()

    }

}