package br.ufpe.cin.android.podcast.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import br.ufpe.cin.android.podcast.ItemFeed
import br.ufpe.cin.android.podcast.R
import kotlinx.android.synthetic.main.itemlista.view.*

class ItemFeedAdapter(private val itemFeeds: List<ItemFeed>, private val context: Context):
    RecyclerView.Adapter<ItemFeedAdapter.ViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.itemlista, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val p = itemFeeds[position]

        holder.title?.text = p.title
        holder.pubDate?.text = p.pubDate
        holder.downloadLink?.text = p.downloadLink
    }

    override fun getItemCount() = itemFeeds.size

    class ViewHolder (item : View) : RecyclerView.ViewHolder(item), View.OnClickListener {
        val title = item.item_title
        val pubDate = item.item_date
        val downloadLink = item.item_action

        init {
            item.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            val position = adapterPosition
            Toast.makeText(v.context, "Clicou no item da posição: $position", Toast.LENGTH_SHORT).show()
        }
    }
}