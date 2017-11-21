package ca.qc.android.cstj.bibliothequemobile.fragments


import android.annotation.SuppressLint
import android.os.Bundle
import android.app.Fragment
import android.graphics.Paint
import android.support.v7.widget.LinearLayoutManager
import android.text.SpannableString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.Toast

import ca.qc.android.cstj.bibliothequemobile.R
import ca.qc.android.cstj.bibliothequemobile.adapters.CommentaireRecyclerViewAdapter
import ca.qc.android.cstj.bibliothequemobile.helpers.COMMENTAIRE_URL
import ca.qc.android.cstj.bibliothequemobile.models.Commentaire
import ca.qc.android.cstj.bibliothequemobile.models.Livre
import com.github.kittinunf.fuel.android.core.Json
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_livre.view.*
import kotlinx.android.synthetic.main.fragment_livre_details.*
import kotlinx.android.synthetic.main.fragment_livre_details.view.*
import java.text.SimpleDateFormat
import java.util.*


class LivreDetailsFragment(private val href: String) : Fragment() {
    var commentaires = mutableListOf<Commentaire>()
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {


        lstCommentaires.layoutManager = LinearLayoutManager(this.context)
        lstCommentaires.adapter = CommentaireRecyclerViewAdapter(commentaires)

        var url = href + "?expand=commentaires"
        var urlCommentaire = href + "/commentaires"

        //lstCommentaires.adapter =
        view!!.btnSubmit.setOnClickListener{

            if(txtMessage.text != null && txtPrenom.text != null && txtNom.text != null ) {

                var calendrier = Calendar.getInstance()
                val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                val date = dateFormat.format(calendrier.getTime())
                var commentaire = Commentaire(date.toString(), txtMessage.text.toString(), starRatingBar.rating.toString().toDouble(), txtNom.text.toString(), txtPrenom.text.toString())
                urlCommentaire.httpPost()
                        .header("Content-Type" to "application/json")
                        .body(commentaire.toJson()).responseJson { _, response, _ ->
                    when (response.statusCode) {
                        201 -> {

                            Toast.makeText(this.context, "Commentaire ajouté", Toast.LENGTH_LONG).show()

                            txtMessage.text = null
                            txtNom.text = null
                            txtPrenom.text = null
                            starRatingBar.rating = 0F
                        }
                    }
                }

            }else{
                Toast.makeText(this.context,"Champs manquant",Toast.LENGTH_LONG).show()
            }
        }



        // Inflate the layout for this fragment

        url.httpGet().responseJson{ request, response, result ->
            when(response.statusCode){
                200-> {
                    val livre = Livre(result.get())
                    createCommentaireList(result.get())
                    lstCommentaires.adapter.notifyDataSetChanged()
                    Picasso.with(imgLivre.context).load(livre.urlImg).placeholder(R.drawable.spinner).fit().centerInside().into(imgLivre)

                    lblPrix.text = livre.prix.toString() + " $"
                    lblAuteur.text = livre.auteur
                    lblSujet.text = livre.sujet
                    lblISBN.text = "ISBN: " + livre.ISBN

                    lblCommentaires.paintFlags = Paint.UNDERLINE_TEXT_FLAG
                }
                404-> {
                }
            }
        }
        super.onViewCreated(view, savedInstanceState)

    }





    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_livre_details, container, false)

        return view
    }



    fun createCommentaireList(json: Json) {

        commentaires.clear()
        //var tabJson = json.array()
        var tabJson = json.obj().getJSONArray("commentaires")


        for (i in 0.. (tabJson.length() -1)){
            commentaires.add(Commentaire((Json(tabJson[i] .toString()))))

        }
    }
/*
    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LivreDetailsFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): LivreDetailsFragment {
            val fragment = LivreDetailsFragment(livre.href)
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
*/
}// Required empty public constructor
