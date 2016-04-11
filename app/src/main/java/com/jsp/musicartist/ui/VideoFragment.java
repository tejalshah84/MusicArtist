package com.jsp.musicartist.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import com.jsp.musicartist.R;

/**
 * Created by Tejal Shah.
 */
public class VideoFragment extends Fragment implements View.OnClickListener {
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;
    private String pkgName;
    private VideoView video;
    private Button b1, b2;
    private int prevClick=-1;
    private Button prevView;
    private View placeholder;
    private DisplayMetrics dm;
    private int vid;
    MediaController media_Controller;


    public static VideoFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        VideoFragment fragment = new VideoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
        pkgName = getContext().getPackageName();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.video_page, container, false);
        video = (VideoView) view.findViewById(R.id.video_player_view);
        b1 = (Button) view.findViewById(R.id.video1_button);
        b2 = (Button) view.findViewById(R.id.video2_button);
        //placeholder = (View) view.findViewById(R.id.placeholder_img);
        vid = R.raw.small;
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        return view;
    }

    public void initializeVideo(Uri paath){
        media_Controller = new MediaController(getActivity());
        dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int height = dm.heightPixels; int width = dm.widthPixels;
        video.setMinimumWidth(width);
        video.setMinimumHeight(height);
        video.setMediaController(media_Controller);
        video.setVideoURI(paath);
        video.start();
    }

    @Override
    public void onClick(View v) {
        vid = v.getId();

        if(vid==R.id.video1_button){
            vid=R.raw.small;
        }

        if(vid==R.id.video2_button){
            vid=R.raw.bunny;
        }

        Uri paath = Uri.parse("android.resource://" + pkgName + "/" + vid);
        initializeVideo(paath);

    }
}
