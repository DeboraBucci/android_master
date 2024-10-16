package com.example.androidmaster.settings

data class SettingsModel(
    var volume: Int,
    val bluetoothOn: Boolean,
    var darkMode: Boolean,
    val vibration: Boolean,
    var vibrationLvl: Int,
)