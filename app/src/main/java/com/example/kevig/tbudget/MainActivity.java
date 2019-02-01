package com.example.kevig.tbudget;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final int viewTransactions_RC = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * On Click transactionsActivity Button, Start Transaction View Activity
     * @param view
     */
    protected void onViewTransactions(View view) {
        Intent intent = new Intent(this, TransactionViewActivity.class);
        startActivityForResult(intent, viewTransactions_RC);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
}
