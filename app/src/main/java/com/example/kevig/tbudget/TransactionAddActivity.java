package com.example.kevig.tbudget;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CompoundButton;

import java.util.ArrayList;
import java.util.List;

import org.parceler.Parcels;

import android.widget.TextView;
import android.widget.ToggleButton;


public class TransactionAddActivity extends AppCompatActivity {

    private static final int addItem_RC = 2;        // Request code for Add Item activities
    private List<Item> transactionItems;            // Transaction Items List
    private AdapterTransactionItems tItemsAdapter;  // Data to view's adapter
    private boolean transactionType = false;        // false=Off/Income | true=On/Expenditure
    private TextView transactionDateView;           // Transaction Date text component

    /**
     * Activity Initialisation
     * @param savedInstanceState Bundle object containing state information
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_add);

        // Initialise Attributes
        this.transactionItems = new ArrayList<>();
        this.addTestDataItems(); // Add Test Values

        // Transaction Items View Component
        RecyclerView tItemsView = findViewById(R.id.transactionItems_View);
        tItemsAdapter = new AdapterTransactionItems(this.transactionItems);

        RecyclerView.LayoutManager tItemsLayoutManager =
                new LinearLayoutManager(getApplicationContext());

        tItemsView.setLayoutManager(tItemsLayoutManager);
        tItemsView.setItemAnimator(new DefaultItemAnimator());
        tItemsView.setAdapter(tItemsAdapter);

        // Transaction type toggle and event listener
        ToggleButton transactionTypeToggle = findViewById(R.id.transactionType_toggleButton);
        this.transactionTypeToggleEvent(transactionTypeToggle);

        // Transaction Date datePicker components
        transactionDateView = findViewById(R.id.transactionDate_datePicker);
        new ComponentDatePicker(TransactionAddActivity.this, transactionDateView);
    }

    /**
     * On click 'addItem_button', Start Item Add Activity
     * @param view object that called this method 'Add Item Button'
     */
    protected void onAddItem(@SuppressWarnings("UnusedParameters") View view) {
        Intent intent = new Intent(this, ItemAddActivity.class);
        startActivityForResult(intent, addItem_RC);
    }

    /**
     * On click 'completeTransaction_button' complete this Activity and return to caller
     * @param view object that called this method 'Complete Transaction Button'
     */
    protected void onCompleteTransaction(@SuppressWarnings("UnusedParameters") View view) {

        Enum<TRANSACTION_TYPE> type =   transactionType ?
                                        TRANSACTION_TYPE.EXPENDITURE : TRANSACTION_TYPE.INCOME;

        Transaction transaction = new Transaction(  transactionDateView.getText().toString(),
                                                    this.transactionItems,
                                                    type);

        if(isValidTransaction(transaction)) {
            Intent intent = new Intent();
            Parcelable parcel = Parcels.wrap(transaction);
            intent.putExtra("Transaction", parcel);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    }

    /**
     * Called upon 'completion' of an activity started from this activity to handle results
     * @param requestCode Integer value representing a specific request
     * @param resultCode Integer value representing the result of an activity call
     * @param data Returned intent from an activity started from this activity
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // Result of an Add Item Activity
        if(resultCode == Activity.RESULT_OK && requestCode == addItem_RC) {
            Parcelable parcel = data.getParcelableExtra("Item");
            Item item = Parcels.unwrap(parcel);
            if(item != null) {
                this.transactionItems.add(item);
                this.updateDisplay();
            }
        }
    }

    /**
     * Test Data for Item display
     */
    private void addTestDataItems() {
        Item a = new Item("Test1", 0.27d, 1);
        Item b = new Item("Test2", 9.99d, 5);
        Item c = new Item("Test3", 7.82d, 2);

        this.transactionItems.add(a);
        this.transactionItems.add(b);
        this.transactionItems.add(c);
    }

    /**
     * Triggers a GUI update
     */
    private void updateDisplay() {
        this.tItemsAdapter.notifyDataSetChanged();
    }

    /**
     * Evaluate the validity of a Transaction by testing that its values are not null
     * and meet certain requirements
     * Constrains:  A valid transaction must have at least 1 valid item
     *              Date attribute cannot be null and is of a predefined format (DD/MM/YYYY)
     * @param transaction Transaction Object
     * @return Boolean
     */
    private boolean isValidTransaction(@SuppressWarnings("UnusedParameters") Transaction transaction) { //TODO: Remove Unused Suppression
        return true;
    }

    /**
     * Toggle Button - Income / Expenditure event listener
     * @param toggleButton View component
     */
    private void transactionTypeToggleEvent(ToggleButton toggleButton) {
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                transactionType = isChecked;
            }
        });
    }

}
