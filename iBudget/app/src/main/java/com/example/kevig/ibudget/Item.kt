package com.example.kevig.ibudget

class Item(aName: String,  aDescription: String, aValue: Double) {

    var name: String = aName
    var description: String = aDescription
    var value: Double = aValue

    init {
        // TODO - Possibly Infilling or changing passed values based on stored data
        // Example: changing item name spelling or filling an empty description with a pre-defined stored description
    }
}