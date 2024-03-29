package ge.msda.application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import ge.msda.myapplication.R

class PasswordResetActivity : AppCompatActivity() {

    private lateinit var editTextEmail: EditText
    private lateinit var buttonSendEmail: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_reset)

        init()

        registerListeners()

    }

    private fun init() {
        editTextEmail = findViewById(R.id.editTextEmail)
        buttonSendEmail = findViewById(R.id.buttonSendEmail)
    }

    private fun registerListeners() {
        buttonSendEmail.setOnClickListener {
            val email = editTextEmail.text.toString()
            if(email.isEmpty()) {
                Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful) {
                        Toast.makeText(this, "Check email!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

}