package com.example.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity(),OnFragmentDataListener {

    private lateinit var toolbarTB:Toolbar

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbarTB = findViewById(R.id.toolbarTB)
        setSupportActionBar(toolbarTB)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_main,MainFragment())
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.exitItem -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onData(data: String?) {
        val bundle = Bundle()
        bundle.putString("key",data)

        val transaction = this.supportFragmentManager.beginTransaction()
        val detailsFragment = DetailsFragment()
        detailsFragment.arguments = bundle

        transaction.replace(R.id.fragment_main,detailsFragment)
        transaction.addToBackStack(null)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.commit()
    }
}