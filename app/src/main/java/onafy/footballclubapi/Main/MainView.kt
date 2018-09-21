package onafy.footballclubapi.Main

import onafy.footballclubapi.ModelDataClass.Team

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Team>)
}