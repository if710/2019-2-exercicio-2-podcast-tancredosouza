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
            val xmlDownloadLink =
                "https://s3-us-west-1.amazonaws.com/podcasts.thepolyglotdeveloper.com/podcast.xml"

            val rssFeed = URL(xmlDownloadLink).readText()

            saveToDatabase(Parser.parse(rssFeed))

            itemFeeds = getFromDatabase()

                uiThread {
                listRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                listRecyclerView.adapter = ItemFeedsAdapter(itemFeeds!!, this@MainActivity)
                listRecyclerView.addItemDecoration(
                    DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL))
            }
        }
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
}
