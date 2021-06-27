package tresna.mobile.movieapps.apirepository;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import tresna.mobile.movieapps.BuildConfig;
import tresna.mobile.movieapps.movienowplaying.model.NowResponse;
import tresna.mobile.movieapps.movietoprated.model.TopRatedResponse;
import tresna.mobile.movieapps.movieupcoming.model.UpResponse;
import tresna.mobile.movieapps.searchmovie.model.SearchResponse;

public interface ApiService {

    @GET("search/movie?api_key=" + BuildConfig.TOKEN + BuildConfig.QUERY)
    Observable<SearchResponse> searchMovie(@Query("query") String movie);

    @GET(BuildConfig.MOVIE + "now_playing?api_key=" + BuildConfig.TOKEN)
    Observable<NowResponse> getNowPlaying();

    @GET(BuildConfig.MOVIE + "upcoming?api_key=" + BuildConfig.TOKEN)
    Observable<UpResponse> getUpcoming();


    @GET(BuildConfig.MOVIE + "top_rated?api_key=" + BuildConfig.TOKEN)
    Observable<TopRatedResponse>getToprated();
}
