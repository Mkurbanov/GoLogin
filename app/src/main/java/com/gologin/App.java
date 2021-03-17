package com.gologin;

import android.app.Application;

import com.gologin.data.server.Requests;
import com.gologin.utils.Functions;

public class App extends Application {
    private Requests requests;
    private Functions functions;
    private static App app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }

    public static App getInstance() {
        return app;
    }

    public Requests getRequests() {
        if (requests == null)
            requests = new Requests();
        return requests;
    }

    public Functions getFunctions() {
        if (functions == null)
            functions = new Functions();
        return functions;
    }

    public static String getToken() {
        return Functions.loadText(app, config.app.TOKEN);
    }
}
