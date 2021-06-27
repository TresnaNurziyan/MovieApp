package tresna.mobile.movieapps.searchmovie.view;

import android.content.Intent;

import tresna.mobile.movieapps.searchmovie.model.SearchResponse;

public interface SearchView {

    void showLoading();

    void  hideLoading();

    void getSuccess(SearchResponse searchResponse);

    void getDataFail(String message);

    void moveToDetail(Intent intent);

}
