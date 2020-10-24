package x.pandoraapp.models

import com.google.gson.annotations.SerializedName

data class User (
    @SerializedName("email") val email : String,
    @SerializedName("senha") val password : String,
    @SerializedName("telefone") val telephone : String,
    @SerializedName("imagem") val profilePicture : String,
    @SerializedName("cep") val cep : String,
    @SerializedName("id") val id : Int,
    @SerializedName("nome") val name : String,
    @SerializedName("cpf") val cpf : String,
    @SerializedName("cnpj") val cnpj : String
)