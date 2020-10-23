package x.pandoraapp.controllers

import androidx.lifecycle.MutableLiveData
import x.pandoraapp.models.CEP
import x.pandoraapp.models.Service
import x.pandoraapp.repository.ExampleRepository
import x.pandoraapp.repository.ServiceRepository

class ServiceController {
    val error: MutableLiveData<String> = MutableLiveData()

    val data: MutableLiveData<List<Service>> =  MutableLiveData()

    fun getInfo() = ServiceRepository.getAllServices(this)

    fun defineErr(err: String?) {
        error.value = err
    }

    fun defineServices(service: List<Service>) = this.data.postValue(service)
}