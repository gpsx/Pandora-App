import com.google.gson.annotations.SerializedName


data class ProviderData (
	@SerializedName("email") val email : String,
	@SerializedName("senha") val password : String,
	@SerializedName("telefone") val number : String,
	@SerializedName("imagem") val image : String,
	@SerializedName("cep") val cep : String,
	@SerializedName("id") val id : Int,
	@SerializedName("nome") val name : String,
	@SerializedName("cpf") val cpf : String,
	@SerializedName("cnpj") val cnpj : String
)