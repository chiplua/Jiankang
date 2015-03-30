package com.chiplua.jiankang;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by chiplua_client on 15-3-30.
 */
public class ChildRearingListViewAdapter extends BaseAdapter {
    private static final String TAG = "ChildRearingListViewAdapter";
    private static Context mContext = null;
    private List<Map<String, Object>> mListItems;
    private LayoutInflater listContainer;

    public final class ListItemView {
        public TextView text;
        public ImageView into;
    }

    public ChildRearingListViewAdapter(Context context, List<Map<String, Object>> listItems) {
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
            convertView = listContainer.inflate(R.layout.list_item_library, null);

            listItemView.text = (TextView) convertView.findViewById(R.id.library_name);
            listItemView.into = (ImageView) convertView.findViewById(R.id.into_item);

            convertView.setTag(listItemView);
        } else {
            listItemView = (ListItemView)convertView.getTag();
        }

        listItemView.text.setText((String) mListItems.get(position).get("text"));
        listItemView.into.setBackgroundResource((Integer) mListItems.get(position).get("into"));

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                int id = v.getId();
                String selectName = (String)mListItems.get(position).get("text");
                String childID;
                Log.d(TAG, "U pressed is " + selectName);
                Log.d(TAG, "id = " + id + " position = " + position);
                childID = SQLOperation.getChildRearingRelationMapOutlineID(selectName);
                Log.d(TAG, "childID is " + childID);

                Intent intentCommon = new Intent();
                intentCommon.putExtra("selectName", selectName);
                intentCommon.setClass(mContext, ChildRearingCommonSecondActivity.class);
                mContext.startActivity(intentCommon);
            }
        });
        return convertView;
    }
}