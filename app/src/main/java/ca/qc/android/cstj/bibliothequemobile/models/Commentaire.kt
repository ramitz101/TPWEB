package ca.qc.android.cstj.bibliothequemobile.models

import com.github.kittinunf.fuel.android.core.Json

/**
 * Created by Administrateur on 2017-11-09.
 */
class Commentaire(jsonObject: Json) {

    var dateCommentaire : String = jsonObject.obj().getString("dateCommentaire")
    var message : String = jsonObject.obj().getString("message")
    var etoile : Double = jsonObject.obj().getDouble("etoile")
    var nom : String = jsonObject.obj().getString("nom")
    var prenom : String = jsonObject.obj().getString("prenom")
}