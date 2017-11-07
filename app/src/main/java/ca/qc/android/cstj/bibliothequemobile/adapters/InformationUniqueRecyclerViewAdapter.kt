package ca.qc.android.cstj.bibliothequemobile.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ca.qc.android.cstj.bibliothequemobile.R

import ca.qc.android.cstj.bibliothequemobile.models.Item
import kotlinx.android.synthetic.main.card_item.view.*



class InformationUniqueRecyclerViewAdapter(private val mValues:List<Item>,
                                           private val mListener:OnListFragmentInformationUnique?):RecyclerView.Adapter<InformationUniqueRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent:ViewGroup, viewType:Int): ViewHolder {
        val view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false)
        return ViewHolder(view)
    }

     override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mValues[position])
         holder.mView.setOnClickListener{
             mListener!!.onListFragmentInteraction(holder.item)
         }
       /* holder.mView.setOnClickListener(object:View.OnClickListener {
            override fun onClick(v:View) {
                if (null != mListener)
                {

                        mListener!!.onListFragmentInteraction(holder.item)


                }
            }
        })*/
    }

    override fun getItemCount():Int {
        return mValues.size
    }

    inner class ViewHolder( val mView:View):RecyclerView.ViewHolder(mView) {
        var lblNom = mView.lblNom
        var item: Item? = null

        fun bind(item: Item) {
            this.item = item
            lblNom.text = item.getAffichage()
        }




    }
}
