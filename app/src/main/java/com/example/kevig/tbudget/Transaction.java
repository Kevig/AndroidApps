package com.example.kevig.tbudget;

import java.util.List;
import org.parceler.Parcel;

/**
 * Private fields cannot be detected during annotation that need to be serialized.
 */
@Parcel
@SuppressWarnings("WeakerAccess")
public class Transaction {

    // IdCounter is a temp measure. until persistence exists
    private static int idCounter;

    public int id;
    public String date;
    public List<Item> items;
    public Double totalValue;
    public Enum<TRANSACTION_TYPE> type;

    public Transaction() { } // Empty constructor for Parceler

    /**
     * Constructor
     * @param aDate A String representing a date
     * @param itemList A List array of Item objects
     * @param aType TRANSACTION_TYPE enum representing a Transaction type (Income or Expenditure)
     */
    Transaction(String aDate, List<Item> itemList, Enum<TRANSACTION_TYPE> aType) {
        idCounter++;
        this.id = idCounter;
        this.date = aDate;
        this.items = itemList;
        this.type = aType;
        this.updateTotalValue();
    }

    public int getId() {
        return this.id;
    }

    protected void setId(int anId) {
        this.id = anId;
    }

    public String getDate() {
        return this.date;
    }

    protected void setDate(String aDate) {
        this.date = aDate;
    }

    /**
     * Returns the total value of this transaction as a positive if Income, Negative if expenditure
     * @return Double
     */
    Double getTotalValue() {
        if(this.type == TRANSACTION_TYPE.EXPENDITURE) {
            return (0 - this.totalValue);
        } else {
            return this.totalValue;
        }
    }

    /**
     * Updates TotalValue as individual item values multiplied by their quantities
     * and rounded to 2 decimal places.
     */
    private void updateTotalValue() {
        Double value = 0.00d;
        for(Item i : this.items) {
            value += (i.getValue() * i.getQuantity());
        }
        this.totalValue = Math.round(value*100.0d) / 100.0d;
    }

    /**
     * Returns the number of items linked to this transaction
     * @return Integer size of Items array
     */
    protected int getItemsCount() {
        return this.items.size();
    }

}
