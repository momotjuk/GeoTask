package com.example.geotask.extensions

import android.content.Intent

fun Intent.newTask() = apply {
    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
}