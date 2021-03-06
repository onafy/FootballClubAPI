package onafy.footballclubapi.Main

import com.google.gson.Gson
import onafy.footballclubapi.Api.ApiRepository
import onafy.footballclubapi.ModelDataClass.TeamResponse
import onafy.footballclubapi.Api.TheSportDBApi
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainPresenter(private val view: MainView,
                    private val apiRepository: ApiRepository,
                    private val gson: Gson){


    fun getTeamList(league: String?) {
        view.showLoading()
        doAsync{
            val data = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getTeams(league)),
                    TeamResponse::class.java)

            uiThread{
                view.hideLoading()
                view.showTeamList(data.teams)
            }
        }
    }

}