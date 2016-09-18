package com.juliocalderonuninorte.pokemongodemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by JULIO CALDERON on 18/09/2016.
 */
public class MySQLHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "location";
    public static final String ID_COL = "loc_id";
    public static final String TITLE = "loc_title";
    public static final String SNIPPET = "loc_snippet";
    public static final String POSITION = "loc_position";

    private static final int D_VERSION = 1;
    private static final String DB_NAME = "markerlocation.db";
    private static final String DB_CREATE =
            "create table"+ TABLE_NAME + "{"
            + ID_COL + "integer primary key autoincrement, "
            + TITLE + "text, "
            + SNIPPET + "text, "
            + POSITION + "text)"
            ;

    public MySQLHelper(Context context){
        super(context,DB_NAME,null,D_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);
    }
}
