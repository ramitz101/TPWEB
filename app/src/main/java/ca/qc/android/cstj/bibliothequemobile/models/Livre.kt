package ca.qc.android.cstj.bibliothequemobile.models

import com.github.kittinunf.fuel.android.core.Json


class Livre(jsonObject: Json) : Item() {

    override fun getAffichage():String {
        return titre
    }

    override fun getUrl():String {
        return href
    }


    var href : String = jsonObject.obj().getString("href")
    var titre : String = jsonObject.obj().getString("titre")
    var prix : Double = jsonObject.obj().getDouble("prix")
    var auteur : String = jsonObject.obj().getString("auteur")
    var sujet : String = jsonObject.obj().getString("sujet")
    var ISBN : String = jsonObject.obj().getString("ISBN")
    var urlImg : String = jsonObject.obj().getString("urlImg")

}