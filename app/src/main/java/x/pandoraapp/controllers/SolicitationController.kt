package x.pandoraapp.controllers

import androidx.lifecycle.MutableLiveData
import x.pandoraapp.models.Solicitation
import x.pandoraapp.repository.SolicitationRepository

class SolicitationController {

    val error: MutableLiveData<String> = MutableLiveData()

    val data: MutableLiveData<List<Solicitation>> = MutableLiveData()

    fun getInfo(id : Int) = SolicitationRepository.getSolicitations(this, id)

    fun defineErr(err: String?) {
        error.value = err
    }

    fun defineSolicitations(solicitation: List<Solicitation>) = this.data.postValue(solicitation)

    fun createSolicitationidPres(
        idPres: Int,
        idSol: Int?,
        description: String
    ) = SolicitationRepository.createSolicitation(this, idPres, idSol, description)

    fun getFilteredSolicitation(
        id : Int,
        filter : String
    ) = SolicitationRepository.getFilteredSolicitation(this, id, filter)

}