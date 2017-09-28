package com.gwmobile.metroexplorer
import java.util.ArrayList

object PlaceData {

    var placeNameArray = arrayOf("Bora Bora", "Canada", "Dubai", "Hong Kong", "Iceland", "India", "Kenya", "London", "Switzerland", "Sydney")
    var MetroArray = arrayOf("Addison Road-Seat Pleasant","Anacostia","Archives-Navy Memorial-Penn Quarter ","Arlington Cementary", "Ballston", "Bennig Road", "bethesda","braddockRoad"
            ,"brach Ave","Brookland-CUA", "Capitol Heights", "Capitol South","Cheverly","Clarendon","Cleveland Park","Dupont Cirlce")

    fun placeList(): ArrayList<Place> {
        val list = ArrayList<Place>()
        for (i in placeNameArray.indices) {
            var isFav = false
            if (i == 2 || i == 5) {
                isFav = true
            }
            val place = Place(placeNameArray[i], placeNameArray[i].replace("\\s+".toRegex(), "").toLowerCase(), isFav)
            list.add(place)
        }
        return list
    }
}