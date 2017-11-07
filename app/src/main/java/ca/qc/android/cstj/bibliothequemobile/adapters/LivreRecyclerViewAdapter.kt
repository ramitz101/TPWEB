package ca.qc.android.cstj.bibliothequemobile.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ca.qc.android.cstj.bibliothequemobile.R

import ca.qc.android.cstj.bibliothequemobile.fragments.LivreListFragment.OnListFragmentInteractionListener
import ca.qc.android.cstj.bibliothequemobile.models.Livre
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_livre.view.*


class LivreRecyclerViewAdapter(private val mValues: List<Livre>, private val mListener: OnListFragmentInteractionListener?) : RecyclerView.Adapter<LivreRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_livre, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mValues[position])
        holder.mView.setOnClickListener {
            mListener!!.onListFragmentInteraction(holder.livre)
        }
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {

        var lblTitre = mView.lblTitre
        var lblAuteur = mView.lblAuteur
        var imgLivre = mView.imgLivre

        var livre: Livre? = null

        fun bind(livre: Livre){
            this.livre = livre

            Picasso.with(imgLivre.context).load(livre.urlImg).placeholder(R.drawable.spinner).fit().centerInside().into(imgLivre)

            lblTitre.text = livre.titre
            lblAuteur.text = livre.auteur

        }

    }
}
