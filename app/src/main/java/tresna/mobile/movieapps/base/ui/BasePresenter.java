package tresna.mobile.movieapps.base.ui;

import android.util.Log;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import tresna.mobile.movieapps.apirepository.ApiService;
import tresna.mobile.movieapps.apirepository.InitRetrofit;


public class BasePresenter<V> {
    public V view;
    protected ApiService apiServices;
    private CompositeSubscription compositeSubscription;
    private Subscriber subscriber;

    protected void attachView(V view) {
        this.view = view;
        apiServices = InitRetrofit.getRetrofit().create(ApiService.class);
    }

    public void dettachView() {
        this.view = null;
        onUnsubscribe();
    }

    private void onUnsubscribe() {
        if (compositeSubscription != null && compositeSubscription.hasSubscriptions()) {
            compositeSubscription.unsubscribe();
            Log.e("TAG", "onUnsubscribe: ");
        }
    }

    protected void addSubscribe(Observable observable, Subscriber subscriber) {
        this.subscriber = subscriber;
        if (compositeSubscription == null) {
            compositeSubscription = new CompositeSubscription();
        }
        compositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }

    public void stop() {
        if (subscriber != null) {
            subscriber.unsubscribe();
        }
    }
}
