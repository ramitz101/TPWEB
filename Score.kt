package ca.qc.cstj.android.testpost.models

import com.github.kittinunf.fuel.android.core.Json
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.annotations.Expose
import java.util.*

/**
 * Created by Yannick on 2017-11-09.
 */
data class Score(@Expose var nom : String, @Expose var pointage: Int) {

    constructor(json: Json) :this(json.obj().getString("nom"), json.obj().getInt("pointage"))

    fun toJson() : String {
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(this)
    }
}