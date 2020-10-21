package x.pandoraapp.data

import x.pandoraapp.models.Rating

class DataSourceRating {
    companion object {

        fun createDataSet(): ArrayList<Rating> {
            val list = ArrayList<Rating>()
            list.add(
                Rating(
                    "Título de serviço 1!",
                    "Uma descrição",
                    3.5,
                    "https://raw.githubusercontent.com/mitchtabian/Blog-Images/master/digital_ocean.png"
                )
            )
            list.add(
                Rating(
                    "Título de serviço 2!",
                    "Uma descrição",
                    3.5,
                    "https://raw.githubusercontent.com/mitchtabian/Blog-Images/master/digital_ocean.png"
                )
            )

            list.add(
                Rating(
                    "Título de serviço 3!",
                    "Uma descrição",
                    3.5,
                    "https://raw.githubusercontent.com/mitchtabian/Blog-Images/master/digital_ocean.png"
                )
            )

            return list
        }
    }
}