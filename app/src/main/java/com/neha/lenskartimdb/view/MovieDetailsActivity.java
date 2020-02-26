package com.neha.lenskartimdb.view;

import android.content.Intent;
import android.os.Bundle;

import com.neha.lenskartimdb.R;
import com.neha.lenskartimdb.base.Constants;
import com.neha.lenskartimdb.databinding.ActivityMovieDetailsBinding;
import com.neha.lenskartimdb.model.Movie;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class MovieDetailsActivity extends AppCompatActivity {

    private ActivityMovieDetailsBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details);

        Intent intent = getIntent();
        if(getIntent()!=null){
            Movie movie = (Movie) intent.getSerializableExtra(Constants.MOVIE_INTENT);
            if(movie!=null)
                binding.setMovie(movie);
        }
    }
}
