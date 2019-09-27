package com.mala.movie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

import com.mala.movie.Model.Episode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ApiService service;
    @BindView(R.id.rv_series)
    RecyclerView recyclerEpisode;
    private ArrayList<Episode> episodeList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        LinearLayoutManager llmService=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerEpisode.setLayoutManager(llmService);
        recyclerEpisode.setHasFixedSize(true);

        getEpisodes();
    }

    private void getEpisodes(){
        service = RetrofitBuilder.createService(ApiService.class);
        Call<GetEpisodesResponse> call = service.getEpisodes(70327);
        call.enqueue(new Callback<GetEpisodesResponse>() {
            @Override
            public void onResponse(Call<GetEpisodesResponse> call, Response<GetEpisodesResponse> response) {
                if(response.isSuccessful()){
                    episodeList = new ArrayList<>();
                    for (Episode episode: response.body().getEpisodes()) {
                        episodeList.add(episode);
                    }
                    EpisodeAdapter episodesAdapter = new EpisodeAdapter(episodeList, MainActivity.this);
                    recyclerEpisode.setAdapter(episodesAdapter);
                } else {
                    Toast toast = Toast.makeText(MainActivity.this, "API Error", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<GetEpisodesResponse> call, Throwable t) {
                Toast toast = Toast.makeText(MainActivity.this, "Network Failure", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
            }
        });
    }


}
