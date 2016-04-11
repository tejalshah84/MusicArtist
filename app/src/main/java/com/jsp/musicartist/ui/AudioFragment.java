package com.jsp.musicartist.ui;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jsp.musicartist.R;

/**
 * Created by Tejal Shah.
 */
public class AudioFragment extends Fragment implements View.OnClickListener {
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;
    private MediaPlayer mp3Play;
    private int prevClick=-1;

    public static AudioFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        AudioFragment fragment = new AudioFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
        getActivity().setVolumeControlStream(AudioManager.STREAM_MUSIC);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.audio_page, container, false);
        Button button1=(Button)view.findViewById(R.id.audiobutton1);
        Button button2=(Button)view.findViewById(R.id.audiobutton2);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if(i==R.id.audiobutton1){
            i=R.raw.maidflaxen;
        }
        else if(i==R.id.audiobutton2){
            i=R.raw.sleepaway;
        }

        // Create a new MediaPlayer to play this sound
        if(i==prevClick && mp3Play!=null){
            mp3Play.stop();
            prevClick=-1;
        }
        else {
            if (mp3Play != null) {
                mp3Play.release();
            }
            mp3Play = MediaPlayer.create(getContext(), i);
            mp3Play.start();
            prevClick=i;
        }
    }

    @Override
    public void onDestroy() {
        if(null!=mp3Play){
            mp3Play.release();
        }
        super.onDestroy();
    }
}
