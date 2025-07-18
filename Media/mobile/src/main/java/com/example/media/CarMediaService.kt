package com.example.media


import android.os.Bundle
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaDescriptionCompat
import android.support.v4.media.session.MediaSessionCompat
import androidx.media.MediaBrowserServiceCompat
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ext.mediasession.MediaSessionConnector

class CarMediaService : MediaBrowserServiceCompat() {

    private lateinit var session: MediaSessionCompat
    private lateinit var connector: MediaSessionConnector
    private lateinit var player: ExoPlayer

    override fun onCreate() {
        super.onCreate()
        player = ExoPlayer.Builder(this).build()
        session = MediaSessionCompat(this, "CarMediaService").apply { isActive = true }
        sessionToken = session.sessionToken

        connector = MediaSessionConnector(session).apply {
            setPlayer(player)
        }

        val mediaItem = MediaItem.fromUri("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3")
        player.setMediaItem(mediaItem)
        player.prepare()
    }

    override fun onGetRoot(
        clientPackageName: String,
        clientUid: Int,
        rootHints: Bundle?
    ): BrowserRoot? {
        return BrowserRoot("root",null)
    }

    override fun onLoadChildren(
        parentId: String,
        result: Result<MutableList<MediaBrowserCompat.MediaItem>>
    ) {
        val desc = MediaDescriptionCompat.Builder()
            .setMediaId("1")
            .setTitle("Sample Track")
            .setSubtitle("By Rakshita")
            .build()

        result.sendResult(mutableListOf(
            MediaBrowserCompat.MediaItem(desc, MediaBrowserCompat.MediaItem.FLAG_PLAYABLE)
        ))
    }

    override fun onDestroy() {
        player.release()
        session.release()
        super.onDestroy()
    }
}