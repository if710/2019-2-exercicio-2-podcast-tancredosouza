package br.ufpe.cin.android.podcast

import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.ufpe.cin.android.podcast.adapters.ItemFeedAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import java.net.URL


class MainActivity : AppCompatActivity() {
    var itemFeeds: List<ItemFeed>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var itemFeeds: List<ItemFeed>

        doAsync {
            val xmlDownloadLink =
                "https://s3-us-west-1.amazonaws.com/podcasts.thepolyglotdeveloper.com/podcast.xml"

            val rssFeed = URL(xmlDownloadLink).readText()

            itemFeeds = Parser.parse(rssFeed)

            listRecyclerView.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)

                adapter = ItemFeedAdapter(itemFeeds, this@MainActivity)
            }
        }
    }
}
