package com.chiplua.jiankang;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chiplua_client on 15-3-23.
 */
public class SQLOperation {
    private final static String TAG = "SQLOperation";
    private final static String DATABASE_NAME = "health_library.db";
    private final static String DATABASE_PATH = "/data/data/" + JiankangActivity.PACKAGE_NAME + "/databases";
    private final static String REPORTOUTLINEID = "id";
    private final static String REPORTOUTLINENAME = "name";
    private final static String REPORTOUTLINESORT = "sort";
    private final static String REPORTOUTLINEORDERNO = "orderNo";
    private final static String REPORTRELATIONMAPLR_ID = "lr_id";
    private final static String REPORTRELATIONMAPCA_ID = "ca_id";
    private final static String REPORTRELATIONMAPNAME = "name";
    private final static String REPORTDETAILSCONTENT = "content";

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

    public static String[] getReportOutline(String orderNo) {
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
                    nameIndex = c.getColumnIndexOrThrow(REPORTOUTLINENAME);
                    sortIndex = c.getColumnIndexOrThrow(REPORTOUTLINESORT);
                    idIndex = c.getColumnIndexOrThrow(REPORTOUTLINEORDERNO);
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

    public static int getLaboratorAssistantCount() {
        SQLiteDatabase db = openDatabase();
        String sql = "select * from report_outline";
        Cursor cs = db.rawQuery(sql, null);

        if (cs.moveToFirst()) {
            return cs.getCount();
        }
        return 0;
    }

    public static List<Map<String, Object>> getReportRelationMap(String lr_id) {
        SQLiteDatabase db = null;
        Cursor c = null;
        String[] data = null;
        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
        try {
            db = openDatabase();
            if (db == null)
                return listItems;
            String sql = "select * from report_relation_map where lr_id='"+ lr_id +"'";
            c = db.rawQuery(sql, null);
            if (c.getCount() > 0 && c.moveToFirst()) {
                int caIDIndex;
                int nameIndex;
                int lrIDIndex;
                Map<String, Object> map = new HashMap<String, Object>();
                try {
                    caIDIndex = c.getColumnIndexOrThrow(REPORTRELATIONMAPCA_ID);
                    nameIndex = c.getColumnIndexOrThrow(REPORTRELATIONMAPNAME);
                    lrIDIndex = c.getColumnIndexOrThrow(REPORTRELATIONMAPLR_ID);
                } catch (IllegalArgumentException e) {
                    lrIDIndex = 1;
                    caIDIndex = 2;
                    nameIndex = 3;
                }
                do {
                    data = new String[3];
                    data[0] = c.getString(caIDIndex);
                    data[1] = c.getString(nameIndex);
                    data[2] = c.getString(lrIDIndex);
                    Log.d(TAG, "data[0] = " + data[0]);
                    Log.d(TAG, "data[1] = " + data[1]);
                    Log.d(TAG, "data[2] = " + data[2]);
                    map.put(data[0], data[1]);
                } while (c.moveToNext());
                listItems.add(map);
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
        return listItems;
    }

    public static String getReportRelationMapLRID(String name) {
        SQLiteDatabase db = null;
        Cursor c = null;
        String data = null;
        try {
            db = openDatabase();
            if (db == null)
                return data;
            String sql = "select * from report_outline where name='"+ name +"'";
            c = db.rawQuery(sql, null);
            if (c.getCount() > 0 && c.moveToFirst()) {
                int IDIndex;
                try {
                    IDIndex = c.getColumnIndexOrThrow(REPORTOUTLINEID);
                } catch (IllegalArgumentException e) {
                    IDIndex = 1;
                }
                do {
                    data = c.getString(IDIndex);
                    Log.d(TAG, "data = " + data);
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

    public static String getReportDetailsContent(String selectName) {
        SQLiteDatabase db = null;
        Cursor c = null;
        String data = null;
        try {
            db = openDatabase();
            if (db == null)
                return data;
            String sql = "select * from report_details where name='"+ selectName +"'";
            c = db.rawQuery(sql, null);
            if (c.getCount() > 0 && c.moveToFirst()) {
                int contentIndex;
                try {
                    contentIndex = c.getColumnIndexOrThrow(REPORTDETAILSCONTENT);
                } catch (IllegalArgumentException e) {
                    contentIndex = 4;
                }
                do {
                    data = c.getString(contentIndex);
                    Log.d(TAG, "data = " + data);
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

}
