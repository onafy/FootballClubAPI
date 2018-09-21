package onafy.footballclubapi.Team

import onafy.footballclubapi.ModelDataClass.Team

interface TeamsView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Team>)
}