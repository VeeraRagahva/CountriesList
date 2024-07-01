package com.example.countriesjava;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryViewModel extends AndroidViewModel {

    private final MutableLiveData<List<Country>> countries;
    private final MutableLiveData<String> error;

    public CountryViewModel(@NonNull Application application) {
        super(application);
        countries = new MutableLiveData<>();
        error = new MutableLiveData<>();
        fetchCountries();
    }

    public LiveData<List<Country>> getCountries() {
        return countries;
    }

    public LiveData<String> getError() {
        return error;
    }

    private void fetchCountries() {
        CountryService service = RetrofitInstance.getCountryService();
        Call<List<Country>> call = service.getCountries();

        call.enqueue(new Callback<List<Country>>() {

            public void onResponse(@NonNull Call<List<Country>> call, @NonNull Response<List<Country>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    countries.postValue(response.body());
                } else {
                    error.postValue("Error: " + response.message());
                }
            }


            public void onFailure(@NonNull Call<List<Country>> call, @NonNull Throwable t) {
                error.postValue("Failure: " + t.getMessage());
            }
        });
    }
}
