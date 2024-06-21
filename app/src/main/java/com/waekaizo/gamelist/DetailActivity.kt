package com.waekaizo.gamelist

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import java.util.Objects

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val tvDetailName: TextView = findViewById(R.id.tv_game_name)
        val tvDetailDescription: TextView = findViewById(R.id.tv_detail_description)
        val ivDetailPhoto: ImageView = findViewById(R.id.img_detail)
        val btnShare: Button = findViewById(R.id.action_share)


        val dataGame = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Game>("key_game", Game::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Game>("key_game")
        }

        if (dataGame != null) {
            tvDetailName.text = dataGame.name
            tvDetailDescription.text = dataGame.description
            ivDetailPhoto.setImageResource(dataGame.photo)
        }

        btnShare.setOnClickListener{
            val share = Intent(Intent.ACTION_SEND)
            share.type = "text/plain"
            if (dataGame != null) {
                share.putExtra(Intent.EXTRA_TEXT, dataGame.description)
            }
            startActivity(Intent.createChooser(share, "Share to :"))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.about_me -> {
                val moveIntent = Intent(this@DetailActivity, AboutMe::class.java)
                startActivity(moveIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}