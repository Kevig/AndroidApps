package com.example.kevig.tbudget;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.parceler.Parcels;

public class ItemAddActivity extends AppCompatActivity {

    private TextView name, quantity, value;

    /**
     * Activity Initialisation
     * @param savedInstanceState Bundle object containing state information
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_add);

        this.name = findViewById(R.id.itemName_inputText);
        this.quantity = findViewById(R.id.itemQuantity_inputNumber);
        this.value = findViewById(R.id.valuePerItem_inputDecimal);
    }

    /**
     * On Add Button click functionality
     * @param view object that called this method - 'Add Item Button'
     */
    protected void onAddItem(@SuppressWarnings("UnusedParameters") View view) {

        Item item = new Item(this.getItemName(), this.getItemValue(), this.getItemQuantity());

        if(isValidItem(item)) {
            Intent intent = new Intent();
            Parcelable parcel = Parcels.wrap(item);
            intent.putExtra("Item", parcel);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    }

    /**
     * Evaluate the validity of an Item by testing none of its values are null
     * Values of an item are set to null if the input values violate the field constraints
     * @param item An object of type Item
     * @return True if Item is valid else false
     */
    private boolean isValidItem(Item item) {
        return item.getName() != null && item.getQuantity() != null && item.getValue() != null;
    }

    /**
     * Returns user input value of Item name
     * Constraints: Value cannot be the default field text or empty
     * @return A string value or null
     */
    private String getItemName() {
        String result = name.getText().toString();
        if(!result.equals("Item Name") && !result.isEmpty()) {
            return result;
        } else {
            // TODO: User must be notified value must be set and not empty
            return null;
        }
    }

    /**
     * Returns user input value of a single Item's value
     * Input field enforces double formatting
     * Constraints: Value must be 0 or greater
     * @return A Double value or Null
     */
    private Double getItemValue() {
        Double result = null;
        try {
            result = Double.parseDouble(value.getText().toString());
        } catch (NumberFormatException e) {
            // Can only occur if default input field value is not changed
            // TODO: User notification that value must be entered
        }

        if(result != null && result >= 0d) {
            return result;
        } else {
            // TODO: User must be notified value must be set and 0 or greater
            return null;
        }
    }

    /**
     * Returns user input value of an Item's quantity
     * Input field enforces integer formatting
     * Constrains: Value must be 1 or greater
     * @return An integer value or null
     */
    private Integer getItemQuantity() {
        Integer result = null;
        try {
            result = Integer.parseInt(quantity.getText().toString());
        } catch (NumberFormatException e) {
            // Can only occur if default input field value is not changed
            // TODO: User notification that value must be entered
        }

        if(result != null && result >= 1) {
            return result;
        } else {
            // TODO: User must be notified value must be set and 1 or greater
            return null;
        }
    }
}
