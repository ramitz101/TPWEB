package ca.qc.android.cstj.bibliothequemobile

import android.app.FragmentManager
import android.os.Bundle
import android.os.PersistableBundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import ca.qc.android.cstj.bibliothequemobile.adapters.OnListFragmentInformationUnique
import ca.qc.android.cstj.bibliothequemobile.fragments.*
import ca.qc.android.cstj.bibliothequemobile.models.Item
import ca.qc.android.cstj.bibliothequemobile.models.Succursale

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

import ca.qc.android.cstj.bibliothequemobile.models.Categorie
import ca.qc.android.cstj.bibliothequemobile.models.Livre
import android.content.Intent
import android.nfc.Tag
import android.util.Log
import android.view.Gravity
import ca.qc.android.cstj.bibliothequemobile.R.layout.activity_main


class MainActivity : AppCompatActivity(), FragmentManager.OnBackStackChangedListener, NavigationView.OnNavigationItemSelectedListener, OnListFragmentInformationUnique, LivreListFragment.OnListFragmentInteractionListener {


    override fun onBackStackChanged() {

        if(fragmentManager.backStackEntryCount > 0) {
            val index = fragmentManager.backStackEntryCount - 1
            val backEntry = fragmentManager.getBackStackEntryAt(index)


            when(backEntry.name){
                "DetailsSuccursale" -> toolbar.title = "Détails Succursale"
                "ListeSuccursale" -> toolbar.title = "Liste Succursale"
                "ListeCategorie" -> toolbar.title = "Liste Catégorie"
                "ListeLivreCategorie" -> toolbar.title = "Liste Livre"
            //"DetailsLivre" -> toolbar.title = "Détails Livre"
                else -> toolbar.title = "Bibliothèque Mobile"

            }
        }else{
            toolbar.title = "Bibliothèque Mobile"
        }
    }

    override fun onListFragmentInteraction(livre: Livre?) {
        nav_view.setNavigationItemSelectedListener(this)
        Runnable {
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.contentFrame, LivreDetailsFragment(livre!!.href))
            transaction.addToBackStack("DetailsLivre")
            transaction.commit()
        }.run()
    }

    override fun onListFragmentInteraction(item: Item?) {
        toolbar.setNavigationIcon(R.drawable.arrowhead_left)
        //Succursale
        if(item is Succursale) {


            Runnable {
                val transaction = fragmentManager.beginTransaction()
                transaction.replace(R.id.contentFrame, SuccursaleDetailsFragment(item.href))
                transaction.addToBackStack("DetailsSuccursale")
                transaction.commit()
            }.run()

        }
        //Categorie
        if(item is Categorie)
        {

            toolbar.title= "Catégorie"
            Runnable {
                val transaction = fragmentManager.beginTransaction()
                transaction.replace(R.id.contentFrame, LivreListFragment(item.href))
                transaction.addToBackStack("ListeLivreCategorie")

                transaction.commit()
            }.run()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        fragmentManager.addOnBackStackChangedListener(this)
        toolbar.title = "Bibliothèque Mobile"

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)

        //fragmentManager.findFragmentByTag()


        toggle.isDrawerIndicatorEnabled=false

        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        toolbar.setNavigationIcon(R.drawable.images)


        toggle.setToolbarNavigationClickListener  {
            if(drawer_layout.isDrawerOpen(GravityCompat.START)) {
                drawer_layout.closeDrawer(GravityCompat.START)
            } else {

                if(fragmentManager.backStackEntryCount > 0) {

                    val index = fragmentManager.backStackEntryCount - 1
                    val backEntry = fragmentManager.getBackStackEntryAt(index)



                    if (backEntry.name == "ListeSuccursale" || backEntry.name =="ListeCategorie") {
                        drawer_layout.openDrawer(GravityCompat.START)
                        toolbar.setNavigationIcon(R.drawable.images)

                    } else {
                        super.onBackPressed()
                        if (fragmentManager.backStackEntryCount == 0 || fragmentManager.getBackStackEntryAt(index -1).name == "ListeCategorie" || fragmentManager.getBackStackEntryAt(index -1).name == "ListeSuccursale") {
                            toolbar.setNavigationIcon(R.drawable.images)
                        }

                    }
                } else {
                    drawer_layout.openDrawer(GravityCompat.START)
                    toolbar.setNavigationIcon(R.drawable.images)
                }
            }
        }
        nav_view.setNavigationItemSelectedListener(this)

        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.contentFrame, SuccursaleListFragment.newInstance(1))
        transaction.commit()

    }

    override fun onBackPressed() {


        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            if(fragmentManager.backStackEntryCount > 0) {

                val index = fragmentManager.backStackEntryCount - 1
                val backEntry = fragmentManager.getBackStackEntryAt(index)


                if (backEntry.name == "ListeSuccursale" || backEntry.name =="ListeCategorie") {
                    drawer_layout.openDrawer(GravityCompat.START)
                    toolbar.setNavigationIcon(R.drawable.images)

                } else {
                    super.onBackPressed()
                    if (fragmentManager.backStackEntryCount == 0 || fragmentManager.getBackStackEntryAt(index -1).name == "ListeCategorie" || fragmentManager.getBackStackEntryAt(index -1).name == "ListeSuccursale") {
                        toolbar.setNavigationIcon(R.drawable.images)

                        //fragmentManager.back fragmentManager.getBackStackEntryAt(0)
                        val intent = Intent(applicationContext, MainActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                    }

                }
            } else {
                super.onBackPressed()
            }
        }



    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        if (item.itemId == android.R.id.home) {
            onBackPressed();    //Call the back button's method
            return true;
        }

        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_succursale -> {
                Runnable {
                    val transaction = fragmentManager.beginTransaction()
                    //transaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    transaction.replace(R.id.contentFrame, SuccursaleListFragment.newInstance(1))
                    transaction.addToBackStack("ListeSuccursale")
                    transaction.commit()
                }.run()
            }
            R.id.nav_categorie -> {
                Runnable {
                    val transaction = fragmentManager.beginTransaction()
                    //transaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    transaction.replace(R.id.contentFrame, CategorieListFragment.newInstance(1))
                    transaction.addToBackStack("ListeCategorie")
                    transaction.commit()
                }.run()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
