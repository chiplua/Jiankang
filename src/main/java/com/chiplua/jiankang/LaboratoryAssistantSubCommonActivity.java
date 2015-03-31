package com.chiplua.jiankang;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LaboratoryAssistantSubCommonActivity extends Activity {
    private static final String TAG = "LABORATORYASSISTANTSUBCOMMONACTIVITY";
    private static TextView titleText = null;
    private static ImageButton backButton = null;
    private static List<Map<String, Object>> listItems = null;
    private static ListView listView = null;
    private static LaboratoryAssistantCommonSubListViewAdapter laboratoryAssistantCommonSubListViewAdapter = null;
    private static String selectName = null;
    private static String lrID = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_common_second);
        this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);
        selectName = getIntent().getStringExtra("selectName");
        lrID = SQLOperation.getReportRelationMapLRID(selectName);

        Log.d(TAG, "selectName is " + selectName);
        titleText = (TextView) findViewById(R.id.title_name);
        titleText.setText(selectName);
        backButton = (ImageButton) findViewById(R.id.back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listView = (ListView) findViewById(R.id.list_items);
        listItems = getListItems(lrID);
        laboratoryAssistantCommonSubListViewAdapter = new LaboratoryAssistantCommonSubListViewAdapter(this, listItems);
        listView.setAdapter(laboratoryAssistantCommonSubListViewAdapter);
    }

    private List<Map<String, Object>> getListItems(String lr) {
        List<Map<String, Object>> sqlList = SQLOperation.getReportRelationMap(lrID);
        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> m : sqlList) {
            for (String k : m.keySet()) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("text", m.get(k));
                map.put("into", R.drawable.right_arrow);
                listItems.add(map);
                //Log.d(TAG, "key: " + k + " value: " + m.get(k));
            }
        }
        return listItems;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_common_second, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
