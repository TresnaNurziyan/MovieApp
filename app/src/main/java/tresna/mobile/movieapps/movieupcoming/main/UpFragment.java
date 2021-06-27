package tresna.mobile.movieapps.movieupcoming.main;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tresna.mobile.movieapps.R;
import tresna.mobile.movieapps.base.mvp.MvpFragment;
import tresna.mobile.movieapps.movieupcoming.model.ResultsItem;
import tresna.mobile.movieapps.movieupcoming.adapter.UpAdapter;
import tresna.mobile.movieapps.movieupcoming.model.UpResponse;
import tresna.mobile.movieapps.movieupcoming.presenter.UpPresenter;
import tresna.mobile.movieapps.movieupcoming.view.UpView;
import tresna.mobile.movieapps.utils.RecyclerItemClickListener;

public class UpFragment extends MvpFragment<UpPresenter> implements UpView {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private UpPresenter presenter;
    private List<ResultsItem> list;

    public UpFragment() {
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_now, container, false);

        recyclerView = view.findViewById(R.id.rcvMovie);
        progressBar = view.findViewById(R.id.progress);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerView.addOnItemTouchListener(recyclerItemClickListener());
        presenter = new UpPresenter(this);
        presenter.loadData();

        return view;
    }

    private RecyclerItemClickListener recyclerItemClickListener(){
        return new RecyclerItemClickListener(getActivity(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                presenter.getItem(list.get(position), activity);
            }

            @Override
            public void onLongItemClick(View view, int position) {
                presenter.getItem(list.get(position), activity);
            }
        });
    }

    @Override
    protected UpPresenter createPresenter() {
        return new UpPresenter(this);
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
    public void getSuccess(UpResponse upResponse) {
        this.list = upResponse.getResults();
        recyclerView.setAdapter(new UpAdapter(list, R.layout.card_movie, getActivity()));
    }

    @Override
    public void getDataFail(String message) {
        Log.i("Upcoming", message);
    }

    @Override
    public void moveToDetail(Intent intent) {
        startActivity(intent);
    }
}
