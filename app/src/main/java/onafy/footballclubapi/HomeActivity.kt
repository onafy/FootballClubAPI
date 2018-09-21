package onafy.footballclubapi

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.*
import onafy.footballclubapi.Fragment.FavoriteTeamsFragment
import onafy.footballclubapi.Fragment.TeamsFragment
import onafy.footballclubapi.R.id.favorites
import onafy.footballclubapi.R.id.teams

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                teams -> {

                }
                favorites -> {

                }
            }
            true
        }
        bottom_navigation.selectedItemId = teams
    }

    private  fun loadTeamsFragment(savedInstanceState: Bundle?){
        if(savedInstanceState == null)
        {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, TeamsFragment(), TeamsFragment::class.java.simpleName)
                    .commit()
        }
    }

    private fun loadFavoritesFragment(savedInstanceState: Bundle?){
        if(savedInstanceState == null)
        {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, FavoriteTeamsFragment(), FavoriteTeamsFragment::class.java.simpleName)
                    .commit()
        }
    }
}
