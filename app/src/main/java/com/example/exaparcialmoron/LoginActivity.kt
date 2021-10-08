package com.example.exaparcialmoron

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity(){

    private lateinit var LoginButton : Button
    private lateinit var EmailEditText: EditText
    private lateinit var PasswordEditText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()
        SetUp()
    }


    private fun init() {
        LoginButton = findViewById(R.id.btnLogin)
        EmailEditText = findViewById(R.id.emailEditText)
        PasswordEditText = findViewById(R.id.passwordEditText)

    }

    private fun SetUp() {
        LoginButton.setOnClickListener {
            if(EmailEditText.text.isNotEmpty() && PasswordEditText.text.isNotEmpty()){
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(EmailEditText.text.toString(), PasswordEditText.text.toString())
                    .addOnCompleteListener{
                        if(it.isSuccessful){
                            Toast.makeText(this, "sesion iniciada correctamente", Toast.LENGTH_SHORT).show()
                            showHome(it.result?.user?.email ?: "", ProviderType.BASIC)
                        }else{
                            showAlert()
                        }
                    }
            }

        }
    }

    private fun showHome(email : String, provider : ProviderType){
        val homeItent = Intent(this, MenuActivity::class.java).apply {
            putExtra( "email", email)
            putExtra( "provider", provider.name)
        }
        startActivity(homeItent)
    }

    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("error")
        builder.setMessage("se ha producido un error autentificando al usuario")
        builder.setPositiveButton("aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()


    }

}