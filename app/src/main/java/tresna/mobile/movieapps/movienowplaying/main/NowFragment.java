package tresna.mobile.movieapps.movienowplaying.main;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tresna.mobile.movieapps.R;
import tresna.mobile.movieapps.base.mvp.MvpFragment;
import tresna.mobile.movieapps.movienowplaying.adapter.NowAdapter;
import tresna.mobile.movieapps.movienowplaying.model.NowResponse;
import tresna.mobile.movieapps.movienowplaying.model.ResultsItem;
import tresna.mobile.movieapps.movienowplaying.presenter.NowPresenter;
import tresna.mobile.movieapps.movienowplaying.view.NowView;
import tresna.mobile.movieapps.utils.RecyclerItemClickListener;

public class NowFragment extends MvpFragment<NowPresenter> implements NowView {

    private List<ResultsItem> list;
    RecyclerView recyclerView;
    private NowPresenter nowPresenter;
    ProgressBar progressBar;

    @Override
    protected NowPresenter createPresenter() {
        return new NowPresenter(this);
    }

    public NowFragment() {

    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_now, container, false);

        nowPresenter = new NowPresenter(this);
        recyclerView = view.findViewById(R.id.rcvMovie);
        progressBar = view.findViewById(R.id.progress);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.addOnItemTouchListener(recyclerItemClickListener());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        nowPresenter.loadData();
        return view;
    }

    private RecyclerItemClickListener recyclerItemClickListener() {
        return new RecyclerItemClickListener(getActivity(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                nowPresenter.getItem(list.get(position), activity);
            }

            @Override
            public void onLongItemClick(View view, int position) {
                nowPresenter.getItem(list.get(position), activity);

            }
        });
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
    public void getDataSuccess(NowResponse nowResponse) {
        this.list = nowResponse.getResults();
        recyclerView.setAdapter(new NowAdapter(list, R.layout.card_movie, getActivity()));

    }

    @Override
    public void getDataFail(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void movieToDetail(Intent intent) {
        startActivity(intent);
    }

}