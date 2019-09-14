package br.ufpe.cin.android.podcast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.ufpe.cin.android.podcast.database.ItemFeedsDatabase
import kotlinx.android.synthetic.main.activity_episode_detail.*
import org.jetbrains.anko.doAsync

class EpisodeDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_episode_detail)

        episodeName.text = intent.getStringExtra("item_title")
        doAsync {
            val database = ItemFeedsDatabase.getDatabase(this@EpisodeDetailActivity)

            val itemFeed = database.itemFeedsDao().getItemFeed(episodeName.text!!.toString())

            episodePubDate.text = itemFeed.pubDate
            episodeDescription.text = itemFeed.description
        }
    }
}
