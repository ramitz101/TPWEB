package ca.qc.android.cstj.bibliothequemobile.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ca.qc.android.cstj.bibliothequemobile.R

import ca.qc.android.cstj.bibliothequemobile.fragments.SuccursaleListFragment.OnListFragmentInteractionListener
import ca.qc.android.cstj.bibliothequemobile.models.Item
import kotlinx.android.synthetic.main.card_item.view.*


/**
 * [RecyclerView.Adapter] that can display a [Item] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class InformationUniqueRecyclerViewAdapter(private val mValues:List<Item>,
                                           private val mListener:OnListFragmentInteractionListener?):RecyclerView.Adapter<InformationUniqueRecyclerViewAdapter.ViewHolder>() {

    public override fun onCreateViewHolder(parent:ViewGroup, viewType:Int): ViewHolder {
        val view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false)
        return ViewHolder(view)
    }

    public override fun onBindViewHolder(holder: ViewHolder, position:Int) {
        holder.bind(mValues[position])

        holder.mView.setOnClickListener(object:View.OnClickListener {
            public override fun onClick(v:View) {
                if (null != mListener)
                {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener!!.onListFragmentInteraction(holder.mItem)
                }
            }
        })
    }

    public override fun getItemCount():Int {
        return mValues.size
    }

    inner class ViewHolder( val mView:View):RecyclerView.ViewHolder(mView) {

        var lblNom = mView.lblNom
        var mItem:Item? = null

        fun bind(item: Item) {
            //this.it = item
            lblNom.text = item.getAffichage()
        }




    }
}
