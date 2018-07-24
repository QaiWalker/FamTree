package com.rp.qai.famtree;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "fam.db";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_FAMILY = "family";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_REL = "relation";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_NUM = "number";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createNoteTableSql = "CREATE TABLE " + TABLE_FAMILY + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT, "
                + COLUMN_REL + " TEXT, "
                + COLUMN_TITLE + " TEXT, "
                + COLUMN_ADDRESS + " TEXT, "
                + COLUMN_NUM + " INTEGER ) ";
        db.execSQL(createNoteTableSql);
        Log.i("Info", "Records inserted");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAMILY);
        onCreate(db);
    }
    public long insertMember(String name, String relation, String title, String address, int number) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_REL, relation);
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_ADDRESS, address);
        values.put(COLUMN_NUM, number);
        long result = db.insert(TABLE_FAMILY, null, values);
        db.close();
        Log.d("SQL Insert ",""+ result);
        return result;
    }
    public ArrayList<Member> getAllMembers() {
        ArrayList<Member> members = new ArrayList<Member>();

        String selectQuery = "SELECT " + COLUMN_ID + ","
                + COLUMN_NAME + ","
                + COLUMN_REL + ","
                + COLUMN_TITLE + ","
                + COLUMN_ADDRESS + ","
                + COLUMN_NUM + " FROM " + TABLE_FAMILY;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String relation = cursor.getString(2);
                String title = cursor.getString(3);
                String address = cursor.getString(4);
                int number = cursor.getInt(5);
                Member member = new Member(id, name, relation, title, address, number);
                members.add(member);
                Log.d("Data", member.toString());
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return members;
    }
    public int updateMember(Member data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, data.getName());
        values.put(COLUMN_REL, data.getRelation());
        values.put(COLUMN_TITLE, data.getTitle());
        values.put(COLUMN_ADDRESS, data.getAddress());
        values.put(COLUMN_NUM, data.getNumber());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(TABLE_FAMILY, values, condition, args);
        db.close();
        return result;
    }
    public int deleteMember(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_FAMILY, condition, args);
        db.close();
        return result;
    }
}
