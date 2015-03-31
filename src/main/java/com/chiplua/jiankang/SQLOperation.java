package com.chiplua.jiankang;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
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
    public final static int DATABASE_VERSION = 1;
    private final static String REPORTOUTLINEID = "id";
    private final static String REPORTOUTLINENAME = "name";
    private final static String REPORTOUTLINESORT = "sort";
    private final static String REPORTOUTLINEORDERNO = "orderNo";
    private final static String REPORTRELATIONMAPLR_ID = "lr_id";
    private final static String REPORTRELATIONMAPCA_ID = "ca_id";
    private final static String REPORTRELATIONMAPNAME = "name";
    private final static String REPORTDETAILSCONTENT = "content";
    private final static String COMMONDISEASENAME = "name";
    private final static String COMMONDISEASEDESC = "contentDesc";
    private final static String CHILDREARINGID = "id";
    private final static String CHILDREARINGNAME = "name";
    private final static String CHILDREARINGMAPDETAILID = "detail_id";
    private final static String CHILDREARINGMAPNAME = "name";
    private final static String CHILDDETAILCONTENT = "content";

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

    public static String getCommonDiseaseName(String id) {
        SQLiteDatabase db = null;
        Cursor c = null;
        String data = null;
        try {
            db = openDatabase();
            if (db == null)
                return data;
            String sql = "select * from common_disease where id='"+ id +"'";
            c = db.rawQuery(sql, null);
            if (c.getCount() > 0 && c.moveToFirst()) {
                int nameIndex;
                try {
                    nameIndex = c.getColumnIndexOrThrow(COMMONDISEASENAME);
                } catch (IllegalArgumentException e) {
                    nameIndex = 2;
                }
                do {
                    data = c.getString(nameIndex);
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

    public static String getCommonDiseaseContent(String name) {
        SQLiteDatabase db = null;
        Cursor c = null;
        String data = null;
        try {
            db = openDatabase();
            if (db == null)
                return data;
            String sql = "select * from common_disease where name='"+ name +"'";
            c = db.rawQuery(sql, null);
            if (c.getCount() > 0 && c.moveToFirst()) {
                int contentIndex;
                try {
                    contentIndex = c.getColumnIndexOrThrow(COMMONDISEASEDESC);
                } catch (IllegalArgumentException e) {
                    contentIndex = 3;
                }
                do {
                    data = c.getString(contentIndex);
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

    public static int getCommonDiseaseCount() {
        SQLiteDatabase db = openDatabase();
        String sql = "select * from common_disease";
        Cursor cs = db.rawQuery(sql, null);

        if (cs.moveToFirst()) {
            return cs.getCount();
        }
        return 0;
    }

    public static String getChildRearingOutline(String id) {
        SQLiteDatabase db = null;
        Cursor c = null;
        String data = null;
        try {
            db = openDatabase();
            if (db == null)
                return data;
            String sql = "select * from child_outline where id='"+ id +"'";
            c = db.rawQuery(sql, null);
            if (c.getCount() > 0 && c.moveToFirst()) {
                int nameIndex;
                try {
                    nameIndex = c.getColumnIndexOrThrow(CHILDREARINGNAME);
                } catch (IllegalArgumentException e1) {
                    nameIndex = 2;
                }
                do {
                    data = c.getString(nameIndex);
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

    public static int getChildRearingCount() {
        SQLiteDatabase db = openDatabase();
        String sql = "select * from child_outline";
        Cursor cs = db.rawQuery(sql, null);

        if (cs.moveToFirst()) {
            return cs.getCount();
        }
        return 0;
    }

    public static String getChildRearingRelationMapOutlineID(String name) {
        SQLiteDatabase db = null;
        Cursor c = null;
        String data = null;
        try {
            db = openDatabase();
            if (db == null)
                return data;
            String sql = "select * from child_outline where name='"+ name +"'";
            c = db.rawQuery(sql, null);
            if (c.getCount() > 0 && c.moveToFirst()) {
                int IDIndex;
                try {
                    IDIndex = c.getColumnIndexOrThrow(CHILDREARINGID);
                } catch (IllegalArgumentException e) {
                    IDIndex = 1;
                }
                do {
                    data = c.getString(IDIndex);
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

    public static List<Map<String, Object>> getChildRearingRelationMap(String outline_id) {
        SQLiteDatabase db = null;
        Cursor c = null;
        String[] data = null;
        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
        try {
            db = openDatabase();
            if (db == null)
                return listItems;
            String sql = "select * from child_relation_map where outline_id='"+ outline_id +"'";
            c = db.rawQuery(sql, null);
            if (c.getCount() > 0 && c.moveToFirst()) {
                int detailIDIndex;
                int nameIndex;
                Map<String, Object> map = new HashMap<String, Object>();
                try {
                    detailIDIndex = c.getColumnIndexOrThrow(CHILDREARINGMAPDETAILID);
                    nameIndex = c.getColumnIndexOrThrow(CHILDREARINGMAPNAME);
                } catch (IllegalArgumentException e) {
                    detailIDIndex = 1;
                    nameIndex = 3;
                }
                do {
                    data = new String[2];
                    data[0] = c.getString(detailIDIndex);
                    data[1] = c.getString(nameIndex);
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

    public static String getChildRearingMapDetailID(String name) {
        SQLiteDatabase db = null;
        Cursor c = null;
        String data = null;
        try {
            db = openDatabase();
            if (db == null)
                return data;
            String sql = "select * from child_relaition_map where name='"+ name +"'";
            c = db.rawQuery(sql, null);
            if (c.getCount() > 0 && c.moveToFirst()) {
                int detailIDIndex;
                try {
                    detailIDIndex = c.getColumnIndexOrThrow(CHILDREARINGMAPDETAILID);
                } catch (IllegalArgumentException e) {
                    detailIDIndex = 1;
                }
                do {
                    data = c.getString(detailIDIndex);
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

    public static String getChildRearingDetailsContent(String selectName) {
        SQLiteDatabase db = null;
        Cursor c = null;
        String data = null;
        try {
            db = openDatabase();
            if (db == null)
                return data;
            String sql = "select * from child_details where name='"+ selectName +"'";
            c = db.rawQuery(sql, null);
            if (c.getCount() > 0 && c.moveToFirst()) {
                int contentIndex;
                try {
                    contentIndex = c.getColumnIndexOrThrow(CHILDDETAILCONTENT);
                } catch (IllegalArgumentException e) {
                    contentIndex = 3;
                }
                do {
                    data = c.getString(contentIndex);
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

    public static void initExtraDB(Context context) {
        try {
            File dir = new File(DATABASE_PATH);
            if (!dir.exists()) {
                dir.mkdir();
            }
            String databaseFileName = DATABASE_PATH + "/" + DATABASE_NAME;
            File databaseFile = new File(databaseFileName);
            if (!databaseFile.exists()) {
                FileOutputStream fos = new FileOutputStream(databaseFileName);
                byte[] buffer = new byte[8192];
                int count = 0;
                InputStream is = context.getResources().openRawResource(R.raw.health_library);
                while ((count = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                }
                fos.close();
                is.close();
                SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DATABASE_PATH + "/"
                        + DATABASE_NAME, null);
                db.setVersion(DATABASE_VERSION);
                db.close();
            } else {
                SQLiteDatabase oldDb = SQLiteDatabase.openOrCreateDatabase(DATABASE_PATH + "/"
                        + DATABASE_NAME, null);
                if (oldDb.getVersion() < DATABASE_VERSION) {
                    oldDb.close();
                    databaseFile.delete();
                    FileOutputStream fos = new FileOutputStream(databaseFileName);
                    byte[] buffer = new byte[8192];
                    int count = 0;
                    InputStream is = context.getResources().openRawResource(R.raw.health_library);
                    while ((count = is.read(buffer)) > 0) {
                        fos.write(buffer, 0, count);
                    }
                    fos.close();
                    is.close();
                    SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DATABASE_PATH + "/"
                            + DATABASE_NAME, null);
                    db.setVersion(DATABASE_VERSION);
                    db.close();
                } else {
                    oldDb.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
