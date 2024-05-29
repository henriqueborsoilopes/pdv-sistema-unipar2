package br.com.borsoitech.pdv.model.service.interfaces;

import br.com.borsoitech.pdv.model.type.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface IUserService {
    
    @FormUrlEncoded
    @POST("oauth/token")
    Call<LoginResponse> login(
        @Header("Authorization") String authorization,
        @Header("Content-Type") String contentType,
        @Field("username") String username,
        @Field("password") String password,
        @Field("grant_type") String grantType
    );
}