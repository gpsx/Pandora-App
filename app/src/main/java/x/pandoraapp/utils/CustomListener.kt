package x.pandoraapp.utils

import android.app.Dialog
import android.content.Context
import android.view.View
import android.widget.EditText
import android.widget.Toast
import x.pandoraapp.R
import x.pandoraapp.constraints.USER_ID_BUNDLE
import x.pandoraapp.controllers.SolicitationController
import x.pandoraapp.models.User
import x.pandoraapp.repository.SharedPreferencesRepositoryImplementation

class CustomListener(
    private val dialog: Dialog,
    private val context: Context,
    private val id: Int,
    private val view : View
) : View.OnClickListener {

    private val solicitationController by lazy { SolicitationController() }
    private val pref by lazy { SharedPreferencesRepositoryImplementation<User>(context) }

    override fun onClick(v: View?) {

        val description = view.findViewById<EditText>(R.id.pop_description).text.toString()

        if (description.isEmpty()) {
            Toast.makeText(context, "Descrição não deve estar vazia", Toast.LENGTH_SHORT).show()
            return;
        }

        var idSol = pref.getValue(USER_ID_BUNDLE, User::class.java)?.id
        if (idSol == 0) {
            idSol = 7
        }else if(idSol == null){
            idSol = 7
        }

        solicitationController.createSolicitationidPres(
            id,
            idSol,
            description
        )

        Toast.makeText(context, R.string.toastSend, Toast.LENGTH_SHORT).show()
        dialog.dismiss()
    }

}