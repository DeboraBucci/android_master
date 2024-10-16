package com.example.androidmaster.settings

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.androidmaster.R
import com.example.androidmaster.databinding.ActivitySettingsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingsActivity : AppCompatActivity() {

    companion object {
        const val VOLUME_LVL = "volume_lvl"
        const val BLUETOOTH_ON = "bluetooth_on"
        const val DARK_MODE_ON = "dark_mode_on"
        const val VIBRATION_ON = "vibration_on"
        const val VIBRATION_LVL = "vibration_lvl"
    }


    private lateinit var binding: ActivitySettingsBinding
    private var firstTime: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySettingsBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        CoroutineScope(Dispatchers.IO).launch {
            getSettings()
                .filter { firstTime }
                .collect { settings ->

                    if (settings != null) {

                        runOnUiThread {
                            binding.rsVolume.setValues(settings.volume.toFloat())
                            binding.rsVibrationLvl.setValues(settings.vibrationLvl.toFloat())

                            binding.smBluetooth.isChecked = settings.bluetoothOn
                            binding.smDarkTheme.isChecked = settings.darkMode
                            binding.smVibration.isChecked = settings.vibration

                            firstTime = !firstTime
                        }
                    }
                }
        }

        initUI()
    }

    private fun initUI() {
        binding.rsVolume.addOnChangeListener { _, value, _ ->

            if (value.toInt() == 0) {
                binding.ivVolume.setImageResource(R.drawable.ic_mute)

            } else if (value.toInt() == 100) {
                binding.ivVolume.setImageResource(R.drawable.ic_volume)

            } else {
                binding.ivVolume.setImageResource(R.drawable.ic_volume_down)
            }

            CoroutineScope(Dispatchers.IO).launch {
                saveVolume(VOLUME_LVL, value.toInt())
            }
        }

        binding.rsVibrationLvl.addOnChangeListener { _, value, _ ->
            CoroutineScope(Dispatchers.IO).launch {
                saveVolume(VIBRATION_LVL, value.toInt())
            }
        }

        binding.smBluetooth.setOnCheckedChangeListener { _, isChecked ->
            CoroutineScope(Dispatchers.IO).launch {
                saveOptions(BLUETOOTH_ON, isChecked)
            }
        }

        binding.smDarkTheme.setOnCheckedChangeListener { _, isChecked ->

            if (isChecked) {
                enableDarkMode()
                binding.ivThemeIcon.setImageResource(R.drawable.ic_dark_mode)

            } else {
                disableDarkMode()
                binding.ivThemeIcon.setImageResource(R.drawable.ic_light_mode)
            }

            CoroutineScope(Dispatchers.IO).launch {
                saveOptions(DARK_MODE_ON, isChecked)
            }
        }

        binding.smVibration.setOnCheckedChangeListener { _, isChecked ->
            CoroutineScope(Dispatchers.IO).launch {
                saveOptions(VIBRATION_ON, isChecked)
            }
        }
    }

    private suspend fun saveVolume(key: String, value: Int) {
        dataStore.edit { preferences ->
            preferences[intPreferencesKey(key)] = value
        }
    }

    private suspend fun saveOptions(key: String, value: Boolean) {
        dataStore.edit { preferences ->
            preferences[booleanPreferencesKey(key)] = value
        }
    }

    private fun getSettings(): Flow<SettingsModel?> {
        return dataStore.data.map { preferences ->

            SettingsModel(
                volume = preferences[intPreferencesKey(VOLUME_LVL)] ?: 50,
                bluetoothOn = preferences[booleanPreferencesKey(BLUETOOTH_ON)] ?: false,
                darkMode = preferences[booleanPreferencesKey(DARK_MODE_ON)] ?: false,
                vibration = preferences[booleanPreferencesKey(VIBRATION_ON)] ?: true,
                vibrationLvl = preferences[intPreferencesKey(VIBRATION_LVL)] ?: 50,
            )
        }
    }

    private fun enableDarkMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        delegate.applyDayNight()
    }

    private fun disableDarkMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        delegate.applyDayNight()
    }

}