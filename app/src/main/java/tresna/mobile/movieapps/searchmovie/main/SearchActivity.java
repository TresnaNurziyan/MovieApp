package tresna.mobile.movieapps.searchmovie.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tresna.mobile.movieapps.R;
import tresna.mobile.movieapps.base.mvp.MvpActivity;
import tresna.mobile.movieapps.searchmovie.adapter.SearchAdapter;
import tresna.mobile.movieapps.searchmovie.model.ResultsItem;
import tresna.mobile.movieapps.searchmovie.model.SearchResponse;
import tresna.mobile.movieapps.searchmovie.presenter.SearchPresenter;
import tresna.mobile.movieapps.utils.RecyclerItemClickListener;

public class SearchActivity extends MvpActivity<SearchPresenter> implements tresna.mobile.movieapps.searchmovie.view.SearchView {

    private RecyclerView recyclerView;
    private List<ResultsItem> list;
    private ProgressBar progressBar;
    private SearchPresenter searchPresenter;

    @Override
    protected SearchPresenter createPresenter() {
        return new SearchPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initView();
        searchPresenter.loadData(getIntent().getStringExtra("query"));
    }

    private void initView() {

        searchPresenter = new SearchPresenter(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        progressBar = findViewById(R.id.progress);
        recyclerView = findViewById(R.id.rcvSearch);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.addOnItemTouchListener(recyclerItemClickListener());

    }

    private RecyclerItemClickListener recyclerItemClickListener() {
        return new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                searchPresenter.getItem(list.get(position), activity);
            }

            @Override
            public void onLongItemClick(View view, int position) {
                searchPresenter.getItem(list.get(position), activity);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);

        MenuItem menuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchPresenter.loadData(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        return true;
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void getSuccess(SearchResponse searchResponse) {
        this.list = searchResponse.getResults();
        recyclerView.setAdapter(new SearchAdapter(list, R.layout.card_movie, getApplicationContext()));
    }

    @Override
    public void getDataFail(String message) {
        Log.i("search", message);
    }

    @Override
    public void moveToDetail(Intent intent) {
        startActivity(intent);
    }
}
