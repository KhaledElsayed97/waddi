package dev.khaled.waddi.domain.model

import dev.khaled.waddi.R

enum class Category(val value: String, val icon: Int) {
    All("all", R.drawable.ic_all),
    Food("food", R.drawable.ic_food),
    Drinks("drinks", R.drawable.ic_coffee),
    Shopping("shopping", R.drawable.ic_shopping),
    Entertainment("entertainment", R.drawable.ic_entertainment);
}