package ca.qc.android.cstj.bibliothequemobile.models

import com.github.kittinunf.fuel.android.core.Json


class Succursale(jsonObject: Json) : Item() {
    override fun getAffichage():String {
        return nom
    }



    var nom : String = jsonObject.obj().getString("appelatif")
    var href: String = jsonObject.obj().getString("href")
    var adresse : String = jsonObject.obj().getString("adresse")
    var ville : String = jsonObject.obj().getString("ville")
    var codePostal : String = jsonObject.obj().getString("codePostal")
    var province : String = jsonObject.obj().getString("province")
    var telephone : String = jsonObject.obj().getString("telephone")
    var telecopieur : String = jsonObject.obj().getString("telecopieur")
    var information : String = jsonObject.obj().getString("information")



}