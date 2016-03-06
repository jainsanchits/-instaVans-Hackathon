package instavans.sanchit.instavans.utilties;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by sanchitjain on 05/03/16.
 */
public class InstavanPrefs {
    private static final String PREFERENCES_NAME = "instaprefs";
    private static InstavanPrefs instaPref;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private String IS_LOGGED_IN = "is_logged_in";
    private String PORTERID ="porter_id";
    private String REMEBERTOKEN = "remember_token";
    private String USER_ID = "userId";
    private String NAME = "name";
    private String CONTACT = "mobile";


    private InstavanPrefs(Context mContext) {
        preferences = mContext.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
        editor.apply();
    }

    public static synchronized InstavanPrefs getInstance(Context context) {
        if (instaPref == null) {
            instaPref = new InstavanPrefs(context);
        }
        return instaPref;
    }

    public boolean getIS_LOGGED_IN() {
        return preferences.getBoolean(IS_LOGGED_IN, true);
    }

    public void setIS_LOGGED_IN(Boolean isloggedin ) {
        editor.putBoolean(IS_LOGGED_IN, isloggedin);
        editor.commit();
    }

    public String getPORTERID() {
        return preferences.getString(PORTERID, "");
    }

    public void setPORTERID(String porterid) {
        editor.putString(PORTERID, porterid);
        editor.commit();
    }

    public String getREMEBERTOKEN() {
        return preferences.getString(REMEBERTOKEN, "");
    }

    public void setREMEBERTOKEN(String remeber_token) {
        editor.putString(REMEBERTOKEN, remeber_token);
        editor.commit();
    }

    public String getUSER_ID() {
        return preferences.getString(USER_ID, "");
    }

    public void setUSER_ID(String userId) {
        editor.putString(USER_ID, userId);
        editor.commit();
    }

    public String getNAME() {
        return preferences.getString(NAME, "");
    }

    public void setNAME(String name) {
        editor.putString(NAME, name);
        editor.commit();
    }

    public String getCONTACT() {
        return preferences.getString(CONTACT, "");
    }

    public void setCONTACT(String contact) {
        editor.putString(CONTACT, contact);
        editor.commit();
    }
}
