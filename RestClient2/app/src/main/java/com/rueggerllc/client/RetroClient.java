package com.rueggerllc.client;


import retrofit2.Retrofit;

public class RetroClient {

    private static final String ROOT_URL = "http://192.168.1.167:8080/RestWeb/";


    public RetroClient() {

    }

    /**
     * Get Retro Client
     *
     * @return JSON Object
     */
    private static Retrofit getRetroClient() {
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                // .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static FileUploadService getApiService() {
        return getRetroClient().create(FileUploadService.class);
    }

}
