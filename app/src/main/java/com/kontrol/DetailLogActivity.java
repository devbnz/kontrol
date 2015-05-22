package com.kontrol;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Erik on 5/4/2015.
 */
public class DetailLogActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView ListView;
    private List<Messlogshort> marketList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_detail_log);
        this.ListView = ButterKnife.findById(DetailLogActivity.this, com.kontrol.R.id.listView);
        //ButterKnife.inject(this);
        //Bundle extras = getIntent().getExtras();
        //Toast.makeText(DetailLogActivity.this, com.kontrol.R.string.error_loading, Toast.LENGTH_LONG).show();


        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://sourcloud.com/pegel15/api")
                .build();

        PegelApi api = restAdapter.create(PegelApi.class);
        api.messLog(new Callback<List<Messlogshort>>() {
            @Override
            public void success(List<Messlogshort> mvalues, Response response) {
                marketList = mvalues;
                ListView.setAdapter(
                        new ArrayAdapter<>(
                                DetailLogActivity.this,
                                android.R.layout.simple_list_item_1,
                                android.R.id.text1,
                                marketList));
            }

            @Override
            public void failure(RetrofitError error) {
               // Log.e(TAG, error.getMessage(), error);
                Toast.makeText(DetailLogActivity.this, com.kontrol.R.string.error_loading, Toast.LENGTH_LONG).show();
            }
        });

//        name.setText(newString);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    //    Log.v(TAG, "item-id=" + this.marketList.get(position).longname);
     //   startActivity(MarketDetailsActivity.makeIntent(this, this.marketList.get(position).longname));
    }
}
