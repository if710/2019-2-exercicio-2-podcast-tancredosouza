package br.ufpe.cin.android.podcast.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import br.ufpe.cin.android.podcast.EpisodeDetailActivity
import br.ufpe.cin.android.podcast.ItemFeed
import br.ufpe.cin.android.podcast.R

class ItemFeedsAdapter(private val itemFeeds: List<ItemFeed>, private val context: Context):
    RecyclerView.Adapter<ItemFeedsAdapter.ItemFeedViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemFeedViewHolder {
        val itemFeedViewInflater =
            LayoutInflater.from(context).inflate(R.layout.itemlista, parent, false)

        return ItemFeedViewHolder(itemFeedViewInflater)
    }

    override fun onBindViewHolder(holder: ItemFeedViewHolder, position: Int) {
        val itemFeed = itemFeeds[position]

        holder.bind(itemFeed, context)
    }

    override fun getItemCount() = itemFeeds.size

    class ItemFeedViewHolder (item: View) : RecyclerView.ViewHolder(item), View.OnClickListener {
        var podcastTitleView: TextView? = null
        var podcastPubDateView: TextView? = null
        var podcastDownloadLinkView: Button? = null


        init {
            podcastTitleView = itemView.findViewById(R.id.item_title)
            podcastPubDateView = itemView.findViewById(R.id.item_date)
            podcastDownloadLinkView = itemView.findViewById(R.id.item_action)
            item.setOnClickListener(this)
        }

        fun bind(itemFeed : ItemFeed, context: Context) {
            podcastTitleView?.text = itemFeed.title
            podcastPubDateView?.text = itemFeed.pubDate

            podcastDownloadLinkView!!.setOnClickListener {
                Toast.makeText(context, "Baixando link = ${itemFeed.downloadLink}", Toast.LENGTH_SHORT).show()
            }
        }

        override fun onClick(v: View) {
            val intent = Intent(v.context, EpisodeDetailActivity::class.java)
            startActivity(v.context, intent, null)
        }
    }
}