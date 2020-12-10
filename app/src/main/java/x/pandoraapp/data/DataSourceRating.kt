package x.pandoraapp.data

import x.pandoraapp.models.Rating

class DataSourceRating {
    companion object {

        fun createDataSet(): ArrayList<Rating> {
            val list = ArrayList<Rating>()
            list.add(
                Rating(
                    2,
                    5.toBigDecimal(),
                    "Ótimo profissional!"
                )
            )

            list.add(
                Rating(
                    3,
                    2.toBigDecimal(),
                    "Deixou a desejar, mas foi no horário marcado."
                )
            )

            return list
        }
    }
}