package onafy.footballclubapi.Main

import android.R
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.*
import onafy.footballclubapi.R.array.league
import com.google.gson.Gson
import onafy.footballclubapi.Api.ApiRepository
import onafy.footballclubapi.ModelDataClass.Team
import onafy.footballclubapi.R.color.colorAccent
import onafy.footballclubapi.Util.invisible
import onafy.footballclubapi.Util.visible

import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class MainActivity : AppCompatActivity(), MainView {
    private lateinit var listTeam: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var spinner: Spinner
    private var teams: MutableList<Team> = mutableListOf()
    private lateinit var presenter: MainPresenter
    private lateinit var adapter: MainAdapter
    private lateinit var leagueName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ========================= UI ===========================================================
        linearLayout {
            lparams(width= matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)


            spinner = spinner()
            swipeRefresh = swipeRefreshLayout{
                setColorSchemeResources(colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)
                relativeLayout{
                    lparams(width= matchParent, height= wrapContent)

                    listTeam = recyclerView{
                        lparams(width= matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }
                progressBar =  progressBar{}
                        .lparams{
                            centerHorizontally()
                        }
                }
            }
        }
        //========================================================================================

        adapter = MainAdapter(teams)
        listTeam.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(this, request, gson)

        val spinnerItems = resources.getStringArray(league)
        val spinnerAdapter = ArrayAdapter(ctx, R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter
        spinner.onItemSelectedListener= object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
              leagueName = spinner.selectedItem.toString()
                presenter.getTeamList(leagueName)
            }

        }

        swipeRefresh.onRefresh {
            presenter.getTeamList(leagueName)
        }

    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamList(data: List<Team>) {
        swipeRefresh.isRefreshing = false
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }
}
