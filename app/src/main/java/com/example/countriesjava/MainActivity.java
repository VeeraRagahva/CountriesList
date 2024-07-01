package com.example.countriesjava;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.rvCountries);
        progressBar = findViewById(R.id.progressBar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        CountryViewModel countryViewModel = new ViewModelProvider(this).get(CountryViewModel.class);

        countryViewModel.getCountries().observe(this, new Observer<List<Country>>() {
            @Override
            public void onChanged(List<Country> countries) {
                recyclerView.setAdapter(new CountryAdapter(countries));
                progressBar.setVisibility(View.GONE); // Hide the progress bar when data is loaded
            }
        });

        countryViewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String error) {
                Toast.makeText(MainActivity.this, error, Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE); // Hide the progress bar on error
            }
        });

        progressBar.setVisibility(View.VISIBLE); // Show the progress bar while data is loading
    }
}
