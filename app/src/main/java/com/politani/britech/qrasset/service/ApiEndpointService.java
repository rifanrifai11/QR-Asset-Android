package com.politani.britech.qrasset.service;


import com.politani.britech.qrasset.models.ResponseToken;
import com.politani.britech.qrasset.models.ResponseUser;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Eko on 12/07/2017.
 */

public interface ApiEndpointService {

    @FormUrlEncoded
    @POST("token")
    Call<ResponseToken> getAccessToken(@Field("email") String email, @Field("password") String password);

    /*@FormUrlEncoded
    @POST("users")
    Call<ResponseUser> registerUser(@Field("kontak") String kontak, @Field("name") String nama, @Field("email") String email, @Field("nik") String nik, @Field("password") String password,
                                    @Field("password_confirmation") String password_confirmation);
*/
/*
    @FormUrlEncoded
    @POST("reset_password")
    Call<ResponseResetPassword> resetPassword(@Field("kontak") String kontak, @Field("email") String email);
*/

//    @GET("data_asets/show")
//    Call<ResponseDataAset> getDataAset(@Header("Authorization") String token, @Query("barcode") String barcode);
//
//    @GET("data_asets")
//    Call<ResponseListDataAset> syncDataAset(@Header("Authorization") String token);
//
//    @GET("users/show")
//    Call<ResponseUser> getUser(@Header("Authorization") String token);

/*
    @Multipart
    @POST("users/update")
    Call<ResponseUser> updateUser(@Header("Authorization") String token,
                                  @Part("name") RequestBody name, @Part("email") RequestBody email, @Part("nik") RequestBody kontak,
                                  @Part("no_sambungan") RequestBody no_sambungan, @Part("pekerjaan") RequestBody pekerjaan,
                                  @Part("alamat") RequestBody alamat,
                                  @Part MultipartBody.Part file);

    @FormUrlEncoded
    @POST("users/password")
    Call<ResponseUser> changePassword(@Header("Authorization") String token, @Field("current-password") String current_password,
                                      @Field("password") String password, @Field("password_confirmation") String password_confirmation);
*/


//    @GET("kondisi_asets")
//    Call<ResponseKondisiAset> getKondisiAset(@Header("Authorization") String token);
//

//    @FormUrlEncoded
//    @POST("aset_takings")
//    Call<ResponseStoreAsetTaking> setAsetTaking(@Header("Authorization") String token, @Field("kode_data_aset") String kode_data_aset,
//                                                @Field("kondisi_aset_id") String kondisi_aset_id);


}
