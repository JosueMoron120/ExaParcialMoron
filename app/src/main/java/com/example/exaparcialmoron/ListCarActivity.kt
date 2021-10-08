package com.example.exaparcialmoron

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListCarActivity: AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private var adapter : CarAdapter? = null
    private lateinit var sqliteHelper : SQLiteHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_list_car)
        recyclerView = findViewById(R.id.recyclerView)
        sqliteHelper = SQLiteHelper(this)
        initRecyclerView()
        super.onCreate(savedInstanceState)
        adapter?.setOnClickDeleteItem { deleteCar(it.id) }

        getCars()


    }
    private fun getCars(){
        val carList = sqliteHelper.getAllCars()
        Log.e("ppp", "${carList.size}")
        adapter?.addItems(carList)
    }

    private fun initRecyclerView(){
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CarAdapter()
        recyclerView.adapter =adapter
    }
    private fun deleteCar(id : Int) {
        if (id == null) return
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Está seguro de eliminar?")
        builder.setCancelable(true)
        builder.setPositiveButton("Yes" ){dialog, _ ->
            sqliteHelper.deleteCar(id)
            getCars()
            dialog.dismiss()
        }
        builder.setNegativeButton("No") {dialog, _ ->
            dialog.dismiss()
        }
        val alert = builder.create()
        alert.show()

    }




}