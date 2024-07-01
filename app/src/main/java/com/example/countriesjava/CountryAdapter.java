package com.example.countriesjava;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    private final List<Country> countries;

    public CountryAdapter(List<Country> countries) {
        this.countries = countries;
    }

    public static class CountryViewHolder extends RecyclerView.ViewHolder {
        public TextView nameRegionCode;
        public TextView capital;

        public TextView code;

        public CountryViewHolder(View itemView) {
            super(itemView);
            nameRegionCode = itemView.findViewById(R.id.nameRegion);
            code = itemView.findViewById(R.id.code);
            capital = itemView.findViewById(R.id.capital);
        }
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country, parent, false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        Country country = countries.get(position);
        holder.nameRegionCode.setText(country.getName() + ", " + country.getRegion() );
        holder.code.setText(country.getCode());
        holder.capital.setText(country.getCapital());
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }
}
