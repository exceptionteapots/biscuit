package ru.exceptionteapots.pricetrace;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * singleton-класс, необходимый для связывания с API
 * */
public class NetworkService {
    private static NetworkService mInstance;
    private static final String BASE_URL = "https://api.pricetrace.ru";
    private Retrofit mRetrofit;

    private NetworkService() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static NetworkService getInstance() {
        if (mInstance == null) {
            mInstance = new NetworkService();
        }
        return mInstance;
    }

    public PriceTraceAPI getPriceTraceAPI() {
        return mRetrofit.create(PriceTraceAPI.class);
    }
}
