package com.example.moviecatalogue.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.moviecatalogue.R;
import com.example.moviecatalogue.db.FavoriteDao;
import com.example.moviecatalogue.db.FavoriteDatabase;
import com.example.moviecatalogue.model.FavoriteMovieModel;
import com.squareup.picasso.Picasso;

import java.util.List;


public class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    //deklarasi
    private final Context mContext;

    private List<FavoriteMovieModel> listItemMovie;
    private int mAppWidgetId;
    private FavoriteDao favoriteDao;
    private Cursor cursor;

    public StackRemoteViewsFactory(Context mContext, Intent intent) {
        this.mContext = mContext;
        mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        FavoriteDatabase db;
        db = FavoriteDatabase.getInstance(mContext);
        favoriteDao = db.favoriteDao();
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
//        if (cursor != null){
//            cursor.close();
//        }
//        final long identity = Binder.clearCallingIdentity();
//        cursor = mContext.getContentResolver().query(CONTENT_URI, null,
//                null,null,null);
//        Binder.restoreCallingIdentity(identity);
        listItemMovie = favoriteDao.selectAll();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return listItemMovie.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.widget_item);

        Bitmap bitmap = null;
        try {
            bitmap = Picasso.with(mContext)
                    .load(listItemMovie.get(position).getPoster_path())
                    .get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        rv.setImageViewBitmap(R.id.imageView, bitmap);
        Log.d("widget movie", listItemMovie.get(position).getPoster_path());
        Bundle extras = new Bundle();
        extras.putInt(FavoriteWidget.EXTRA_ITEM, position);
        Intent fillIntent = new Intent();
        fillIntent.putExtras(extras);

        rv.setOnClickFillInIntent(R.id.imageView, fillIntent);
        return rv;

    }


    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
