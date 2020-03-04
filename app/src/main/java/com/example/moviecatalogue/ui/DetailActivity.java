package com.example.moviecatalogue.ui;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.moviecatalogue.R;
import com.example.moviecatalogue.helper.MovieHelper;
import com.example.moviecatalogue.helper.ProviderEntity;
import com.example.moviecatalogue.model.FavoriteMovieModel;
import com.example.moviecatalogue.model.FavoriteTvModel;
import com.example.moviecatalogue.model.MovieModel;
import com.example.moviecatalogue.model.TvModel;
import com.example.moviecatalogue.viewmodel.FavMovieViewModel;
import com.example.moviecatalogue.viewmodel.FavTvViewModel;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.moviecatalogue.helper.DatabaseContract.CONTENT_URI;
import static com.example.moviecatalogue.helper.DatabaseContract.MovieColumns.DATE;
import static com.example.moviecatalogue.helper.DatabaseContract.MovieColumns.OVERVIEW;
import static com.example.moviecatalogue.helper.DatabaseContract.MovieColumns.POSTER_PATH;
import static com.example.moviecatalogue.helper.DatabaseContract.MovieColumns.TITLE;


public class DetailActivity extends AppCompatActivity {

    private FavMovieViewModel favMovieViewModel;
    private FavTvViewModel favTvViewModel;
    //array movie provider
    private ArrayList<ProviderEntity> listMovieProvider = new ArrayList<>();

    public static final String KEY_EXTRA_FAV_MOVIE = "extra_fav_movie";
    public static final String KEY_EXTRA_FAV_TV = "extra_fav_tv";
    public static final String KEY_EXTRA = "extra_movie_data";
    public static final String KEY_EXTRA_TV = "extra_tv_data";
    private ImageView imageView, poster;
    private TextView overview,title,date;

    //sqlite
    private MovieHelper movieHelper;
    MovieModel movieModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageView = findViewById(R.id.main_backdrop);
        poster = findViewById(R.id.img_poster);
        overview = findViewById(R.id.tv_overview);
        title = findViewById(R.id.tv_judul);
        date = findViewById(R.id.tv_date);
        //open sqlite
        movieHelper = new MovieHelper(this);
        movieHelper.open();
        Uri uri = getIntent().getData();
        if (uri!=null) {
            Cursor cursor = getContentResolver().query(uri, null,null,null, null);
            if (cursor!=null) {
                if (cursor.moveToFirst()) {
                    movieModel = new MovieModel(cursor);
                    cursor.close();
                }
            }
        }

