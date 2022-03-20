package com.android.devthien.handlecameraandrenderimage.GridViewImage;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.android.devthien.handlecameraandrenderimage.R;

import java.util.List;

public class GridViewImage extends BaseAdapter {
    private List<Bitmap> images;
    private Context context;
    private LayoutInflater layoutInflater;

    public GridViewImage ( List<Bitmap> images, Context context){
        this.images = images;
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int i) {
        return images.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null){
            view = layoutInflater.inflate(R.layout.item_image_view, viewGroup, false);
        }
        ImageView photo = view.findViewById(R.id.imageViewItem);

        photo.setImageBitmap(images.get(i));

        return view;
    }
}
