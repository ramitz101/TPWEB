package ca.qc.android.cstj.bibliothequemobile.fragments


import android.annotation.SuppressLint
import android.os.Bundle
import android.app.Fragment
import android.graphics.Paint
import android.text.SpannableString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView

import ca.qc.android.cstj.bibliothequemobile.R
import ca.qc.android.cstj.bibliothequemobile.models.Commentaire
import ca.qc.android.cstj.bibliothequemobile.models.Livre
import com.github.kittinunf.fuel.android.core.Json
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpGet
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_livre_details.*


class LivreDetailsFragment(private val href: String) : Fragment() {


    private var commentaires = mutableListOf<Commentaire>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var url = href + "?expand=commentaires"
        url.httpGet().responseJson{ request, response, result ->
            when(response.httpStatusCode){
                200-> {
                    val livre = Livre(result.get())

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

        return inflater.inflate(R.layout.fragment_livre_details, container, false)
    }



    fun createCommentaireList(json: Json) {

        commentaires.clear()
        var tabJson = json.array()

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
