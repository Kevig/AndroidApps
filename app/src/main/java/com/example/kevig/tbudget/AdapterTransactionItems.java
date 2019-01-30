package com.example.kevig.tbudget;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AdapterTransactionItems extends
        RecyclerView.Adapter<AdapterTransactionItems.TransactionItemsViewHolder> {

    private List<Item> dataSet;

    /**
     * Nested ViewHolder Class, defines views for a single transaction item
     */
    public static class TransactionItemsViewHolder extends RecyclerView.ViewHolder {

        public TextView name, value, quantity;

        /**
         * Constructor
         * @param view Object containing the individual data views
         */
        TransactionItemsViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            value = view.findViewById(R.id.value);
            quantity = view.findViewById(R.id.quantity);
        }
    }

    /**
     * Constructor
     * @param items A list of Item objects
     */
    AdapterTransactionItems(List<Item> items) {
        this.dataSet = items;
    }

    /**
     * Called after adapter creation to initialise ViewHolder
     * @param parent ViewHolder for individual views with each holding an attribute of Item
     * @param viewType Integer reference to the specific view within the ViewHolder
     * @return Custom ViewHolder object
     */
    @NonNull
    @Override
    public TransactionItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.transaction_items_view, parent, false);
        return new TransactionItemsViewHolder(itemView);
    }

    /**
     * Binds data from this objects dataSet to its View components
     * @param holder ViewHolder object representing this set of data
     * @param position Index value of dataSet array
     */
    @Override
    public void onBindViewHolder(@NonNull TransactionItemsViewHolder holder, int position) {
        Item item = dataSet.get(position);

        String value = "£" + String.format("%.2f", item.getValue());
        String quantity = item.getQuantity().toString();

        holder.name.setText(item.getName());
        holder.quantity.setText(quantity);
        holder.value.setText(value);
    }

    /**
     * Returns a value representing the number of items referenced by this transaction
     * @return Integer
     */
    @Override
    public int getItemCount() {
        return this.dataSet.size();
    }
}
