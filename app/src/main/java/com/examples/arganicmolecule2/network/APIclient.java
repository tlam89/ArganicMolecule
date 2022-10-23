package com.examples.arganicmolecule2.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIclient {

    //define base url
    public static String base_url = "https://www.jsonkeeper.com/";

   //retrofit
    public static Retrofit getClient(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(base_url )
               .addConverterFactory(GsonConverterFactory.create()).build();

        return retrofit;
    }

    public static APIinterface apiInterface(){
        return getClient().create(APIinterface.class);
    }

}
