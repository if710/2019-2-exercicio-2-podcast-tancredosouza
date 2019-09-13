package br.ufpe.cin.android.podcast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val a = Parser.parse(
"<rss version=\"2.0\">\n" +
            "<channel>\n" +
                "<title>W3Schools Home Page</title>\n" +
                "<link>http://www.w3schools.com</link>\n" +
                "<description>Free web building tutorials</description>\n" +
                "<item>\n" +
                    "<title>RSS Tutorial</title>\n" +
                    "<link>http://www.w3schools.com/rss</link>\n" +
                    "<pubDate>Thu, 12 Sep 2019 21:21:22 -0300</pubDate>\n" +
                    "<description>New RSS tutorial on W3Schools</description>\n" +
                    "<url>https://16583.mc.tritondigital.com/TOCA_E_SAI_P/media-session/d053a8a6-67b9-439e-b026-1b886b73eb27/audios/encodeds/11/2019/08/29/272718_20190829.mp3</url>\n" +
                "</item>\n" +
            "</channel>\n" +
        "</rss>"
        )

        val textView = findViewById<TextView>(R.id.olar)

        textView.text = a.toString()
    }
}
