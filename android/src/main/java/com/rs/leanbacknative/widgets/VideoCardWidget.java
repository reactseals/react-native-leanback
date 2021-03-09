package com.rs.leanbacknative.widgets;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.VideoView;

public class VideoCardWidget extends VideoView {
    private MediaPlayer mMediaPlayer;

    public VideoCardWidget(Context context) {
        super(context);
    }

    public VideoCardWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setupMediaPlayer(String url, final OnVideoReadyListener onVideoReadyListener) {
        try {
            setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mMediaPlayer = mp;
                    mMediaPlayer.setLooping(true);
                    mMediaPlayer.setVolume(0, 0);
                    onVideoReadyListener.onVideoReady();
                }
            });
            setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    onVideoReadyListener.onVideoError();
                    return false;
                }
            });
            setVideoURI(Uri.parse(url));
        } catch (Exception e) {}
    }

    public void stopPlayback() {
        if (mMediaPlayer != null) {
            mMediaPlayer.pause();
        }
    }
    public void starPlayback() {
        if (mMediaPlayer != null) {
            mMediaPlayer.start();
        }
    }

    public interface OnVideoReadyListener {
        void onVideoReady();
        void onVideoError();
    }
}
