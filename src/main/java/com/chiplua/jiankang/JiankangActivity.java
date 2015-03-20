package com.chiplua.jiankang;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
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


public class JiankangActivity extends Activity {
    public final static String PACKAGE_NAME = "com.chiplua.jiankang";
    private ListView libraryList = null;
    private static ImageButton backButton = null;
    private static TextView titleName = null;
    private LayoutInflater inflater = null;
    private LibraryListViewAdapter listViewAdapter = null;
    private List<Map<String, Object>> listItems;
    private String[] libraryName = {"化验单助手", "常见病介绍", "育儿介绍", "收藏夹"};
    private Integer[] imgIDs = {R.drawable.icon_test, R.drawable.icon_common_ill,
                                R.drawable.icon_child, R.drawable.icon_favorite};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_jiankang);
        this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);

        libraryList = (ListView) findViewById(R.id.library_list);
        listItems = getListItems();
        listViewAdapter = new LibraryListViewAdapter(this, listItems);
        libraryList.setAdapter(listViewAdapter);

        backButton = (ImageButton) findViewById(R.id.back);
        backButton.setVisibility(View.GONE);
        titleName = (TextView) findViewById(R.id.title_name);
        titleName.setText(R.string.healty_library);
    }


    private List<Map<String, Object>> getListItems(){
        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < libraryName.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", imgIDs[i]);
            map.put("text", libraryName[i]);
            map.put("into", R.drawable.right_arrow);
            listItems.add(map);
        }
        return listItems;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_jiankang, menu);
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
