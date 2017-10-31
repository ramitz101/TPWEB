package ca.qc.android.cstj.bibliothequemobile.models

import com.github.kittinunf.fuel.android.core.Json

/**
 * Created by Administrateur on 2017-10-31.
 */
class Categorie(jsonObject: Json) {
    var categorieId : String = jsonObject.obj().getString("idCategorie")
    var categorieUUID : String = jsonObject.obj().getString("uuid")
    var categorie : String = jsonObject.obj().getString("categorie")
}