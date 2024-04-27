package content

sealed class WindDirection {
    object Right : WindDirection()
    object Left : WindDirection()
    object None : WindDirection()
}
