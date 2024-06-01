package br.com.borsoitech.pdv.layout.login;

import br.com.borsoitech.pdv.model.type.LoginResponse;

public class SessionManager {

    private static SessionManager instance;
    private static LoginResponse loginResponse;

    private SessionManager() { }

    public static synchronized SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public LoginResponse getLoginResponse() {
        return loginResponse;
    }

    public void setLoginResponse(LoginResponse loginResponse) {
        SessionManager.loginResponse = loginResponse;
    }
}
