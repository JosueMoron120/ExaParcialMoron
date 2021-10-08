package com.example.exaparcialmoron

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.AdapterListUpdateCallback
import androidx.recyclerview.widget.RecyclerView

class CarAdapter: RecyclerView.Adapter<CarAdapter.ProductViewHolder>(){
    private var prodList : ArrayList<CarModel> = ArrayList()
    private var onClickItem : ((CarModel)-> Unit)? = null
    private var onClickDeleteItem :  ((CarModel)-> Unit)? = null

    fun addItems(items : ArrayList<CarModel>){
        this.prodList = items
        notifyDataSetChanged()
    }
    fun setOnClickItem(callback: (CarModel) -> Unit){
        this.onClickItem = callback
    }
    fun setOnClickDeleteItem(callback: (CarModel) -> Unit){
        this.onClickDeleteItem = callback
    }


    class ProductViewHolder(var view: View) : RecyclerView.ViewHolder(view){
        private var nomprod = view.findViewById<TextView>(R.id.tvName)
        private var price = view.findViewById<TextView>(R.id.tvPrice)
        private var category = view.findViewById<TextView>(R.id.tvCategory)
        var btnDelete = view.findViewById<Button>(R.id.btnDelete)


        fun bindView(prod: CarModel){
            nomprod.text = prod.nomcar
            price.text = prod.price.toString()
            category.text = prod.category


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProductViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.activity_card_item,parent, false)
    )

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val prod = prodList[position]
        holder.bindView(prod)
        holder.itemView.setOnClickListener { onClickItem?.invoke(prod)  }
        holder.btnDelete.setOnClickListener { onClickDeleteItem?.invoke(prod) }
    }

    override fun getItemCount(): Int {
        return prodList.size
    }


}