package br.ufpe.cin.android.podcast

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.itemlista.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.sdk27.coroutines.onClick
import java.net.URL


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.itemlista)

        item_action.onClick {
            doAsync {
                val xmlDownloadLink =
                    "https://s3-us-west-1.amazonaws.com/podcasts.thepolyglotdeveloper.com/podcast.xml"

                val rssFeed = URL(xmlDownloadLink).readText()

                val itemFeed = Parser.parse(rssFeed)

                println(itemFeed.size)
            }
        }
    }
}
