package ca.qc.android.cstj.bibliothequemobile.fragments

import android.content.Context
import android.os.Bundle
import android.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import ca.qc.android.cstj.bibliothequemobile.R
import ca.qc.android.cstj.bibliothequemobile.adapters.InformationUniqueRecyclerViewAdapter
import ca.qc.android.cstj.bibliothequemobile.adapters.OnListFragmentInformationUnique
import ca.qc.android.cstj.bibliothequemobile.helpers.CATEGORIE_URL
import ca.qc.android.cstj.bibliothequemobile.models.Categorie


import com.github.kittinunf.fuel.android.core.Json
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpGet



class CategorieListFragment : Fragment() {

    private var mColumnCount = 1
    private var mListener: OnListFragmentInformationUnique? = null
    private var categories = mutableListOf<Categorie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            mColumnCount = arguments.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_categorie_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            val context = view.getContext()
            val recyclerView = view

            if (mColumnCount <= 1) {
                recyclerView.layoutManager = LinearLayoutManager(context)
            } else {
                recyclerView.layoutManager = GridLayoutManager(context, mColumnCount)
            }
            recyclerView.adapter = InformationUniqueRecyclerViewAdapter(categories, mListener)

            // Récuperer les catégories de l'API
            CATEGORIE_URL.httpGet().responseJson{request, response, result ->
                when(response.httpStatusCode){
                    200 ->{
                        createCategorieList(result.get())
                        recyclerView.adapter.notifyDataSetChanged()

                    }
                }
            }
        }
        return view
    }

    fun createCategorieList(json: Json) {

        categories.clear()
        val tabJson = json.array()

        for (i in 0.. (tabJson.length() -1))
        {
            categories.add(Categorie(Json(tabJson[i].toString())))
        }
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInformationUnique) {
            mListener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    companion object {

        // TODO: Customize parameter argument names
        private val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        fun newInstance(columnCount: Int): CategorieListFragment {
            val fragment = CategorieListFragment()
            val args = Bundle()
            args.putInt(ARG_COLUMN_COUNT, columnCount)
            fragment.arguments = args
            return fragment
        }
    }
}
