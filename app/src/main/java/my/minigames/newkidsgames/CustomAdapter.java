package my.minigames.newkidsgames;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import my.minigames.newkidsgames.R;


public class CustomAdapter extends BaseAdapter {
    Context context;
    int logos[];
    String names[];
    String links[];
    LayoutInflater inflter;
    public CustomAdapter(Context applicationContext, int[] logos, String[] names, String[] links) {
        this.context = applicationContext;
        this.logos = logos;
        this.names=names;
        this.links=links;
        inflter = (LayoutInflater.from(applicationContext));
    }
    @Override
    public int getCount() {
        return logos.length;
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
        view = inflter.inflate(R.layout.view_grid_item, null); // inflate the layout
        TextView textView = (TextView) view.findViewById(R.id.textView);
        ImageView icon = (ImageView) view.findViewById(R.id.icon); // get the reference of ImageView
        icon.setImageResource(logos[i]); // set logo images
        textView.setText(names[i]);
        return view;
    }
}
