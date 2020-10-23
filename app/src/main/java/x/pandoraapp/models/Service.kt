package x.pandoraapp.models

import ProviderData
import ServiceCategory
import com.google.gson.annotations.SerializedName

data class Service (
	@SerializedName("idServico") val id : Int,
	@SerializedName("descricao") val description : String,
	@SerializedName("titulo") val title : String,
	@SerializedName("imagem") val image : String,
	@SerializedName("fkPrestador") val providerData : ProviderData,
	@SerializedName("fkCategoriaServico") val category : ServiceCategory
)