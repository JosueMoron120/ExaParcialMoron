package com.example.exaparcialmoron

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

enum class ProviderType{
    BASIC
}
class MenuActivity : AppCompatActivity() {
    private lateinit var createCarButton: Button
    private lateinit var btn_dot_delete: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        createCarButton = findViewById(R.id.btn_dot_add)
        btn_dot_delete = findViewById(R.id.btn_dot_delete)
        btn_dot_delete.setOnClickListener{
            startActivity(Intent(this,ListCarActivity::class.java))
        }
        createCarButton.setOnClickListener{
            startActivity(Intent(this,InsertCarActivity::class.java))
        }
    }

}