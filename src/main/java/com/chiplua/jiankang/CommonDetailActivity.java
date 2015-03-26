package com.chiplua.jiankang;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;


public class CommonDetailActivity extends Activity {
    private static final String TAG = "CommonDetailActivity";
    private static TextView detailTitle = null;
    private static TextView detailName = null;
    private static TextView detailDescribe = null;
    private static ImageButton backButton = null;
    private static String selectName = null;
    private static String describeString = null;
    private static String describeData = null;
    private static String normalValue = null;
    private static String clinicSignificance = null;
    private static String mattersAttention = null;
    private static String checkUp = null;
    private static String relatedDisease = null;
    private static String relatedSymptoms = null;

    private static Button normalButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_common_detail);
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
        //detailDescribe.setText(SQLOperation.getReportDetailsContent(selectName));
        describeString = SQLOperation.getReportDetailsContent(selectName);

        try {
            //JsonReader reader = new JsonReader(new StringReader(describeString));
            //reader.setLenient(true);
            JSONObject jsonObject = new JSONObject(describeString);
            Log.d(TAG, "describe" + describeString);
            JSONArray jsonArray =  jsonObject.getJSONArray("data");
            String title="TITLE";
            String content="CONTENT:";
            describeData = (String)((JSONObject) jsonArray.get(0)).get("content");
            normalValue = (String)((JSONObject) jsonArray.get(1)).get("content");
            clinicSignificance = (String)((JSONObject) jsonArray.get(2)).get("content");
            mattersAttention = (String)((JSONObject) jsonArray.get(3)).get("content");
            checkUp = (String)((JSONObject) jsonArray.get(4)).get("content");
            relatedDisease = (String)((JSONObject) jsonArray.get(5)).get("content");
            relatedSymptoms = (String)((JSONObject) jsonArray.get(6)).get("content");
            Log.d(TAG, "describeData: " + describeData);
            Log.d(TAG, "normalValue: " + normalValue);
            Log.d(TAG, "clinicSignificance: " + clinicSignificance);
            Log.d(TAG, "mattersAttention: " + mattersAttention);
            Log.d(TAG, "checkUp: " + checkUp);
            Log.d(TAG, "relatedDisease: " + relatedDisease);
            Log.d(TAG, "relatedSymptoms: " + relatedSymptoms);



            } catch (Exception e) {
                //TODO Auto-generated catch block
                e.printStackTrace();
            }

        detailDescribe.setText(describeData);
        normalButton = (Button) findViewById(R.id.normal_value_button);
        normalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "xxxxxxxxxxxxxxxxxxxxxxxxx");
            }
        });

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
}
