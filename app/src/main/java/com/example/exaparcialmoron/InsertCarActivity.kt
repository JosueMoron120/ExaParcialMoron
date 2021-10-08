package com.example.exaparcialmoron

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class InsertCarActivity :  AppCompatActivity(){
    private lateinit var edNomCar : EditText
    private lateinit var edPrice : EditText
    private lateinit var edCategory : EditText
    private lateinit var btnAdd : Button
    private lateinit var sqliteHelper : SQLiteHelper
    private var prod : CarModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        sqliteHelper = SQLiteHelper(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_car)
        initView()
        btnAdd.setOnClickListener { addCar() }
    }
    private fun initView(){
        edNomCar = findViewById(R.id.edNomCar)
        edPrice = findViewById(R.id.edPrice)
        edCategory = findViewById(R.id.edCategory)
        btnAdd = findViewById(R.id.btnAdd)

    }
    private fun addCar(){
        val nomCar = edNomCar.text.toString()
        val  Price = edPrice.text.toString().toDouble()
        val  Category = edCategory.text.toString()
        if(nomCar.isBlank() ||Price.isNaN() ||  Category.isBlank() ){
            Toast.makeText(this,  "Por favor, no deje en blanco los inputs", Toast.LENGTH_SHORT).show()
        }else{
            val car = CarModel(nomcar = nomCar,price = Price, category = Category )
            val status = sqliteHelper.insertCar(car)
            if(status > -1){
                Toast.makeText(this, "Carro Añadido", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Carro NO Añadido", Toast.LENGTH_SHORT).show()

            }
        }
    }

}
