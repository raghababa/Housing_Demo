package com.example.dttproject.navigation

import com.example.dttproject.R

sealed class BottomNavigationItem(var icon:Int, var screen_route:String
                            ){

    object Home : BottomNavigationItem( R.drawable.ic_home,"home")
    object Information: BottomNavigationItem(R.drawable.ic_info,"information")
}