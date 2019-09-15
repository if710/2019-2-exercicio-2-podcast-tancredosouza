package br.ufpe.cin.android.podcast

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import br.ufpe.cin.android.podcast.adapters.ItemFeedsAdapter
import br.ufpe.cin.android.podcast.database.ItemFeedsDatabase
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL


class MainActivity : AppCompatActivity() {
    var itemFeeds: List<ItemFeed>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        doAsync {
            val rssFeed = downloadXMLFile()

            saveToDatabase(Parser.parse(rssFeed))

            itemFeeds = getFromDatabase()

            uiThread {
                setupRecyclerView()
            }
        }
    }

    fun downloadXMLFile(): String {
        val xmlDownloadLink = getString(R.string.download_link)

        return URL(xmlDownloadLink).readText()
    }


    fun saveToDatabase(itemFeeds: List<ItemFeed>?) {
        val database = ItemFeedsDatabase.getDatabase(this@MainActivity)

        itemFeeds!!.forEach {
                itemFeed -> database.itemFeedsDao().insertItemFeeds(itemFeed)
        }
    }

    fun getFromDatabase(): List<ItemFeed> {
        val database = ItemFeedsDatabase.getDatabase(this@MainActivity)

        return database.itemFeedsDao().getAllItemFeeds()
    }

    fun setupRecyclerView() {
        listRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        listRecyclerView.adapter = ItemFeedsAdapter(itemFeeds!!, this@MainActivity)
        listRecyclerView.addItemDecoration(
            DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL))
    }
}
