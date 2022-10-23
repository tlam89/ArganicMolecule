package com.examples.arganicmolecule2.network;

import com.examples.arganicmolecule2.model.molecule;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIinterface {
    @GET("b/ZIDH")
    Call<List<molecule>> getmolecule();

}
