package ca.qc.android.cstj.bibliothequemobile.models

import com.github.kittinunf.fuel.android.core.Json
import ca.qc.android.cstj.bibliothequemobile.models.Item


class Categorie(jsonObject: Json) : Item() {
    override fun getAffichage():String {
        return nom
    }

    override fun getUrl():String {
        return href
    }

    var nom : String = jsonObject.obj().getString("categorie")
    var href : String = jsonObject.obj().getString("href")
}