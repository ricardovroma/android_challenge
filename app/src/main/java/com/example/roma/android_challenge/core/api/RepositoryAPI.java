package com.example.roma.android_challenge.core.api;

import android.content.Context;

import com.example.roma.android_challenge.BuildConfig;
import com.example.roma.android_challenge.repository_list.models.RepositoryEnvelopModelRest;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;


public class RepositoryAPI {

    public final API service;

    public RepositoryAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getOkHttpClient())
                .build();

        this.service = retrofit.create(API.class);
    }

    public interface API {

        @GET("search/repositories")
        Flowable<RepositoryEnvelopModelRest> repository(@Query("q") String q, @Query("sort") String sort, @Query("page") int page);

    }

    public OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.readTimeout(50, TimeUnit.SECONDS);
        client.connectTimeout(10, TimeUnit.SECONDS);
        client.addInterceptor(
                new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();

                        // Request customization: add request headers
                        Response response;
                        Request.Builder requestBuilder = original.newBuilder()
                                .method(original.method(), original.body());

                        Request request = requestBuilder.build();
                        response = chain.proceed(request);

                        return response;
                    }
                }
        );
        return client.build();
    }
}
