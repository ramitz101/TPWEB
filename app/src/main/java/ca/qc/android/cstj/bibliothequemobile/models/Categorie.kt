package ca.qc.android.cstj.bibliothequemobile.models

import com.github.kittinunf.fuel.android.core.Json
import ca.qc.android.cstj.bibliothequemobile.models.Item

/**
 * Created by Administrateur on 2017-10-31.
 */
<<<<<<< HEAD
class Categorie(jsonObject: Json) {
    

=======
class Categorie(jsonObject: Json) {
    var categorieId : String = jsonObject.obj().getString("idCategorie")
    var categorieUUID : String = jsonObject.obj().getString("uuid")
    var categorie : String = jsonObject.obj().getString("categorie")
>>>>>>> 594779b2745973608ec84fb285845b8f5a0d0f42
}