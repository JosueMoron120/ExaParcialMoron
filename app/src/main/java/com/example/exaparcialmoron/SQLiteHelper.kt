package com.example.exaparcialmoron

import android.content.*;
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper;
import java.lang.Exception
    class SQLiteHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    companion object {
        private const val DATABASE_VERSION = 2;
        private const val DATABASE_NAME = "carro.db";
        private const val TB_CARRO = "carro";
        private const val IDC = "idcarro";
        private const val NOMCAR = "nomcar";
        private const val PRICE = "price";
        private const val CATEGORY = "category";
    }

        override fun onCreate(db: SQLiteDatabase?) {
                val createTBCar =
            ("CREATE TABLE  $TB_CARRO( $IDC INTEGER PRIMARY KEY,$NOMCAR TEXT, $PRICE REAL, $CATEGORY TEXT)")
            db?.execSQL(createTBCar)
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db!!.execSQL("DROP TABLE IF EXISTS $TB_CARRO")
            onCreate(db)
        }
        override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

            db!!.execSQL("DROP TABLE IF EXISTS $TB_CARRO")
            onCreate(db)
    }
        fun insertCar(car: CarModel): Long {
            val db = this.writableDatabase;
            val contentValues = ContentValues()
            contentValues.put(NOMCAR, car.nomcar)
            contentValues.put(PRICE, car.price)
            contentValues.put(CATEGORY, car.category)

            val success = db.insert(TB_CARRO, null, contentValues)
            db.close()
            return success
        }
        fun getAllCars(): ArrayList<CarModel> {
            val db = this.readableDatabase
            val productList: ArrayList<CarModel> = ArrayList();
            val cursor: Cursor?
            val selectQuery = "SELECT * FROM  $TB_CARRO";

            try {
                cursor = db.rawQuery(selectQuery, null)
            } catch (e: Exception) {
                e.printStackTrace()
                db.execSQL(selectQuery);
                return ArrayList();
            }
            var id : Int
            var nomcar : String
            var price : Double
            var category : String
            if (cursor.moveToFirst()) {
                do {
                    id = cursor.getInt(cursor.getColumnIndex("idcarro"))
                    nomcar   = cursor.getString(cursor.getColumnIndex("nomcar"))
                    price   = cursor.getDouble(cursor.getColumnIndex("price"))
                    category   = cursor.getString(cursor.getColumnIndex("category"))
                    val prod = CarModel(id = id, nomcar = nomcar, price = price, category = category )
                    productList.add(prod)
                } while (cursor.moveToNext())
            }
            db.close()
            return productList
        }

        fun deleteCar(idcarro:Int):Int{
            val db = this.writableDatabase
            val contentValue = ContentValues();
            contentValue.put(IDC,idcarro)

            val success = db.delete(TB_CARRO,"$IDC = $idcarro",null)
            db.close()
            return success
        }



    }