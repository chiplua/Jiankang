package com.chiplua.jiankang;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LaboratoryAssistantActivity extends Activity {
    private final static String TAG = "LaboratoryAssistantActivity";
    private static TextView titleText = null;
    private static ImageButton backButton = null;
    private static ListView listView = null;
    private static List<Map<String, Object>> listItems = null;
    private static ListViewAdapter listViewAdapter = null;

    private final static String DATABASE_NAME = "health_library.db";
    private final static String DATABASE_PATH = "/data/data/" + JiankangActivity.PACKAGE_NAME + "/databases";
    private final static String ID = "id";
    private final static String NAME = "name";
    private final static String SORT = "sort";
    private final static String ORDERNO = "orderNo";

    private static SQLiteDatabase openDatabase() {
        File dir = new File(DATABASE_PATH);
        if (!dir.exists()) {
            dir.mkdir();
        }

        try {
            return SQLiteDatabase.openOrCreateDatabase(DATABASE_PATH + "/" + DATABASE_NAME, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.common_list_item_adapter);
        this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);

        titleText = (TextView) findViewById(R.id.title_name);
        titleText.setText(R.string.laboratory_assistant);
        backButton = (ImageButton) findViewById(R.id.back);
        //backButton.setVisibility(View.GONE);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        listView = (ListView) findViewById(R.id.list_item_library);
        listItems = getListItems();
        listViewAdapter = new ListViewAdapter(this, listItems);
        listView.setAdapter(listViewAdapter);

        Log.d(TAG, "the package name is " + DATABASE_PATH);
        openDatabase();
      /*  for (int i = 1; i < 31; i++) {
            String orderNO = String.valueOf(i);
            getName(orderNO);
        }*/

        getLaboratorAssistantCount();
    }


    public static String[] getName(String orderNo) {
        SQLiteDatabase db = null;
        Cursor c = null;
        String[] data = null;
        try {
            db = openDatabase();
            if (db == null)
                return data;
            String sql = "select * from report_outline where orderNo='"+ orderNo +"'";
            c = db.rawQuery(sql, null);
            if (c.getCount() > 0 && c.moveToFirst()) {
                int nameIndex;
                int sortIndex;
                int idIndex;
                try {
                    nameIndex = c.getColumnIndexOrThrow(NAME);
                    sortIndex = c.getColumnIndexOrThrow(SORT);
                    idIndex = c.getColumnIndexOrThrow(ORDERNO);
                } catch (IllegalArgumentException e1) {
                    nameIndex = 2;
                    sortIndex = 3;
                    idIndex = 4;
                }
                do {
                    data = new String[3];
                    data[0] = c.getString(nameIndex);
                    data[1] = c.getString(sortIndex);
                    data[2] = c.getString(idIndex);
                    //Log.d(TAG, "data[0] = " + data[0]);
                    //Log.d(TAG, "data[1] = " + data[1]);
                    //Log.d(TAG, "data[2] = " + data[2]);
                } while (c.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return data;
    }

    private List<Map<String, Object>> getListItems() {
        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();

        for(int i = 0; i < getLaboratorAssistantCount(); i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("text", getName(String.valueOf(i+1))[0]);       //图片资源
            map.put("into", R.drawable.right_arrow);       //物品标题
            listItems.add(map);
        }
        return listItems;
    }

    private static int getLaboratorAssistantCount() {
        SQLiteDatabase db = openDatabase();
        String sql = "select * from report_outline";
        Cursor cs = db.rawQuery(sql, null);

        if (cs.moveToFirst()) {
            return cs.getCount();
        }
        return 0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_laboratory_assistant, menu);
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
