package data

data class Speaker(
    val id: Int,
    val avatar: String,
    val name: String,
    val info: String,
    val theme: String,
    val description: String,
    val time: String,
    val directionType: DirectionType,
    val fromKolesa: Boolean
)