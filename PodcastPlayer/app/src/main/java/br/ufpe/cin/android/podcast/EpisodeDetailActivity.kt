package br.ufpe.cin.android.podcast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class EpisodeDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_episode_detail)

        val episodeTitle = findViewById<TextView>(R.id.episodeName)

        episodeTitle.text = intent.getStringExtra("item_title")
    }
}
