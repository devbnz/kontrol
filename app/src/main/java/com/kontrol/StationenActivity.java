package com.kontrol;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.mixpanel.android.mpmetrics.MixpanelAPI;
import com.mopub.common.MoPub;

import java.util.List;

import butterknife.ButterKnife;
import io.fabric.sdk.android.Fabric;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Erik on 5/4/2015.
 */
public class StationenActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ListView marketsListView;
    private List<Stationenshort> marketList;
    private static final String TAG = "MainActivity";
    private static final String ID_KEY = "Elbe";

    public static final String MIXPANEL_TOKEN = "8d49da4795151ec017f81fbcac53c704";
    public static final String ANDROID_PUSH_SENDER_ID = "899342369017";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics(), new MoPub());
        final String trackingDistinctId = "bnz";
        setContentView(R.layout.activity_stationen);

        Bundle extras = getIntent().getExtras();
        String newString = extras.getString(ID_KEY);

        MixpanelAPI mMixpanel = MixpanelAPI.getInstance(StationenActivity.this, MIXPANEL_TOKEN);
        MixpanelAPI.People people = mMixpanel.getPeople();
        people.identify(trackingDistinctId);
        mMixpanel.identify(trackingDistinctId);
        mMixpanel.getPeople().identify(trackingDistinctId);

        people.initPushHandling(ANDROID_PUSH_SENDER_ID);


        this.marketsListView = ButterKnife.findById(StationenActivity.this, R.id.stationen_listview);
        this.marketsListView.setOnItemClickListener(this);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://sourcloud.com/pegel15/api")
                .build();
        Log.e(TAG, newString);
        PegelApi api = restAdapter.create(PegelApi.class);
        api.messstationen(new Callback<List<Stationenshort>>() {
            @Override
            public void success( List<Stationenshort> hvalues, Response response) {
                marketList = hvalues;
                marketsListView.setAdapter(
                        new ArrayAdapter<>(
                                StationenActivity.this,
                                android.R.layout.simple_list_item_1,
                                android.R.id.text1,
                                marketList));
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, error.getMessage(), error);
                Toast.makeText(StationenActivity.this, com.kontrol.R.string.error_loading, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.v(TAG, "item-id=" + this.marketList.get(position).id);
       // startActivity(MarketDetailsActivity.makeIntent(this, this.marketList.get(position).uuid));
    }

}
