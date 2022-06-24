package com.example.barmenu.ui.model

data class CoctailsDvo(
    val drinks: List<DrinkItem>
)

data class DrinkItem(
    val strDrink: String,
    val strDrinkThumb: String,
    val idDrink: String
)


