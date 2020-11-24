package x.pandoraapp.controllers

import androidx.lifecycle.MutableLiveData
import x.pandoraapp.models.CEP
import x.pandoraapp.models.Service
import x.pandoraapp.repository.ExampleRepository
import x.pandoraapp.repository.ServiceRepository

class ServiceController {
    val error: MutableLiveData<String> = MutableLiveData()

    val data: MutableLiveData<List<Service>> = MutableLiveData()

    val single: MutableLiveData<Service> = MutableLiveData()

    fun getInfo() = ServiceRepository.getAllServices(this)

    fun getServicesByWord(filtro: String) = ServiceRepository.getServiceByWord(this, filtro)

    fun getServiceById(id : Int) = ServiceRepository.getServiceById(this, id)

    fun defineErr(err: String?) {
        error.value = err
    }

    fun defineServices(service: List<Service>) = this.data.postValue(service)

    fun defineService(service: Service) = this.single.postValue(service)
}