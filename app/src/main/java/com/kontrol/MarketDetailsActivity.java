package com.kontrol;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MarketDetailsActivity extends ActionBarActivity {
    private static final String LOG_TAG = MarketDetailsActivity.class.getName();

    private static final String ID_KEY = "Elbe";

    Button b1;

    @InjectView(com.kontrol.R.id.name)
    TextView name;
    @InjectView(com.kontrol.R.id.fetchid)
    TextView fetchid;
    @InjectView(com.kontrol.R.id.subscriber)
    TextView subscriber;
    @InjectView(com.kontrol.R.id.videos)
    TextView videos;
    @InjectView(com.kontrol.R.id.comments)
    TextView comments;
    @InjectView(com.kontrol.R.id.views)
    TextView views;

    public static Intent makeIntent(Context context, String longname) {
        Intent intent = new Intent(context, MarketDetailsActivity.class);
        intent.putExtra(ID_KEY, longname);
        return intent;
    }

    public void showLog(View view) {
        Intent intent = new Intent(this, DetailLogActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(com.kontrol.R.layout.activity_market_details);
       // b1 = (Button) findViewById(R.id.b1);
      //  b1.setOnClickListener(myhandler1);
        ButterKnife.inject(this);
        Bundle extras = getIntent().getExtras();
        String newString= extras.getString(ID_KEY);
       // String longname = getIntent().getExtras(ID_KEY);

        final String longname = ID_KEY;
/*
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://sourcloud.com/pegel15/api")
                .build();

        PegelApi api = restAdapter.create(PegelApi.class);
        api.getMarketDetails(newString, new Callback<Market>() {
            @Override
            public void success(Market market, Response response) {

                fetchid.setText(market.fetchid);
                videos.setText("VIDEOS: " + market.videos);
                subscriber.setText("SUBSCRIBER: " + market.subscriber);
                comments.setText("COMMENTS: " + market.comments);
                views.setText("VIEWS: " + market.views);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(LOG_TAG, error.getMessage(), error);
                Toast.makeText(MarketDetailsActivity.this, com.kontrol.R.string.error_loading, Toast.LENGTH_LONG).show();
            }
        });
    */
        name.setText(newString);
    }


}
