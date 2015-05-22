
package com.kontrol;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

public interface PegelApi {

    @GET("/messlog/short")
    void messLog(Callback<List<Messlogshort>> callback);

    @GET("/fonpit/jobs")
    void messstationen(Callback<List<Stationenshort>> callback);


    @GET("/messlog/short/{river}")
    void getMarketDetails(@Path("river") String river, Callback<Market> callback);

}
