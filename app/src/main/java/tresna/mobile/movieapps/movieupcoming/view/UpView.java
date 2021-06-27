package tresna.mobile.movieapps.movieupcoming.view;

import android.content.Intent;

import tresna.mobile.movieapps.movieupcoming.model.UpResponse;

public interface UpView {

    void showLoading();

    void hideLoading();

    void getSuccess(UpResponse upResponse);

    void getDataFail(String message);

    void moveToDetail(Intent intent);
}
