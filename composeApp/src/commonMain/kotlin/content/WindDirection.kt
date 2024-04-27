package dev.adambennett.doomcompose

sealed class WindDirection {
    object Right : WindDirection()
    object Left : WindDirection()
    object None : WindDirection()
}
