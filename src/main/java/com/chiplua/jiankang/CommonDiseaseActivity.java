package com.chiplua.jiankang;

import android.app.Activity;
import android.os.Bundle;
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


public class CommonDiseaseActivity extends Activity {
    private static TextView commonIllText = null;
    private static ImageButton backButton = null;
    private static ListView listView = null;
    private static List<Map<String, Object>> listItems = null;
    private static CommonDiseaseListViewAdapter commonDiseaseListViewAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.common_list_item_adapter);
        this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);

        commonIllText = (TextView) findViewById(R.id.title_name);
        commonIllText.setText(R.string.common_illness);
        backButton = (ImageButton) findViewById(R.id.back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listView = (ListView) findViewById(R.id.list_item_library);
        listItems = getListItems();
        commonDiseaseListViewAdapter = new CommonDiseaseListViewAdapter(this, listItems);
        listView.setAdapter(commonDiseaseListViewAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_common_illness, menu);
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

    private static List<Map<String, Object>> getListItems() {
        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
        for(int i = 0; i < SQLOperation.getCommonDiseaseCount(); i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("text", SQLOperation.getCommonDiseaseName(String.valueOf(i+1)));
            map.put("into", R.drawable.right_arrow);
            listItems.add(map);
        }
        return listItems;
    }
}
