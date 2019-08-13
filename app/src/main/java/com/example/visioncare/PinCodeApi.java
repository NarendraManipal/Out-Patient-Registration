package com.example.visioncare;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PinCodeApi {
    String BASE_URL = "https://api.postalpincode.in/pincode/";

    @GET("{pincode}")
    Call<String> getPostOffice(@Path("pincode") String pincode);
}
