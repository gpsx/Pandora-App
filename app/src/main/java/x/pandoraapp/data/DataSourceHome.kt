package x.pandoraapp.data
import x.pandoraapp.models.ServiceHome

class DataSourceHome {

    companion object {

        fun createDataSet(): ArrayList<ServiceHome> {
            val list = ArrayList<ServiceHome>()
            list.add(
                ServiceHome(
                    "Título de serviço 1!",
                    2.5,
                    "Está a 300m",
                    "https://raw.githubusercontent.com/mitchtabian/Blog-Images/master/digital_ocean.png"
                )
            )
            list.add(
                ServiceHome(
                    "Título de serviço 2!",
                    2.5,
                    "Está a 300m",
                    "https://raw.githubusercontent.com/mitchtabian/Blog-Images/master/digital_ocean.png"
                )
            )

            list.add(
                ServiceHome(
                    "Título de serviço 3!",
                    2.5,
                    "Está a 300m",
                    "https://raw.githubusercontent.com/mitchtabian/Blog-Images/master/digital_ocean.png"
                )
            )

            return list
        }
    }
}