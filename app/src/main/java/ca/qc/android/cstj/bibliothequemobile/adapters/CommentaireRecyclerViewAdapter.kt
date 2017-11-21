package ca.qc.android.cstj.bibliothequemobile.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ca.qc.android.cstj.bibliothequemobile.R
import ca.qc.android.cstj.bibliothequemobile.fragments.LivreListFragment
import ca.qc.android.cstj.bibliothequemobile.models.Commentaire

import kotlinx.android.synthetic.main.card_commentaire.view.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class CommentaireRecyclerViewAdapter(private val mValues: List<Commentaire>) : RecyclerView.Adapter<CommentaireRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_commentaire, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mValues[position])
        /*holder.mView.setOnClickListener {
            mListener!!.onListFragmentInteraction(holder.commentaire)
        }*/
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {




        var commentaire: Commentaire? = null



        var lblNomPrenom = mView.lblNomPrenom
        var lblDate = mView.lblDate
        var lblCommentaire = mView.lblCommentaire
        var rating = mView.ratingBar

        fun bind(commentaire: Commentaire){
            this.commentaire = commentaire
            var simpleDateFormat = SimpleDateFormat("yyyy-mm-dd")
            simpleDateFormat.timeZone = TimeZone.getDefault()
            val maDate = simpleDateFormat.parse(commentaire.dateCommentaire)
            lblCommentaire.text = commentaire.message

            lblDate.text = simpleDateFormat.format(maDate)
            lblNomPrenom.text = commentaire.nom + " " + commentaire.prenom
            rating.rating = commentaire.etoile.toFloat()

        }

    }
}