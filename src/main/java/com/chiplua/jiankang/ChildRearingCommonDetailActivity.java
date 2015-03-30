package com.chiplua.jiankang;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;


public class ChildRearingCommonDetailActivity extends Activity {
    private static final String TAG = "ChildRearingCommonDetailActivity";
    private static TextView detailTitle = null;
    private static TextView detailName = null;
    private static TextView detailDescribe = null;
    private static ImageButton backButton = null;
    private static String selectName = null;
    private static String describeString = null;
    private static String describeDataString = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_child_rearing_common_detail);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);
        selectName = getIntent().getStringExtra("selectName");

        detailTitle = (TextView) findViewById(R.id.title_name);
        detailTitle.setText(R.string.detail);
        backButton = (ImageButton) findViewById(R.id.back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        detailName = (TextView) findViewById(R.id.detail_name);
        detailName.setText(selectName);
        detailDescribe = (TextView) findViewById(R.id.detail_describe);
        describeString = SQLOperation.getChildRearingDetailsContent(selectName);
        describeDataString = formatString(describeString);
        detailDescribe.setText(describeDataString);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_common_detail, menu);
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

    private static String formatString(String inString) {
        inString = inString.replaceAll(selectName+":", "");
        inString = inString.replaceAll("\\|", "\n"); //替换掉字符串中的所有的“|”，replaceAll替换特殊字符要用\\转义。
        return inString;
    }
}
