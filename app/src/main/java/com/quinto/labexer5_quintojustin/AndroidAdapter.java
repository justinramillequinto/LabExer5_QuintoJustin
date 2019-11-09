package com.quinto.labexer5_quintojustin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AndroidAdapter extends ArrayAdapter<Company> {
    private Context context;
    private int resource;

    public AndroidAdapter(@NonNull Context context, int resource, @NonNull List<Company> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        int logo = getItem(position).getLogo();
        String name = getItem(position).getCompany();
        String country = getItem(position).getCountry();
        String industry = getItem(position).getIndustry();
        String ceo = getItem(position).getCeo();

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource, parent, false);

        ImageView img = convertView.findViewById(R.id.ivLogo);
        TextView txtName = convertView.findViewById(R.id.txtName);
        TextView txtCountry = convertView.findViewById(R.id.txtCountry);
        TextView txtIndustry = convertView.findViewById(R.id.txtIndustry);
        TextView txtCeo = convertView.findViewById(R.id.txtCeo);

        img.setImageResource(logo);
        txtName.setText(name);
        txtCountry.setText("Country: " + country);
        txtIndustry.setText("Industry: " + industry);
        txtCeo.setText("CEO: " + ceo);
        return convertView;
    }
}
