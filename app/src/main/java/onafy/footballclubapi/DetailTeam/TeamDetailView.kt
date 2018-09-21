package onafy.footballclubapi.DetailTeam

import onafy.footballclubapi.ModelDataClass.Team

interface TeamDetailView {
    fun showLoading()
    fun hideLoading()
    fun showTeamDetail(data: List<Team>)
}