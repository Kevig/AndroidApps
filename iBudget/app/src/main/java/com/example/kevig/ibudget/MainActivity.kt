package com.example.kevig.ibudget

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App()
        setContentView(R.layout.activity_main)
    }

    /** Called on use of 'New Transaction Button' */
    fun onNewTransaction(view: View) {
        val intent = Intent(this, NewTransactionActivity::class.java)
        startActivity(intent)
    }
}
