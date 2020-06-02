package com.adapt.attendance.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClient {
    private static Retrofit getRetroInstance(String URL){
        Gson gson = new GsonBuilder()
                .setLenient()
                .registerTypeAdapter(int.class, new IntTypeAdapter())
                .registerTypeAdapter(Integer.class, new IntTypeAdapter()).create();

        return new Retrofit.Builder()
                .baseUrl(URL+HC.BASE_URL)
                .client(header(HC.U,HC.P))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    private static OkHttpClient header(String username, String password){
        String credentials = Credentials.basic(username,password);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor(){
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header(HC.AUTH,credentials )
                        .method(original.method(), original.body())
                        .build();
                return chain.proceed(request);
            }
        });
        return httpClient.build();
    }

    public static ApiService getApiService(String URL){
        return getRetroInstance(URL).create(ApiService.class);
    }
    public static class IntTypeAdapter extends TypeAdapter<Number> {
        @Override
        public void write(JsonWriter out, Number value)
                throws IOException {
            out.value(value);
        }

        @Override
        public Number read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            try {
                String result = in.nextString();
                if ("".equals(result)) {
                    return null;
                }
                return Integer.parseInt(result);
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }
    }
}
