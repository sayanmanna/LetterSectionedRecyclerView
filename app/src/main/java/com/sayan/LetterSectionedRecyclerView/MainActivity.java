package com.sayan.LetterSectionedRecyclerView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    private CountriesAdapter mAdapter;
    private ArrayList<CountriesModel> mSectionList;
    private String[] mCountries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCountries = getApplicationContext().getResources().getStringArray(R.array.countries);
        ArrayList<CountriesModel> countriesModels = new ArrayList<>();
        for (int j = 0; j < mCountries.length ; j++) {
            countriesModels.add(new CountriesModel(mCountries[j].toString(),false));
        }

        mSectionList = new ArrayList<>();
        getHeaderListLatter(countriesModels);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new CountriesAdapter(mSectionList, this);
        recyclerView.setAdapter(mAdapter);

    }


    private void getHeaderListLatter(ArrayList<CountriesModel> usersList) {

        Collections.sort(usersList, new Comparator<CountriesModel>() {
            @Override
            public int compare(CountriesModel user1, CountriesModel user2) {
                return String.valueOf(user1.name.charAt(0)).toUpperCase().compareTo(String.valueOf(user2.name.charAt(0)).toUpperCase());
            }
        });

        String lastHeader = "";

        int size = usersList.size();

        for (int i = 0; i < size; i++) {

            CountriesModel user = usersList.get(i);
            String header = String.valueOf(user.name.charAt(0)).toUpperCase();

            if (!TextUtils.equals(lastHeader, header)) {
                lastHeader = header;
                mSectionList.add(new CountriesModel(header,true));
            }

            mSectionList.add(user);
        }
    }
}
