package hr.foi.rmai.rmai_zg_vjezbe.entities

data class TaskCourse(
    val name: String,
    val color: String
) {
    override fun toString(): String {
        return name
    }
}
