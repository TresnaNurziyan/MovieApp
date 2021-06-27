package tresna.mobile.movieapps.base.mvp;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import tresna.mobile.movieapps.base.ui.BaseFragment;
import tresna.mobile.movieapps.base.ui.BasePresenter;

public abstract class MvpFragment<P extends BasePresenter> extends BaseFragment {
    protected P presenter;

    protected abstract P createPresenter();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        presenter = createPresenter();
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.dettachView();
        }
    }
}
