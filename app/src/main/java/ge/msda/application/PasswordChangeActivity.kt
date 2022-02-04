package ge.msda.application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import ge.msda.myapplication.R

class PasswordChangeActivity : AppCompatActivity() {

    private lateinit var editTextNewPassword: EditText
    private lateinit var buttonUpdatePassword: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_change)

        init()

        registerListeners()

    }

    private fun init() {
        editTextNewPassword = findViewById(R.id.editTextNewPassword)
        buttonUpdatePassword = findViewById(R.id.buttonUpdatePassword)
    }

    private fun registerListeners() {
        buttonUpdatePassword.setOnClickListener {
            val newPassword =  editTextNewPassword.text.toString()
            if(newPassword.isEmpty()) {
                Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            FirebaseAuth
                .getInstance()
                .currentUser
                ?.updatePassword(newPassword)
                ?.addOnCompleteListener { task ->
                    if(task.isSuccessful) {
                        Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

}