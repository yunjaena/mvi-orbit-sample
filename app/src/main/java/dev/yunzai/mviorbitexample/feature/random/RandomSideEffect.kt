package dev.yunzai.mviorbitexample.feature.random

sealed class RandomSideEffect {
    data object GoToMainScreen: RandomSideEffect()
}