package com.example.kevig.tbudget;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class AdapterTransactions extends
        RecyclerView.Adapter<AdapterTransactions.TransactionViewHolder> {

    private List<Transaction> dataSet;

    static class TransactionViewHolder extends RecyclerView.ViewHolder {
        TextView id, date, value, name; // Layout view attributes

        /**
         * Constructor
         * @param view Object containing the individual data views
         */
        TransactionViewHolder(View view) {
            super(view);
            id = view.findViewById(R.id.id);
            date = view.findViewById(R.id.date);
            value = view.findViewById(R.id.value);
            name = view.findViewById(R.id.name);
        }
    }

    /**
     * Constructor
     * @param transactions List of Transaction Objects
     */
    AdapterTransactions(List<Transaction> transactions) {
        this.dataSet = transactions;
    }

    /**
     * Called after adapter creation to initialise ViewHolder
     * @param parent ViewHolder for individual views with each holding an attribute of Item
     * @param viewType Integer reference to the specific view within the ViewHolder
     * @return Custom ViewHolder object
     */
    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View transactionView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.transactions_view, parent, false);
        return new TransactionViewHolder(transactionView);
    }

    /**
     * Binds data from this objects dataSet to its View components
     * @param holder ViewHolder object representing this set of data
     * @param position Index value of dataSet array
     */
    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        Transaction transaction = dataSet.get(position);

        String id = "" + transaction.getId();
        String value = "£" + String.format( Locale.getDefault(),"%.2f",
                                            transaction.getTotalValue());

        Objects.requireNonNull(holder).id.setText(id);
        Objects.requireNonNull(holder).date.setText(transaction.getDate());
        Objects.requireNonNull(holder).value.setText(value);
        Objects.requireNonNull(holder).name.setText(transaction.getName());
    }

    /**
     * Returns a value representing the number of transactions referenced by this view
     * @return Integer
     */
    @Override
    public int getItemCount() {
        return this.dataSet.size();
    }

    void setDataSet(List<Transaction> dataSet) { this.dataSet = dataSet; }
}
