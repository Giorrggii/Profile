package ge.msda.application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import ge.msda.myapplication.R

class ProfileActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private lateinit var buttonChangePassword: Button
    private lateinit var buttonLogout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        init()

        registerListeners()

        textView.text = FirebaseAuth.getInstance().currentUser?.uid

    }

    private fun init() {
        textView = findViewById(R.id.textView)
        buttonChangePassword = findViewById(R.id.buttonChangePassword)
        buttonLogout = findViewById(R.id.buttonLogout)
    }

    private fun registerListeners() {
        buttonChangePassword.setOnClickListener {
            startActivity(Intent(this, PasswordChangeActivity::class.java))
        }
        buttonLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

}