        FloatingActionButton btnAddFav = findViewById(R.id.btn_add_fav);
        FloatingActionButton btnDelFav = findViewById(R.id.btn_delete_fav);
        btnAddFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToFavorite();
            }
        });



        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        //init view model
        favMovieViewModel = new ViewModelProvider(this).get(FavMovieViewModel.class);
        favTvViewModel = new ViewModelProvider(this).get(FavTvViewModel.class);

        MovieModel movieModel = getIntent().getParcelableExtra(KEY_EXTRA);
        TvModel tvModel = getIntent().getParcelableExtra(KEY_EXTRA_TV);
        final FavoriteMovieModel favoriteMovieModel = getIntent().getParcelableExtra(KEY_EXTRA_FAV_MOVIE);
        final FavoriteTvModel favoriteTvModel = getIntent().getParcelableExtra(KEY_EXTRA_FAV_TV);
        if (movieModel != null) {
            setDataMovie(movieModel);
        } else if(tvModel!= null){
            setDataTv(tvModel);
        } else if (favoriteMovieModel != null){
            setDataFavoriteMovie(favoriteMovieModel);
            btnAddFav.hide();
            btnDelFav.show();
            btnDelFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    favMovieViewModel.delete(favoriteMovieModel);
                    finish();
                }
            });
        } else {
            setDataFavoriteTv(favoriteTvModel);
            btnAddFav.hide();
            btnDelFav.show();
            btnDelFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    favTvViewModel.deleteTv(favoriteTvModel);
                    finish();
                }
            });
        }
    }

    private void setDataFavoriteMovie(FavoriteMovieModel favorite) {
        title.setText(favorite.getTitle());
        date.setText(favorite.getRelease_date());
        overview.setText(favorite.getOverview());
        Picasso.with(this)
                .load(favorite.getPoster_path())
                .resize(300, 500)
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView);

        Picasso.with(this)
                .load(favorite.getPoster_path())
                .into(poster);

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.main_collapsing);
        collapsingToolbarLayout.setTitle(getString(R.string.movie_catalogue));

        collapsingToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(this, R.color.white));
        collapsingToolbarLayout.setExpandedTitleColor(
                ContextCompat.getColor(this, R.color.colorPrimary)
        );
    }

    private void setDataFavoriteTv(FavoriteTvModel favorite) {
        title.setText(favorite.getTitle());
        date.setText(favorite.getRelease_date());
        overview.setText(favorite.getOverview());
        Picasso.with(this)
                .load(favorite.getPoster_path())
                .resize(300, 500)
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView);

        Picasso.with(this)
                .load(favorite.getPoster_path())
                .into(poster);

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.main_collapsing);
        collapsingToolbarLayout.setTitle(getString(R.string.movie_catalogue));

        collapsingToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(this, R.color.white));
        collapsingToolbarLayout.setExpandedTitleColor(
                ContextCompat.getColor(this, R.color.colorPrimary)
        );
    }
    private void setDataMovie(MovieModel movieModel) {
        title.setText(movieModel.getTitle());
        date.setText(movieModel.getRelease_date());
        overview.setText(movieModel.getOverview());
        Picasso.with(this)
                .load(movieModel.getPoster_path())
                .resize(300, 500)
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView);

        Picasso.with(this)
                .load(movieModel.getPoster_path())
                .into(poster);

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.main_collapsing);
        collapsingToolbarLayout.setTitle(getString(R.string.movie_catalogue));

        collapsingToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(this, R.color.white));
        collapsingToolbarLayout.setExpandedTitleColor(
                ContextCompat.getColor(this, R.color.colorPrimary)
        );
    }

    private void setDataTv(TvModel tvModel){
        title.setText(tvModel.getName());
        date.setText(tvModel.getRelease_date());
        overview.setText(tvModel.getOverview());
        Picasso.with(this)
                .load(tvModel.getPoster_path())
                .resize(300, 500)
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView);

        Picasso.with(this)
                .load(tvModel.getPoster_path())
                .into(poster);

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.main_collapsing);
        collapsingToolbarLayout.setTitle(getString(R.string.movie_catalogue));

        collapsingToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(this, R.color.white));
        collapsingToolbarLayout.setExpandedTitleColor(
                ContextCompat.getColor(this, R.color.colorPrimary)
        );
    }
    private void saveToFavorite(){
        MovieModel movieModel = getIntent().getParcelableExtra(KEY_EXTRA);
        TvModel tvModel = getIntent().getParcelableExtra(KEY_EXTRA_TV);
        favMovieViewModel = new ViewModelProvider(this).get(FavMovieViewModel.class);
        favTvViewModel = new ViewModelProvider(this).get(FavTvViewModel.class);
        int id ;
        String title;
        String poster_path;
        String overview;
        String release_date;
        if (movieModel != null) {
            id = movieModel.getId();
            title = movieModel.getTitle();
            poster_path = movieModel.getPoster_path();
            overview = movieModel.getOverview();
            release_date = movieModel.getRelease_date();
            if (favMovieViewModel != null){
                favMovieViewModel.insert(new FavoriteMovieModel(id, title, poster_path, overview, release_date));
                addFavProvider(movieModel);
                Toast.makeText(this, R.string.added, Toast.LENGTH_SHORT).show();
                finish();
            }

        } else {
            if (tvModel != null){
                id = tvModel.getId();
                title = tvModel.getName();
                poster_path = tvModel.getPoster_path();
                overview = tvModel.getOverview();
                release_date = tvModel.getRelease_date();
                if (favTvViewModel!= null) {
                    FavoriteTvModel favoriteTvModel = new FavoriteTvModel(id, title, poster_path, overview, release_date);
                    favTvViewModel.insertTv(favoriteTvModel);
                    Toast.makeText(this, R.string.added, Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        }
    }

    private void addFavProvider(MovieModel providerEntity) {
        String titleMovie = providerEntity.getTitle();
        String overView = providerEntity.getOverview();
        String releaseDate = providerEntity.getRelease_date();
        String imagePoster = providerEntity.getPoster_path();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TITLE, titleMovie);
        contentValues.put(OVERVIEW, overView);
        contentValues.put(DATE, releaseDate);
        contentValues.put(POSTER_PATH, imagePoster);
        getContentResolver().insert(CONTENT_URI,contentValues);
//        Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (movieHelper != null){
            movieHelper.close();
        }
    }

}

