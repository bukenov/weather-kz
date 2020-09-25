package kz.bukenov.weather.data.network.response

data class Forecast(
    val day: String,
    val date: Long,
    val low: Int,
    val high: Int,
    val text: String,
    val code: Int
)