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
    private static String describeDataString = null;
    private static String normalValueString = null;
    private static String clinicSignificanceString = null;
    private static String mattersAttentionString = null;
    private static String checkUpString = null;
    private static String relatedDiseaseString = null;
    private static String relatedSymptomsString = null;

    private static Button normalButton = null;
    private static Button clinicSignificanceButton = null;
    private static Button mattersAttentionButton = null;
    private static Button checkUpButton = null;
    private static Button relatedDiseaseButton = null;
    private static Button relatedSymptomsButton = null;

    private static TextView normalText = null;
    private static TextView clinicSignificanceText = null;
    private static TextView mattersAttentionText = null;
    private static TextView checkUpText = null;
    private static TextView relatedDiseaseText = null;
    private static TextView relatedSymptomsText = null;

    Boolean normalFlag = false;
    Boolean clinicFlag = false;
    Boolean matterFlag = false;
    Boolean checkFlag = false;
    Boolean relatedDiseaseFlag = false;
    Boolean relatedSymptomsFlag = false;

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
        describeString = SQLOperation.getReportDetailsContent(selectName);

        try {
            JSONObject jsonObject = new JSONObject(describeString);
            Log.d(TAG, "describe" + describeString);
            JSONArray jsonArray =  jsonObject.getJSONArray("data");
            describeDataString = (String)((JSONObject) jsonArray.get(0)).get("content");
            normalValueString = (String)((JSONObject) jsonArray.get(1)).get("content");
            clinicSignificanceString = (String)((JSONObject) jsonArray.get(2)).get("content");
            mattersAttentionString = (String)((JSONObject) jsonArray.get(3)).get("content");
            checkUpString = (String)((JSONObject) jsonArray.get(4)).get("content");
            relatedDiseaseString = (String)((JSONObject) jsonArray.get(5)).get("content");
            relatedSymptomsString = (String)((JSONObject) jsonArray.get(6)).get("content");
/*            Log.d(TAG, "describeData: " + describeDataString);
            Log.d(TAG, "normalValue: " + normalValueString);
            Log.d(TAG, "clinicSignificance: " + clinicSignificanceString);
            Log.d(TAG, "mattersAttention: " + mattersAttentionString);
            Log.d(TAG, "checkUp: " + checkUpString);
            Log.d(TAG, "relatedDisease: " + relatedDiseaseString);
            Log.d(TAG, "relatedSymptoms: " + relatedSymptomsString);
*/
            } catch (Exception e) {
                //TODO Auto-generated catch block
                e.printStackTrace();
            }

        describeDataString = formatString(describeDataString);
        detailDescribe.setText(describeDataString);
        normalValueString = formatString(normalValueString);
        normalText = (TextView) findViewById(R.id.normal_value_text);
        normalButton = (Button) findViewById(R.id.normal_value_button);
        normalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (normalFlag) {
                    normalText.setVisibility(View.GONE);
                    normalFlag = false;
                } else {
                    normalText.setVisibility(View.VISIBLE);
                    normalText.setText(normalValueString);
                    normalFlag = true;
                }
            }
        });

        clinicSignificanceString = formatString(clinicSignificanceString);
        clinicSignificanceText = (TextView) findViewById(R.id.clinic_significance_text);
        clinicSignificanceButton = (Button) findViewById(R.id.clinic_significance_button);
        clinicSignificanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clinicFlag) {
                    clinicSignificanceText.setVisibility(View.GONE);
                    clinicFlag = false;
                } else {
                    clinicSignificanceText.setVisibility(View.VISIBLE);
                    clinicSignificanceText.setText(clinicSignificanceString);
                    clinicFlag = true;
                }
            }
        });

        mattersAttentionString = formatString(mattersAttentionString);
        mattersAttentionText = (TextView) findViewById(R.id.matters_attention_text);
        mattersAttentionButton = (Button) findViewById(R.id.matters_attention_button);
        mattersAttentionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matterFlag) {
                    mattersAttentionText.setVisibility(View.GONE);
                    matterFlag = false;
                } else {
                    mattersAttentionText.setVisibility(View.VISIBLE);
                    mattersAttentionText.setText(mattersAttentionString);
                    matterFlag = true;
                }

            }
        });

        checkUpString = formatString(checkUpString);
        checkUpText = (TextView) findViewById(R.id.check_up_text);
        checkUpButton = (Button) findViewById(R.id.check_up_button);
        checkUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkFlag) {
                    checkUpText.setVisibility(View.GONE);
                    checkFlag = false;
                } else {
                    checkUpText.setVisibility(View.VISIBLE);
                    checkUpText.setText(checkUpString);
                    checkFlag = true;
                }
            }
        });

        relatedDiseaseString = formatString(relatedDiseaseString);
        relatedDiseaseText = (TextView) findViewById(R.id.related_disease_text);
        relatedDiseaseButton = (Button) findViewById(R.id.related_disease_button);
        relatedDiseaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (relatedDiseaseFlag) {
                    relatedDiseaseText.setVisibility(View.GONE);
                    relatedDiseaseFlag = false;
                } else {
                    relatedDiseaseText.setVisibility(View.VISIBLE);
                    relatedDiseaseText.setText(checkUpString);
                    relatedDiseaseFlag = true;
                }
            }
        });

        relatedSymptomsString = formatString(relatedSymptomsString);
        relatedSymptomsText = (TextView) findViewById(R.id.related_symptoms_text);
        relatedSymptomsButton = (Button) findViewById(R.id.related_symptoms_button);
        relatedSymptomsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (relatedSymptomsFlag) {
                    relatedSymptomsText.setVisibility(View.GONE);
                    relatedSymptomsFlag = false;
                } else {
                    relatedSymptomsText.setVisibility(View.VISIBLE);
                    relatedSymptomsText.setText(checkUpString);
                    relatedSymptomsFlag = true;
                }
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

    private static String formatString(String inString) {
        inString = inString.replaceAll("<P>", "");
        inString = inString.replaceAll("<p>", "");
        inString = inString.replaceAll("</P>", "\n");
        inString = inString.replaceAll("</p>", "\n");
        inString = inString.replaceAll("\t", "");
        inString = inString.replaceAll("\r", "");
        inString = inString.replaceAll(" ", "");
        inString = inString.replaceAll("　", "");
        return inString;
    }
}
