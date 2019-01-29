package com.example.kevig.tbudget;

import org.parceler.Parcel;

/**
 * Private fields cannot be detected during annotation that need to be serialized.
 */
@Parcel
public class Item {

    public String name;
    public Double value;
    public Integer quantity;

    public Item() { } // Empty constructor for Parceler Library

    Item(String name, Double value, Integer quantity) {
        this.name = name;
        this.value = value;
        this.quantity = quantity;
    }

    public String getName() {
        return this.name;
    }

    protected void setName(String aName) {
        this.name = aName;
    }

    public Double getValue() {
        return this.value;
    }

    protected void setValue(Double aValue) {
        this.value = aValue;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    protected void setQuantity(int aValue) {
        this.quantity = aValue;
    }
}
