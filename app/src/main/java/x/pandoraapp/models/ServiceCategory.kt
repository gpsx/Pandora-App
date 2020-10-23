import com.google.gson.annotations.SerializedName

data class ServiceCategory (
	@SerializedName("idCategoria") val id : Int,
	@SerializedName("nomeServico") val name : String
)