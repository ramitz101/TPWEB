package ca.qc.android.cstj.bibliothequemobile.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ca.qc.android.cstj.bibliothequemobile.R

import ca.qc.android.cstj.bibliothequemobile.fragments.LivreListFragment.OnListFragmentInteractionListener
import ca.qc.android.cstj.bibliothequemobile.models.Livre


/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class LivreRecyclerViewAdapter(private val mValues: List<Livre>, private val mListener: OnListFragmentInteractionListener?) : RecyclerView.Adapter<LivreRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_livre, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.livre = mValues[position]
       // holder.mIdView.text = mValues[position].id
        //holder.mContentView.text = mValues[position].content

        holder.mView.setOnClickListener {
            mListener?.onListFragmentInteraction(holder.livre)
        }
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {

        var livre: Livre? = null
        fun bind(livres: Livre){
            this.livre = livre


        }

    }
}
