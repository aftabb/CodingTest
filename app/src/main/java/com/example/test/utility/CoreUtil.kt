package com.example.test.utility

import com.example.test.R

class CoreUtil {
    companion object {
        const val socketUrl = "ws://city-ws.herokuapp.com/"
        fun getColorCode(value: Double): Int {
            when (value) {
                in 0.0..50.0 -> {
                    return R.color.good
                }
                in 50.0..100.0 -> {
                    return R.color.satisfactory
                }
                in 100.0..200.0 -> {
                    return R.color.moderate
                }
                in 200.0..300.0 -> {
                    return R.color.poor
                }
                in 300.0..400.0 -> {
                    return R.color.verypoor
                }
                in 400.0..500.0 -> {
                    return R.color.severe
                }
                else -> return R.color.white
            }
        }

        fun getGredientCode(value: Double): Int {
            when (value) {
                in 0.0..50.0 -> {
                    return R.drawable.gredient_good
                }
                in 50.0..100.0 -> {
                    return R.drawable.gredient_satisfactory
                }
                in 100.0..200.0 -> {
                    return R.drawable.gredient_moderate
                }
                in 200.0..300.0 -> {
                    return R.drawable.gredient_poor
                }
                in 300.0..400.0 -> {
                    return R.drawable.gredient_verypoor
                }
                in 400.0..500.0 -> {
                    return R.drawable.gredient_sever
                }
                else -> return R.color.white
            }
        }
    }
}