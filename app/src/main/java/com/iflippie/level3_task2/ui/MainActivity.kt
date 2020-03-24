package com.iflippie.level3_task2.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.iflippie.level3_task2.R
import com.iflippie.level3_task2.model.Websites

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

const val ADD_WEBSITE_REQUEST_CODE = 100
class MainActivity : AppCompatActivity() {

    private val arrayWebsites = arrayListOf<Websites>()
    private val websiteAdapter = WebsitesAdapter(
        arrayWebsites,
        { plsWork: Websites -> itemClicked(plsWork) })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        initViews()
        fab.setOnClickListener {startAddActivity()
        }
    }

    private fun initViews(){
        rvWebsites.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        rvWebsites.adapter = websiteAdapter

        for (i in Websites.ALL_LINKS.indices) {
            arrayWebsites.add(
                Websites(
                    Websites.ALL_LINKS[i]
                )
            )
        }
        websiteAdapter.notifyDataSetChanged()
    }

    private fun startAddActivity() {
        val intent = Intent(this, AddActivity::class.java)
        startActivityForResult(intent,
            ADD_WEBSITE_REQUEST_CODE
        )
    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                ADD_WEBSITE_REQUEST_CODE -> {
                    val newLink = data!!.getParcelableExtra<Websites>(EXTRA_LINK)
                    arrayWebsites.add(newLink)
                    websiteAdapter.notifyDataSetChanged()
                }
            }
        }
    }

     fun itemClicked(websiteVal: Websites) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, websiteVal.theLink)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


}
