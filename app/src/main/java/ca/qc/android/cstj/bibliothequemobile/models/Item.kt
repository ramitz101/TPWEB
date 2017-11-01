package ca.qc.android.cstj.bibliothequemobile.models


abstract class Item {

   abstract fun getAffichage() : String
   abstract fun getUrl() : String
}