package com.example.rozeepk.Api;

import com.example.rozeepk.Class.AllFields;
import com.example.rozeepk.Class.Post;
import com.example.rozeepk.Class.User;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface Api {
    @FormUrlEncoded
    @POST("signupdata")
    Call<ResponseBody> signupdata(
            @Field("fullname") String fullname,
            @Field("email") String email,
            @Field("password") String pass,
            @Field("phone_no") String number,
            @Field("dob") String dob,
            @Field("gender") String gender,
            @Field("role") String role
    );
    @FormUrlEncoded
    @POST("empsignup")
    Call<ResponseBody> employersignupdata(
            @Field("fullname") String fullname,
            @Field("companydes") String email,
            @Field("email") String pass,
            @Field("city") String number,
            @Field("password") String dob,
            @Field("role") String role
    );
    @FormUrlEncoded
    @POST("emppost")
    Call<ResponseBody> employerjobpost(
            @Field("jobtitle") String fullname,
            @Field("jobdesc") String email,
            @Field("jobexp") String pass,
            @Field("city") String number,
            @Field("industry") String dob,
            @Field("jobtype") String role,
            @Field("department") String dep,
            @Field("eduction") String edu,
            @Field("careerlevel") String careerlevel,
            @Field("gender") String gender,
            @Field("post_date") String postdate,
            @Field("post_valid_date") String applydate
    );
//    @FormUrlEncoded
//    @POST("uploadimg")
//    Call<ResponseBody> uploadimg(
//            @Field("imgpath") String imgpath
//    );
    @Multipart
    @POST("uploadimg")
    Call<ResponseBody> updateProfile(@Part MultipartBody.Part image);

    @GET("loginrecord")
    Call<User> loginrecord(
            @Query("email") String email,
            @Query("password") String pass
    );
    @GET("AllJobPosts")
    Call<List<Post>> Allposts();
    @GET("AllCities")
    Call<ResponseBody> Allcities();
    @GET("AllCountryCode")
    Call<ResponseBody> Allcountrycode();
    @GET("Experience")
    Call<ResponseBody> Allexp();

    @GET("SearchJobs")
    Call<List<Post>> Allskills(
            @Query("job_title") String title
    );
    @GET("SearchJobsByCity")
    Call<List<Post>> searchbycity(
            @Query("city") String city
    );

}
