package br.com.borsoitech.pdv.model.service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import br.com.borsoitech.pdv.model.service.interfaces.IUserService;
import br.com.borsoitech.pdv.model.type.LoginRequest;
import br.com.borsoitech.pdv.model.type.LoginResponse;
import com.google.gson.Gson;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AutorizacaoService {
	
	private IUserService iUserService;

    public AutorizacaoService() {
        Retrofit retrofit = RetrofitService.getClient();
        iUserService = retrofit.create(IUserService.class);
    }

    public void login(String clientId, String clientSecret, String username, String password, String grantType) {
        String credentials = clientId + ":" + clientSecret;
        String authHeader = "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8));
        
        LoginRequest loginRequest = new LoginRequest(username, password, grantType);
        
        System.out.println("Authorization Header: " + authHeader);
        System.out.println("Login Request: " + new Gson().toJson(loginRequest));
        
        Call<LoginResponse> call = iUserService.login(authHeader, "application/json", username, password, grantType);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    LoginResponse loginResponse = response.body();
                    if (loginResponse != null) {
                        String token = loginResponse.getToken();
                        System.out.println("Token: " + token);
                    } else {
                        System.out.println("Login response is null");
                    }
                } else {
                    System.out.println("Login failed: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                System.out.println("Login request failed: " + t.getMessage());
            }
        });
    }
}
