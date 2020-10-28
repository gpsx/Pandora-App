package x.pandoraapp.models

import ProviderData
import com.google.gson.annotations.SerializedName

data class Solicitation(
    @SerializedName("id") val id: Int?,
    @SerializedName("status") val status: String,
    @SerializedName("descricao") val description: String,
    @SerializedName("fkSolicitante") val user: User,
    @SerializedName("fkPrestador") val provider: ProviderData,
    @SerializedName("fkAvaliacao") val rating: Rating
)