package com.mala.movie;

import com.facebook.stetho.BuildConfig;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class RetrofitBuilder {
    private static final String BASE_URL = "https://api.thetvdb.com/";

    private final static OkHttpClient client = buildClient();
    private final static Retrofit retrofit = buildRetrofit(client);

    private static OkHttpClient buildClient(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();

                        Request.Builder builder = request.newBuilder()
                                .addHeader("Accept", "application/json")
                                .addHeader("Connection","close");


                        builder.addHeader("Authorization", "Bearer " + "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1Njk1NzIwMTgsImlkIjoicHJvamVjdF9zYXlhIiwib3JpZ19pYXQiOjE1Njk0ODU2MTgsInVzZXJpZCI6NTM4NTc3LCJ1c2VybmFtZSI6ImRhbnJtbHNhcmlzZTcifQ.gZV2SGnaGwXYP8jqv6TDPJ3SPd8jlx-dm40GYsnkr0tm_HHg_MmF5txkknUBWrGYz0rXcGbmt2FHxWSDLPgR5PNyPt_ALUyJuJljlw6VQP9vfLoO4FXHosa47SsT5t3C_NJ48jbl6gnuQU2hPahK1wLUSgpV07zf61QgCXSRzXPqu6MgqTzokx0TsDePEI47N5iRPxZUNLkwWHQpyqsKJVHUYZRE-vWPZGJfAuTIxODuUqnmVHrDe-AlAr94q-hA4pFoR0VvXTYVVJWY2Xi9hRlmOiXPamslOg3vw_P8NAUDlMMzEf-ubkaMhfo4kgX_G7s1Ir5Hy2RH2kSXm_-vPA");


                        request = builder.build();

                        return chain.proceed(request);
                    }
                });

        if (BuildConfig.DEBUG){
            builder.addNetworkInterceptor(new StethoInterceptor());
        }

        return builder.build();
    }

    private static Retrofit buildRetrofit(OkHttpClient client){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();
    }

    public static <T> T createService(Class<T> service){
        return retrofit.create(service);
    }

    public static Retrofit getRetrofit(){
        return retrofit;
    }


}
