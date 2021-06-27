package tresna.mobile.movieapps.movietoprated.view;

import android.content.Intent;

import tresna.mobile.movieapps.movietoprated.model.TopRatedResponse;

public interface TopView {

    void showLoading();

    void hideLoading();

    void getDataSuccess(TopRatedResponse ratedResponse);

    void getDataFail(String message);

    void moveToDetail(Intent intent);

}
