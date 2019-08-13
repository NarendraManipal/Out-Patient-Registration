package com.example.visioncare;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface OPDetailsApi {
    String BASE_URL = "http://vision.mycomputerudupi.com/OPDetailsService.svc/";

    @POST("InsertOpDetails?strDatabase=mc_vision")
    public Call<ResponseBody> postOpDetails(@Body OPDetailsDTO opDetails);
}
