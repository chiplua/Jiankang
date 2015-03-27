package com.chiplua.jiankang;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;


public class CommonDiseaseDetailCommonActivity extends Activity {
    private static final String TAG = "CommonDiseaseDetailCommonActivity";
    private static ImageButton backButton = null;
    private static TextView titleName = null;
    private static String titleString = null;
    private static TextView detailName = null;
    private static TextView detailDescribe = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_common_disease_detail_common);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);
        titleString = getIntent().getStringExtra("selectName");

        backButton = (ImageButton) findViewById(R.id.back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleName = (TextView) findViewById(R.id.title_name);
        titleName.setText(R.string.detail);
        detailName = (TextView) findViewById(R.id.detail_name);
        detailName.setText(titleString);
        detailDescribe = (TextView) findViewById(R.id.detail_describe);
        detailDescribe.setText(SQLOperation.getCommonDiseaseContent(titleString));



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_common_disease_detail_common, menu);
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
