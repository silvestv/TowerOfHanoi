package org.sadok.TowerOfHanoi;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {
    Context context;
    int img[];
    String[] backGroundNames;
    LayoutInflater inflter;

    public CustomAdapter(Context applicationContext, int[] img, String[] backGroundNames) {
        this.context = applicationContext;
        this.img = img;
        this.backGroundNames = backGroundNames;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return img.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.row, null);
        ImageView icon = (ImageView) view.findViewById(R.id.icon);
        TextView names = (TextView) view.findViewById(R.id.backGround);
        icon.setImageResource(img[i]);
        names.setText(backGroundNames[i]);
        return view;
    }
}