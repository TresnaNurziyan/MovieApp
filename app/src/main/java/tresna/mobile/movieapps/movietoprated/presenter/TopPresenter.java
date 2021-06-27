package tresna.mobile.movieapps.movietoprated.presenter;

import android.app.Activity;
import android.content.Intent;

import tresna.mobile.movieapps.DetailActivity;
import tresna.mobile.movieapps.apirepository.NetworkCallback;
import tresna.mobile.movieapps.base.ui.BasePresenter;
import tresna.mobile.movieapps.movietoprated.model.ResultsItem;
import tresna.mobile.movieapps.movietoprated.model.TopRatedResponse;
import tresna.mobile.movieapps.movietoprated.view.TopView;

public class TopPresenter extends BasePresenter<TopView> {

    public TopPresenter(TopView topView){
        super.attachView(topView);
    }

    public void loadData(){
        view.showLoading();
        addSubscribe(apiServices.getToprated(), new NetworkCallback<TopRatedResponse>() {

            @Override
            public void onSuccess(TopRatedResponse model) {
                view.getDataSuccess(model);
            }

            @Override
            public void onFailure(String message) {
                view.getDataFail(message);

            }

            @Override
            public void onFinish() {
                view.hideLoading();
            }
        });
    }

    public void getItem(ResultsItem item, Activity activity){
        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra("id", item.getId());
        intent.putExtra("title", item.getTitle());
        intent.putExtra("date", item.getReleaseDate());
        intent.putExtra("overview", item.getOverview());
        intent.putExtra("poster", item.getPosterPath());
        intent.putExtra("banner", item.getBackdropPath());
        view.moveToDetail(intent);
    }

}
