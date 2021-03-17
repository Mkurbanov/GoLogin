package com.gologin;

/**
 * Created by Mukam Kurbanov on 17.03.2021.
 */
public interface config {
    String APP_NAME = "GoLogin";
    String LOG_TAG = "gologin";
    String PREFERENCES = "gologin";
    String BASE_URL = "https://api.gologin.app/";


    interface app {
        String TOKEN = "token";
        String PHONE = "phone";
        String BOTTOM_BAR_SELECTED_ITEM_ID = "BOTTOM_BAR_SELECTED_ITEM_ID";
        String LANGUAGE = "language";
        String FULLSCREEN = "fullscreen";
        String FB_SUBC = "fb_subc";

        interface value {
            String FIT = "fit";
            String FILL = "fill";
            String TRUE = "true";
            String FALSE = "false";
        }
    }

    interface server {
        String STATUS = "status";
        String MESSAGE = "message";
        String TOKEN = "token";
        String SESSION = "session";
        String DATA = "data";

        interface value {
            String STATUS_OK = "ok";
            String STATUS_ERROR = "error";
        }

    }
}
