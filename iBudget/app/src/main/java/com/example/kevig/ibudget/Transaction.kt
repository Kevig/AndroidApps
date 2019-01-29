package com.example.kevig.ibudget

import org.threeten.bp.LocalDateTime

class Transaction( aName: String , itemsMap: Map<Item,Int> ) {

    /**
     * id           - TODO - Requires persistence
     * name         - Possibly temporary - User defined attribute
     * dateCreated  - Local date of creation of transaction instance
     * items        - Map containing Item's and quantities
     */

    var name: String = aName
    var items: Map<Item, Int> = itemsMap
    var dateCreated: String = ""

    init {
        val dt = LocalDateTime.now().toString()
        dateCreated = dt.substring(0,dt.indexOf("T" ))
    }

}