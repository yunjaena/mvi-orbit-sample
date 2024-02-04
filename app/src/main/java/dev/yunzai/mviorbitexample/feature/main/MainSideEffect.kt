package dev.yunzai.mviorbitexample.feature.main

sealed class MainSideEffect {
    data class NavigateToRandomScreen(val number: Int) : MainSideEffect()
}