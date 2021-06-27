package tresna.mobile.movieapps.movienowplaying.view;

import android.content.Intent;

import tresna.mobile.movieapps.movienowplaying.model.NowResponse;

public interface NowView {

    void showLoading();

    void hideLoading();

    void getDataSuccess(NowResponse nowResponse);

    void getDataFail(String message);

    void movieToDetail(Intent intent);


}
