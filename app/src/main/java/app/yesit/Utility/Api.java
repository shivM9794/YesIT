package app.yesit.Utility;

import app.yesit.ResponseModel.Example;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @Headers({"Accept: application/json"})
    @GET("demos/marvel/")
    Call<Example> marvel(@Field("name") String name,
                         @Field("realname") String realname,
                         @Field("team") String team,
                         @Field("firstappearance") String firstappearance,
                         @Field("createdby") String createdby,
                         @Field("publisher") String publisher,
                         @Field("imageurl") String imageurl,
                         @Field("bio") String bio);

}
