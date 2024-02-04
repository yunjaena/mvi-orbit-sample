package dev.yunzai.mviorbitexample.feature.random

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RandomState(
    val maxNumber: Int = 0,
    val randomNumber: Int = 0
): Parcelable