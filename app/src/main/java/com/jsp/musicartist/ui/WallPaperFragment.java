package com.jsp.musicartist.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.jsp.musicartist.R;

import java.util.ArrayList;

/**
 * Created by Tejal Shah.
 */
public class WallPaperFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;
    private ArrayList<ImageItem> imageList;

    public static WallPaperFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        WallPaperFragment fragment = new WallPaperFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wallpaper_page, container, false);
        ListView imageGallery = (ListView) view.findViewById(R.id.imagelist);
        createImageList();
        imageGallery.setAdapter(new CustomImageListAdapter(getContext(),imageList));
        return view;
    }

    public void createImageList(){
        imageList = new ArrayList<ImageItem>();
        imageList.add(new ImageItem(R.drawable.shakira1));
        imageList.add(new ImageItem(R.drawable.shakira2));
        imageList.add(new ImageItem(R.drawable.shakira3));
        imageList.add(new ImageItem(R.drawable.shakira4));
        imageList.add(new ImageItem(R.drawable.shakira5));
        imageList.add(new ImageItem(R.drawable.shakira6));
        imageList.add(new ImageItem(R.drawable.shakira7));
    }


    public class CustomImageListAdapter extends ArrayAdapter<ImageItem> {


        public CustomImageListAdapter(Context context, ArrayList<ImageItem> imageId) {
            super(context, android.R.layout.simple_list_item_1,imageId);
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {

            ImageItem i = getItem(position);
            if(view == null) {
                view = LayoutInflater.from(getContext()).inflate(R.layout.wallpaper_item, parent, false);
            }
            ImageView picture = (ImageView)view.findViewById(R.id.img);
            picture.setImageResource(i.image);
            return view;
        }
    }

    private class ImageItem{
        int image;
        protected ImageItem(int id){
            image = id;
        }
    }
}
