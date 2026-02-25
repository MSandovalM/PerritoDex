package com.sanddev.doggodex

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.sanddev.doggodex.auth.LoginActivity
import com.sanddev.doggodex.databinding.ActivityMainBinding
import com.sanddev.doggodex.doglist.DogListActivity
import com.sanddev.doggodex.models.User
import com.sanddev.doggodex.settings.SettingsActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val user = User.getLoggedInUser(this)

        if (user == null) {
            startLogInActivity()
            return
        }

        binding.settingsFab.setOnClickListener {
            startSettingsActivity()
        }

        binding.takePhotoFab.setOnClickListener {
            openCameraActivity()
        }

        binding.listDogFab.setOnClickListener {
            startDogListActivity()
        }

    }

    private fun startDogListActivity() {
        val intent = Intent(this, DogListActivity::class.java)
        startActivity(intent)
    }

    private fun openCameraActivity() {
        // TODO: CameraX Module
    }

    private fun startSettingsActivity() {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }

    private fun startLogInActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
    }
}