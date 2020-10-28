package x.pandoraapp.controllers

import androidx.lifecycle.MutableLiveData
import x.pandoraapp.models.Solicitation
import x.pandoraapp.repository.SolicitationRepository

class SolicitationController {

    val error: MutableLiveData<String> = MutableLiveData()

    val data: MutableLiveData<List<Solicitation>> = MutableLiveData()

    fun getInfo() = SolicitationRepository.getSolicitations(this)

    fun defineErr(err: String?) {
        error.value = err
    }

    fun defineSolicitations(solicitation: List<Solicitation>) = this.data.postValue(solicitation)

}