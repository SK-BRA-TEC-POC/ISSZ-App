package com.atos.upsvar.utils;

import android.content.res.Resources;

import com.atos.upsvar.BuildConfig;
import com.atos.upsvar.R;

/**
 * Created by Jarci on 7. 8. 2018.
 */
public class AppUtils {

    public static String getAppVersionName(Resources resources) {
        String versionName;

        switch (BuildConfig.APP_BUILD_TYPE) {
            case "DEBUG":
                versionName = resources.getString(R.string.app_name) + ' ' + BuildConfig.VERSION_NAME + " - " + BuildConfig.APP_BUILD_TYPE;
                break;
            default:
                versionName = resources.getString(R.string.app_name) + ' ' + BuildConfig.VERSION_NAME;
        }
        return versionName;
    }
}
