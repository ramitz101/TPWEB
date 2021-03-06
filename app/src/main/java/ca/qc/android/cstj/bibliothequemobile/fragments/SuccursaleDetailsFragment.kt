package ca.qc.android.cstj.bibliothequemobile.fragments


import android.os.Bundle
import android.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import ca.qc.android.cstj.bibliothequemobile.R
import ca.qc.android.cstj.bibliothequemobile.helpers.SUCCURSALE_URL
import ca.qc.android.cstj.bibliothequemobile.helpers.URL_BASE
import ca.qc.android.cstj.bibliothequemobile.models.Succursale
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpGet
import kotlinx.android.synthetic.main.fragment_succursale_details.*
import kotlinx.android.synthetic.main.fragment_succursale_details.view.*


class SuccursaleDetailsFragment(private val href:String) : Fragment() {



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val url = href
        url.httpGet().responseJson { request, response, result ->
            when(response.httpStatusCode){
                200-> {
                    val succursale = Succursale(result.get())
                    lblTitre.text = succursale.ville+" : "+succursale.nom
                    lblAdresse.text = succursale.adresse
                    lblCodePostal.text = succursale.codePostal
                    lblProvince.text = succursale.ville+"  ("+succursale.province+")"
                    lblTelephone.text = "Téléphone: "+succursale.telephone
                    lblTelecopieur.text = "Télécopieur: "+ succursale.telecopieur
                    lblInformation.text = succursale.information
                }
                404-> {
                    Toast.makeText(this.context, "Erreur: ressource non trouvée!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        return inflater.inflate(R.layout.fragment_succursale_details, container, false)
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
         * @return A new instance of fragment SuccursaleDetailsFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): SuccursaleDetailsFragment {
            val fragment = SuccursaleDetailsFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
*/
}// Required empty public constructor
