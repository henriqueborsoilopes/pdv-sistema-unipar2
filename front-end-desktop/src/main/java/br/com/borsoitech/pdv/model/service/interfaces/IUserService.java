package br.com.borsoitech.pdv.model.service.interfaces;

import br.com.borsoitech.pdv.model.type.LoginResponse;
import retrofit2.Call;
import retrofit2.http.*;

public interface IUserService {

    @POST("/oauth/token")
    @FormUrlEncoded
    @Headers({
            "Accept: application/json"
    })
    Call<LoginResponse> getAccessToken(@Field("grant_type") String grantType,
                                       @Field("username") String username,
                                       @Field("password") String password,
                                       @Header("Authorization") String authorization);
}