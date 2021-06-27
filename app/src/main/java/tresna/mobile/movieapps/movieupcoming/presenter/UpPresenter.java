package tresna.mobile.movieapps.movieupcoming.presenter;

import android.app.Activity;
import android.content.Intent;

import tresna.mobile.movieapps.DetailActivity;
import tresna.mobile.movieapps.apirepository.NetworkCallback;
import tresna.mobile.movieapps.base.ui.BasePresenter;
import tresna.mobile.movieapps.movieupcoming.model.ResultsItem;
import tresna.mobile.movieapps.movieupcoming.model.UpResponse;
import tresna.mobile.movieapps.movieupcoming.view.UpView;

public class UpPresenter extends BasePresenter<UpView> {

    public UpPresenter(UpView upView){
        super.attachView(upView);
    }

    public void loadData(){
        view.showLoading();
        addSubscribe(apiServices.getUpcoming(), new NetworkCallback<UpResponse>() {

            @Override
            public void onSuccess(UpResponse model) {
                view.getSuccess(model);
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
