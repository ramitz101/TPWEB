package ca.qc.cstj.android.testpost

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import ca.qc.cstj.android.pokedexsolution.ScoreRecyclerViewAdapter
import ca.qc.cstj.android.testpost.models.Score
import com.github.kittinunf.fuel.android.core.Json
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    object SERVICES {
        const val MEMORY_URL = "http://api-andromia.193b.starter-ca-central-1.openshiftapps.com/nexus/memory/pointages"
    }

    var scores = mutableListOf<Score>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lstPointage.layoutManager = LinearLayoutManager(this)
        lstPointage.adapter = ScoreRecyclerViewAdapter(scores)

        updateScore()

        btnEnvoyer.setOnClickListener {
            val score = Score(txtNom.text.toString(),  txtScore.text.toString().toInt())
            Toast.makeText(this, score.toJson(), Toast.LENGTH_LONG).show()
            SERVICES.MEMORY_URL.httpPost()
                    .header("Content-Type" to "application/json")
                    .body(score.toJson()).responseJson { _, response, _ ->
                    when (response.statusCode) {
                        201 -> {
                            updateScore()
                        }
                    }
            }

            /*Fuel.post(SERVICES.MEMORY_URL).header("Content-Type" to "application/json")
                    .body(score.toJson()).responseJson { _, response, _ ->
                        when(response.statusCode) {
                            201 -> {
                                updateScore()
                            }
                        }

                    }*/


        }

    }

    private fun updateScore() {
        SERVICES.MEMORY_URL.httpGet().responseJson { _, response, result ->
            when(response.statusCode) {
                200 -> {
                    createScoreList(result.get())
                    lstPointage.adapter.notifyDataSetChanged()
                }

            }
        }
    }

    private fun createScoreList(array: Json){
        scores.clear()
        val tab = array.array()

        for (i in 0..(tab.length()- 1)) {
            scores.add(Score(Json(tab[i].toString())))
        }

    }
}
