package com.g_one_hospitalapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.g_one_hospitalapp.databinding.ActivityMainBinding
import com.g_one_hospitalapp.utilities.UserPreference
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {
    private lateinit var preference: UserPreference
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preference = UserPreference(applicationContext)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = preference.getLoginData()
        FirebaseMessaging.getInstance().subscribeToTopic(data.user?.hospital?.code.toString())

        binding.titleUName.text = preference.getLoginData().user?.hospital?.name ?: "Rumah Sakit Gadungan"

        onOpenChatButtonClicked()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.history_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.icon_history -> {
                val intent = Intent(this, PatientHistoryActivity::class.java)
                startActivity(intent)
            }
            R.id.icon_logout -> {
                preference.clearLoginData()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)

    }

    fun onOpenChatButtonClicked() {
        binding.openChatButton.setOnClickListener {
            val intent = Intent(this, MedRecordActivity::class.java)
            intent.putExtra(MedRecordActivity.CHAT_ID, "c1679c13-1a05-4384-b996-b6eafcab575a")
            startActivity(intent)
        }
    }
}