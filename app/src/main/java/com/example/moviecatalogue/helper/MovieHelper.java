package com.example.moviecatalogue.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import static android.provider.BaseColumns._ID;
import static com.example.moviecatalogue.helper.DatabaseContract.TABLE_NAME;

public class MovieHelper {
//    private static final String DATABASE_TABLE = TABLE_NAME;
//    private static DatabaseHelper databaseHelper;
//    private Context mContext;
//    private static SQLiteDatabase database;
//
//    public MovieHelper(Context context) {
//        this.mContext = context;
//    }
//
//
//    public void open() throws SQLException {
//        databaseHelper = new DatabaseHelper(mContext);
//        database = databaseHelper.getWritableDatabase();
//    }
//
//    public long insertProvider(ContentValues values) {
//        return database.insert(DATABASE_TABLE, null,values);
//    }
//
//    public void close() {
//        databaseHelper.close();
//    }
//
//    public Cursor queryByIdProvider(String id){
//        return database.query(DATABASE_TABLE,null,_ID+"=?",new String[]{id},null,null,null,null);
//    }
//
//    public int updateProvider(String id, ContentValues contentValues){
//        return database.update(DATABASE_TABLE, contentValues,_ID+"=?",new String[]{id});
//    }
//
//    public int deleteProvider(String id){
//        return database.delete(DATABASE_TABLE,_ID+"=?",new String[]{id});
//    }
//
//    public Cursor queryProvider(){
//        return database.query(DATABASE_TABLE, null,null,null,null,null,_ID+" DESC");
//    }

    private static String DATABASE_TABLE = TABLE_NAME;
    private Context mContext;
    private DatabaseHelper mDatabaseHelper;
    private SQLiteDatabase mDatabase;

    public MovieHelper(Context mContext){
        this.mContext = mContext;
    }

    public void open() throws SQLException {
        mDatabaseHelper = new DatabaseHelper(mContext);
        mDatabase = mDatabaseHelper.getWritableDatabase();
    }

    public long insertProvider(ContentValues contentValues){
        return mDatabase.insert(DATABASE_TABLE, null, contentValues);
    }

    public void close(){
        mDatabaseHelper.close();
    }

    public Cursor queryByIdProvider(String id){
        return mDatabase.query(DATABASE_TABLE,null,_ID+"=?",new String[]{id},null,null,null,null);
    }

    public int updateProvider(String id, ContentValues contentValues){
        return mDatabase.update(DATABASE_TABLE, contentValues,_ID+"=?",new String[]{id});
    }

    public int deleteProvider(String id){
        return mDatabase.delete(DATABASE_TABLE,_ID+"=?",new String[]{id});
    }

    public Cursor queryProvider(){
        return mDatabase.query(DATABASE_TABLE, null,null,null,null,null,_ID+" DESC");
    }

}
