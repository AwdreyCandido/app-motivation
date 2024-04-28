package com.example.motivation.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.motivation.MotivationConstants
import com.example.motivation.R
import com.example.motivation.SecurityPreferences
import com.example.motivation.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSave.setOnClickListener(this)

        verifyUserName()
    }

    fun verifyUserName() {
        val name = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)

        if (name != "") {
            startActivity(
                Intent(
                    this,
                    MainActivity::class.java
                )
            )
            finish()
        }
    }

    override fun onClick(view: View) {
        if (view.id == R.id.button_save) {
            handleSave()
        }
    }

    private fun handleSave() {

        val name = binding.editName.text.toString()
        if (name != "") {
            // SALVA O NOME NO SharedPreferences
            SecurityPreferences(this)
                .storeString(MotivationConstants.KEY.USER_NAME, name)
            // NAVEGA ENTRE ACTIVITIES
            startActivity(
                Intent(this, MainActivity::class.java)
            )
            // FINALIZA A ACTIVITY
            finish()
        } else {
            Toast.makeText(
                this,
                R.string.validation_mandatory_name,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}