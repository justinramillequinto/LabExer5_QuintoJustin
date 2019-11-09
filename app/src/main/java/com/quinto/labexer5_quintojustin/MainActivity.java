package com.quinto.labexer5_quintojustin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    String[] names, countries, industries, ceo, descriptions;
    int[] logo = {R.drawable.icbc, R.drawable.jpmorganchase, R.drawable.chinaconstructionbank, R.drawable.agriculturalbankofchina,
            R.drawable.bankofamerica, R.drawable.apple, R.drawable.pinganinsurancegroup, R.drawable.bankofchina, R.drawable.royaldutchshell,
            R.drawable.wellsfargo, R.drawable.exxonmobil, R.drawable.att, R.drawable.samsungelectronics, R.drawable.citigroup};
    ArrayList<Company> companies = new ArrayList<Company>();
    ListView lvCompanies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        names = getResources().getStringArray(R.array.company);
        countries = getResources().getStringArray(R.array.country);
        industries = getResources().getStringArray(R.array.industry);
        ceo = getResources().getStringArray(R.array.ceo);
        descriptions = getResources().getStringArray(R.array.description);

        for (int i = 0; i < names.length; i++) {
            companies.add(new Company(logo[i], names[i], countries[i], industries[i], ceo[i]));
        }

        AndroidAdapter adapter = new AndroidAdapter(this, R.layout.item, companies);
        lvCompanies = findViewById(R.id.lvAndroid);
        lvCompanies.setAdapter(adapter);
        lvCompanies.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        final File folder = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        final File file = new File(folder, "companies.txt");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            String company = companies.get(position).getCompany();
            String country = companies.get(position).getCountry();
            String industry = companies.get(position).getIndustry();
            String ceo = companies.get(position).getCeo();

            fileOutputStream.write(company.getBytes());
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setIcon(logo[position]);
            dialog.setTitle(names[position]);
            dialog.setMessage(descriptions[position]);
            dialog.setNeutralButton("Close", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    String contain = "";

                    try {
                        FileInputStream fis = new FileInputStream(file);
                        int token;
                        while((token = fis.read()) != -1) {
                            contain = contain + (char)token;
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(MainActivity.this, contain, Toast.LENGTH_LONG).show();
                }
            });
            dialog.create().show();
        } catch (FileNotFoundException e) {
            Toast.makeText(MainActivity.this, "File Not Found Exception", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(MainActivity.this, "IO Exception", Toast.LENGTH_LONG).show();
        }
    }
}
