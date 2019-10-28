package no.hiof.mariusrb.minkokebok.Model

data class Recipe(var uid: String, var title: String, var description: String){
    constructor() : this("", "", "")
}