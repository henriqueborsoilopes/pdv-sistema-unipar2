package br.com.borsoitech.pdv.layout.login;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import br.com.borsoitech.pdv.model.retrofit.RetrofitService;
import br.com.borsoitech.pdv.model.type.LoginResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginService {

    private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";

    private final IUserService userService;

    public LoginService() {
        Retrofit retrofit = RetrofitService.getClient();
        this.userService = retrofit.create(IUserService.class);
    }

    public void login(String clientId, String clientSecret, String username, String password, String grantType, final ILogin autorizacaoCallback) {
        String authHeader = createAuthHeader(clientId, clientSecret);

        Call<LoginResponse> call = userService.getAccessToken(grantType, username, password, authHeader);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    SessionManager.getInstance().setLoginResponse(response.body());
                    autorizacaoCallback.isAutorizado(true);
                } else {
                    SessionManager.getInstance().setLoginResponse(null);
                    autorizacaoCallback.isAutorizado(false);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                SessionManager.getInstance().setLoginResponse(null);
                autorizacaoCallback.isAutorizado(false);
            }
        });
    }

    private String createAuthHeader(String clientId, String clientSecret) {
        String credentials = clientId + ":" + clientSecret;
        return AUTHORIZATION_HEADER_PREFIX + Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8));
    }
}