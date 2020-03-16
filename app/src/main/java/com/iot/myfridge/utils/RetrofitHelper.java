package com.iot.myfridge.utils;

import com.iot.myfridge.model.MyResponse;
import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;


public class RetrofitHelper {

    public static final String BASE_URL = Constants.URLS.BASE_URL;

    static public Retrofit create(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit;
    }

    static public Observable<MyResponse> getResponse(String query, int from, int to ){
        Retrofit retrofit = create();
        RequestService service = retrofit.create(RequestService.class);
        return service.responseService( query, from, to );
    }

    public interface RequestService{
        @GET( Constants.URLS.BASE_URL + "search?app_id=" + Constants.VALUES.APP_ID +"&app_key="
                + Constants.VALUES.APP_KEY )
        Observable<MyResponse> responseService(
                @Query("q") String query,
                @Query("from") int from,
                @Query("to") int to);

    }
}