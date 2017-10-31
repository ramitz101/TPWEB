package ca.qc.android.cstj.bibliothequemobile.models

import com.github.kittinunf.fuel.android.core.Json
import ca.qc.android.cstj.bibliothequemobile.models.Item

/**
 * Created by Administrateur on 2017-10-31.
 */
class Categorie(jsonObject: Json) : Item() {
    override fun getAffichage():String {
        return nom
    }

    var categorieId : String = jsonObject.obj().getString("idCategorie")
    var categorieUUID : String = jsonObject.obj().getString("uuid")
    var nom : String = jsonObject.obj().getString("categorie")
}