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
import android.widget.CheckBox;

import org.parceler.Parcels;
import java.util.ArrayList;
import java.util.List;

public class TransactionViewActivity extends AppCompatActivity {

    private static final int addTransaction_RC = 1;  // Request code for Add Transaction activities
    private List<Transaction> transactionsFull;
    private AdapterTransactions transactionsAdapter; // Transactions recycler view adapter

    private boolean incomeChecked = false;
    private boolean expeditureChecked = false;

    /**
     * Activity Initialisation
     * @param savedInstanceState Bundle object containing state information
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_view);

        // Initialise Attributes
        this.transactionsFull = new ArrayList<>();
        //this.addTestDataItems();

        // Setup components
        // Transaction RecyclerView Component
        RecyclerView transactionsView = findViewById(R.id.transactions_View);
        transactionsAdapter = new AdapterTransactions(new ArrayList<Transaction>());

        RecyclerView.LayoutManager transactionsLayoutManager =
                new LinearLayoutManager(getApplicationContext());

        transactionsView.setLayoutManager(transactionsLayoutManager);
        transactionsView.setItemAnimator(new DefaultItemAnimator());
        transactionsView.setAdapter(transactionsAdapter);
    }

    /**
     * On Click addTransaction_button, Start Transaction Add Activity
     * @param view object that called this method 'Add Transaction Button'
     */
    protected void onAddTransaction(@SuppressWarnings("UnusedParameters") View view) {
        Intent intent = new Intent(this, TransactionAddActivity.class);
        startActivityForResult(intent, addTransaction_RC);
    }

    /**
     * Handler for activity callbacks
     * @param requestCode Integer value representing a specific request
     * @param resultCode Integer value representing the result of an activity call
     * @param data Returned intent from an activity started from this activity
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // Result of an Add Transaction Activity
        if(resultCode == Activity.RESULT_OK && requestCode == addTransaction_RC) {
            Parcelable parcel = data.getParcelableExtra("Transaction");
            Transaction transaction = Parcels.unwrap(parcel);
            if(transaction != null) {
                this.transactionsFull.add(transaction);
                this.applyFilter();
            }
        }
    }

    /**
     * Test data for display testing
     */
    private void addTestDataItems() {
        List<Item> emptyList = new ArrayList<>();
        Transaction a = new Transaction("11/01/2019", emptyList, TRANSACTION_TYPE.INCOME);
        Transaction b = new Transaction("21/01/2018", emptyList, TRANSACTION_TYPE.EXPENDITURE);
        Transaction c = new Transaction("15/02/2019", emptyList, TRANSACTION_TYPE.INCOME);

        this.transactionsFull.add(a);
        this.transactionsFull.add(b);
        this.transactionsFull.add(c);
    }

    /**
     * Triggers a GUI update
     */
    private void applyFilter() {
        List<Transaction> transactionsDisplay = new ArrayList<>();

        int state = this.getSelectionState();
        if(state != 0) {
            for(Transaction t: this.transactionsFull) {
                if(state == 1 && t.getTotalValue() >= 0) { transactionsDisplay.add(t); }
                if(state == 2 && t.getTotalValue() <0) { transactionsDisplay.add(t); }
                if(state == 3) { transactionsDisplay.add(t); }
            }
        }
        this.transactionsAdapter.setDataSet(transactionsDisplay);
        this.transactionsAdapter.notifyDataSetChanged();
    }

    /**
     * Determine view state based on income and expenditure check boxes
     * @return Integer representing one of the four possible states
     */
    private int getSelectionState() {
        int value;

        if(this.incomeChecked && this.expeditureChecked) { value = 3; }
        else if(!this.incomeChecked && this.expeditureChecked) { value = 2; }
        else if(this.incomeChecked) { value = 1; }
        else { value = 0; }

        return value;
    }


    /**
     * Called on an onClick check box event, determines view and sets related boolean to match
     * Calls applyFilter to trigger a display update
     * @param view Checkbox object triggering the onClick call
     */
    protected void onClickCheckBox(View view) {
        boolean isChecked = ((CheckBox) view).isChecked();

        switch(view.getId()) {
            case R.id.incomeView_checkBox:
                this.incomeChecked = isChecked;
                break;

            case R.id.expenditureView_checkBox:
                this.expeditureChecked = isChecked;
                break;
        }
        this.applyFilter();
    }

}
