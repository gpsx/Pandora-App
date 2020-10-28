package x.pandoraapp.models

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class Rating(
    @SerializedName("id") val id: Int?,
    @SerializedName("nota") val rate: BigDecimal,
    @SerializedName("avaliacao") val avaliation: String
)