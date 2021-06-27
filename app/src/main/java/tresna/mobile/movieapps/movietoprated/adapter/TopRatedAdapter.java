package tresna.mobile.movieapps.movietoprated.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import tresna.mobile.movieapps.BuildConfig;
import tresna.mobile.movieapps.R;
import tresna.mobile.movieapps.movietoprated.model.ResultsItem;

public class TopRatedAdapter extends RecyclerView.Adapter<TopRatedAdapter.TopHolder> {

    private List<ResultsItem> resultsItems;
    private int rowLayout;
    private Context context;

    public TopRatedAdapter(List<ResultsItem> items, int rowLayout, Context context){
        this.resultsItems = items;
        this.context = context;
        this.rowLayout = rowLayout;
    }

    @NonNull
    @Override
    public TopRatedAdapter.TopHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);

        return new TopHolder(view);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(@NonNull TopRatedAdapter.TopHolder topHolder, int i) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.background);

        topHolder.mTxtTitle.setText(resultsItems.get(i).getTitle());
        topHolder.mTxtGenre.setText(resultsItems.get(i).getReleaseDate());

        Glide.with(context)
                .setDefaultRequestOptions(requestOptions)
                .load(BuildConfig.IMAGE + resultsItems.get(i).getPosterPath())
                .into(topHolder.mImage);
    }

    @Override
    public int getItemCount() {
        return resultsItems.size();
    }

    public class TopHolder extends RecyclerView.ViewHolder{
        ImageView mImage;
        TextView mTxtGenre, mTxtTitle;
        public TopHolder(@NonNull View itemView) {
            super(itemView);

            mImage = itemView.findViewById(R.id.imgPoster);
            mTxtGenre = itemView.findViewById(R.id.txtGenre);
            mTxtTitle = itemView.findViewById(R.id.txtTitle);
        }
    }
}
