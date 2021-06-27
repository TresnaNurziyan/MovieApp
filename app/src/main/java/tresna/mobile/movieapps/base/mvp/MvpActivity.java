package tresna.mobile.movieapps.base.mvp;

import android.os.Bundle;

import androidx.annotation.Nullable;

import tresna.mobile.movieapps.base.ui.BaseActivity;
import tresna.mobile.movieapps.base.ui.BasePresenter;

public abstract class MvpActivity<P extends BasePresenter> extends BaseActivity {
    protected P presenter;

    protected abstract P createPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        presenter = createPresenter();
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.dettachView();
        }
    }
}