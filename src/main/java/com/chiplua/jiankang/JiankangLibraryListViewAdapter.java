package com.chiplua.jiankang;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by chiplua_client on 15-3-17.
 */
public class JiankangLibraryListViewAdapter extends BaseAdapter {
    private static final String TAG = "JIANKANGLIBRARYLISTVIEWADAPTER";
    private static Context mContext = null;
    private List<Map<String, Object>> mListItems;
    private LayoutInflater listContainer;

    public final class ListItemView {
        public ImageView image;
        public TextView text;
        public ImageView into;
    }

    public JiankangLibraryListViewAdapter(Context context, List<Map<String, Object>> listItems) {
        mContext = context;
        mListItems = listItems;
        listContainer= LayoutInflater.from(mContext);
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getCount() {
        return mListItems.size();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ListItemView  listItemView = null;
        if (convertView == null) {
            listItemView = new ListItemView();
            convertView = listContainer.inflate(R.layout.list_item, null);

            listItemView.image = (ImageView) convertView.findViewById(R.id.image_item);
            listItemView.text = (TextView) convertView.findViewById(R.id.library_name);
            listItemView.into = (ImageView) convertView.findViewById(R.id.into_item);

            convertView.setTag(listItemView);
        } else {
            listItemView = (ListItemView)convertView.getTag();
        }

        listItemView.image.setBackgroundResource((Integer) mListItems.get(position).get("image"));
        listItemView.text.setText((Integer) mListItems.get(position).get("text"));
        listItemView.into.setBackgroundResource((Integer) mListItems.get(position).get("into"));

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                int id = v.getId();
                switch (position) {
                case 0:
                    intent.setClass(mContext, LaboratoryAssistantActivity.class);
                    mContext.startActivity(intent);
                    break;
                case 1:
                    intent.setClass(mContext, CommonDiseaseActivity.class);
                    mContext.startActivity(intent);
                    break;
                case 2:
                    intent.setClass(mContext, ChildRearingActivity.class);
                    mContext.startActivity(intent);
                    break;
                case 3:
                    intent.setClass(mContext, FavoriteActivity.class);
                    mContext.startActivity(intent);
                    break;
                }
            }
        });
        return convertView;
    }
}
