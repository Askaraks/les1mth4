package com.example.les1mth4.ui.activity

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.example.les1mth4.App
import com.example.les1mth4.R
import com.example.les1mth4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var controller: NavController

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNav.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, REQUEST_CODE)
        }

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.onBoardFragment) as NavHostFragment
        controller = navHostFragment.navController

        // Set up the bottom navigation view with the nav controller
        binding.bottomNav.setupWithNavController(controller)

        // Hide the bottom navigation view when the user is on the onBoardFragment
        controller.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.onBoardFragment) {
                binding.bottomNav.visibility = View.GONE
            } else {
                binding.bottomNav.visibility = View.VISIBLE
            }
        }

        if (!App.pref.isBoardShow()) {
            controller.navigate(R.id.onBoardFragment)
        }

        binding.bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.noteFragment -> {
                    controller.navigate(R.id.noteFragment)
                    true
                }
                R.id.navigation_contact -> {
                    controller.navigate(R.id.contactFragment)
                    true
                }
                R.id.navigation_profile -> {
                    controller.navigate(R.id.profileFragment)
                    true
                }
                else -> false
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            val imageUri = data.data
            Glide.with(this)
                .load(imageUri)
            // Обработка выбранной фотографии
        }


    }
    companion object {
        private const val REQUEST_CODE = 1
    }
}