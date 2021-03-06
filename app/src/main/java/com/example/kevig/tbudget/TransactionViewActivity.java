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
import android.widget.Button;
import org.parceler.Parcels;
import java.util.ArrayList;
import java.util.List;

public class TransactionViewActivity extends AppCompatActivity {

    private static final int addTransaction_RC = 1;  // Request code for Add Transaction activities
    private List<Transaction> transactionsFull;
    private AdapterTransactions transactionsAdapter; // Transactions recycler view adapter
    private int viewType = 0;

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
     * Applies view filter to display data and triggers a GUI update
     */
    private void applyFilter() {
        List<Transaction> transactionsDisplay = new ArrayList<>();
        for(Transaction t: this.transactionsFull) {
            if(this.viewType == 0) { transactionsDisplay.add(t); }
            if(this.viewType == 1 && t.getTotalValue() >= 0) { transactionsDisplay.add(t); }
            if(this.viewType == 2 && t.getTotalValue() <  0) { transactionsDisplay.add(t); }

        }
        this.transactionsAdapter.setDataSet(transactionsDisplay);
        this.transactionsAdapter.notifyDataSetChanged();
    }

    /**
     * Called on an onClick event, determines view and sets related int attribute to state value
     * Calls applyFilter to trigger a display update
     * @param view Button object triggering the onClick call
     */
    protected void onClickToggleView(View view) {
        this.viewType++;
        if(this.viewType == 3) { this.viewType = 0; }

        String value = "All";
        if(this.viewType == 1) { value = "Income"; }
        if(this.viewType == 2) { value = "Expenditure"; }

        ((Button) view).setText(value);
        this.applyFilter();
    }

    /**
     * Called on an onClick event of Home button, returns to Home screen
     * @param view Button object triggering the onClick call
     */
    protected void onClickHomeButton(@SuppressWarnings("UnusedParameters") View view) {
        Intent intent = new Intent();
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

}
