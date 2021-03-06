package tresna.mobile.movieapps.base.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;
import rx.subscriptions.CompositeSubscription;

public class BaseFragment extends Fragment {
    public Activity activity;
    CompositeSubscription compositeSubscription;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        activity = getActivity();
        ButterKnife.bind(activity, view);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        onUnsubscribe();
    }
    public void onUnsubscribe() {
        if (compositeSubscription != null) {
            compositeSubscription.unsubscribe();
        }
    }
}