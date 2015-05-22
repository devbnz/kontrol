package com.kontrol;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.mopub.common.MoPub;
import io.fabric.sdk.android.Fabric;
import java.util.List;

import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import com.mixpanel.android.mpmetrics.MixpanelAPI;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView marketsListView;
    private List<Messlogshort> marketList;
    private static final String TAG = "MainActivity";

    public static final String MIXPANEL_TOKEN = "8d49da4795151ec017f81fbcac53c704";
    public static final String ANDROID_PUSH_SENDER_ID = "899342369017";

    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private ArrayAdapter<String> mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;

    private static final String ID_KEY = "Elbe";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics(), new MoPub());
        final String trackingDistinctId = "bnz";
        setContentView(com.kontrol.R.layout.activity_main);

        mDrawerList = (ListView)findViewById(R.id.riverlist);mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();

        addDrawerItems();
        setupDrawer();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);



        MixpanelAPI mMixpanel = MixpanelAPI.getInstance(MainActivity.this, MIXPANEL_TOKEN);
        MixpanelAPI.People people = mMixpanel.getPeople();
        people.identify(trackingDistinctId);
        mMixpanel.identify(trackingDistinctId); //this is the distinct_id value that
        // will be sent with events. If you choose not to set this,
        // the SDK will generate one for you

        mMixpanel.getPeople().identify(trackingDistinctId);

        JSONObject props = new JSONObject();
        try {
            props.put("Action", "Someone opened Mainactivity");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            props.put("Value", "BNZs Test value");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mMixpanel.track("Plan Selected", props);

        people.initPushHandling(ANDROID_PUSH_SENDER_ID);


        //this.marketsListView = ButterKnife.findById(MainActivity.this, com.kontrol.R.id.markets_listview);
        //this.marketsListView.setOnItemClickListener(this);
/*
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://sourcloud.com/pegel15/api")
                .build();

        PegelApi api = restAdapter.create(PegelApi.class);
        api.messLog(new Callback<List<Messlogshort>>() {
            @Override
            public void success(List<Messlogshort> mvalues, Response response) {
                marketList = mvalues;
                marketsListView.setAdapter(
                        new ArrayAdapter<>(
                                MainActivity.this,
                                android.R.layout.simple_list_item_1,
                                android.R.id.text1,
                                marketList));
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, error.getMessage(), error);
                Toast.makeText(MainActivity.this, com.kontrol.R.string.error_loading, Toast.LENGTH_LONG).show();
            }
        });*/
    }

    private void addDrawerItems() {
        final String[] osArray = {"Dashboard", "Youtube", "Facebook", "Twitter", "G+", "Jobs", "Impressum"};
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mDrawerList.getItemAtPosition(position).toString() == "Jobs") {
                    //  Log.e(TAG, mDrawerList.getItemAtPosition(position).toString());
                    Intent intent = new Intent(MainActivity.this, StationenActivity.class);
                    intent.putExtra(ID_KEY, mDrawerList.getItemAtPosition(position).toString());
                   // intent.putExtra(ID_KEY, "job-listings?format=json");
                    startActivity(intent);
                } else {

                    Toast.makeText(MainActivity.this, "Not implemented yet!", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(com.kontrol.R.string.drawer);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
        //    return true;
        //}

        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onDestroy() {
       // mixpanel.flush();
        super.onDestroy();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
