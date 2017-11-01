package ca.qc.android.cstj.bibliothequemobile.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ca.qc.android.cstj.bibliothequemobile.R

//import ca.qc.android.cstj.bibliothequemobile.adapters.OnListFragmentInformationUnique
import ca.qc.android.cstj.bibliothequemobile.models.Categorie
import ca.qc.android.cstj.bibliothequemobile.models.Item
import ca.qc.android.cstj.bibliothequemobile.models.Succursale
import kotlinx.android.synthetic.main.card_item.view.*


/**
 * [RecyclerView.Adapter] that can display a [Item] and makes a call to the
 * specified [OnListFragmentInformationUnique].
 * TODO: Replace the implementation with code for your data type.
 */
class InformationUniqueRecyclerViewAdapter(private val mValues:List<Item>,
                                           private val mListener:OnListFragmentInformationUnique?):RecyclerView.Adapter<InformationUniqueRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent:ViewGroup, viewType:Int): ViewHolder {
        val view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false)
        return ViewHolder(view)
    }

     override fun onBindViewHolder(holder: ViewHolder, position:Int) {
        holder.bind(mValues[position])

        holder.mView.setOnClickListener(object:View.OnClickListener {
            override fun onClick(v:View) {
                if (null != mListener)
                {

                        mListener!!.onListFragmentInteraction(holder.succursale)


                }
            }
        })
    }

    override fun getItemCount():Int {
        return mValues.size
    }

    inner class ViewHolder( val mView:View):RecyclerView.ViewHolder(mView) {
        var categorie: Categorie? = null
        var succursale: Succursale? = null
        var lblNom = mView.lblNom
        var item: Item? = null

        fun bind(item: Item) {
            this.item = item
            this.categorie = categorie
            this.succursale = succursale
            lblNom.text = item.getAffichage()
        }




    }
}
