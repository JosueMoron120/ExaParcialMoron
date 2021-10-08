package com.example.exaparcialmoron

import java.util.*;

 data class CarModel(
     var id : Int = getAutoId(),
     var nomcar : String = " ",
     var price : Double,
     var category : String = " "


 ){
     companion object{
         fun getAutoId() : Int {
             val random = Random()
             return random.nextInt(100);
         }

     }
     override fun toString(): String {
         return nomcar
     }
 };

