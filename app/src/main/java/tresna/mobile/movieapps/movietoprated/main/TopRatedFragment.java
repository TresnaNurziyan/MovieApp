package tresna.mobile.movieapps.movietoprated.main;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tresna.mobile.movieapps.R;
import tresna.mobile.movieapps.base.mvp.MvpFragment;
import tresna.mobile.movieapps.movietoprated.adapter.TopRatedAdapter;
import tresna.mobile.movieapps.movietoprated.model.ResultsItem;
import tresna.mobile.movieapps.movietoprated.model.TopRatedResponse;
import tresna.mobile.movieapps.movietoprated.presenter.TopPresenter;
import tresna.mobile.movieapps.movietoprated.view.TopView;
import tresna.mobile.movieapps.utils.RecyclerItemClickListener;


public class TopRatedFragment extends MvpFragment<TopPresenter> implements TopView {

    private List<ResultsItem> list;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private TopPresenter topPresenter;

    public TopRatedFragment() {

    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_now, container, false);

        topPresenter = new TopPresenter(this);
        recyclerView = view.findViewById(R.id.rcvMovie);
        progressBar = view.findViewById(R.id.progress);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.addOnItemTouchListener(recyclerItemClickListener());
        topPresenter.loadData();
        return view;
    }

    private RecyclerItemClickListener recyclerItemClickListener(){
        return new RecyclerItemClickListener(getActivity(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                topPresenter.getItem(list.get(position), activity);
            }

            @Override
            public void onLongItemClick(View view, int position) {
                topPresenter.getItem(list.get(position), activity);
            }
        });
    }

    @Override
    protected TopPresenter createPresenter() {
        return new TopPresenter(this);
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
    public void getDataSuccess(TopRatedResponse ratedResponse) {
        this.list = ratedResponse.getResults();
        recyclerView.setAdapter(new TopRatedAdapter(list, R.layout.card_movie, getActivity()));
    }

    @Override
    public void getDataFail(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void moveToDetail(Intent intent) {
        startActivity(intent);
    }
}
