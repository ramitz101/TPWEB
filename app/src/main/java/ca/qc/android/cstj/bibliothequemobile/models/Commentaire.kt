package ca.qc.android.cstj.bibliothequemobile.models

import com.github.kittinunf.fuel.android.core.Json
import com.google.gson.GsonBuilder
import com.google.gson.annotations.Expose

/**
 * Created by Administrateur on 2017-11-09.
 */
data class Commentaire(@Expose var dateCommentaire: String, @Expose var message:String,@Expose var etoile: Double, @Expose var nom: String,@Expose var prenom: String) {

    constructor(json:Json) : this(json.obj().getString("dateCommentaire"),
            json.obj().getString("message"),
            json.obj().getDouble("etoile"),
            json.obj().getString("nom"),
            json.obj().getString("prenom"))
    /*var dateCommentaire : String = jsonObject.obj().getString("dateCommentaire")
    var message : String = jsonObject.obj().getString("message")
    var etoile : Double = jsonObject.obj().getDouble("etoile")
    var nom : String = jsonObject.obj().getString("nom")
    var prenom : String = jsonObject.obj().getString("prenom")*/

    fun toJson() : String {
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(this)
    }
}