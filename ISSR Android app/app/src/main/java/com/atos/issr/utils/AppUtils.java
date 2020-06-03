package com.atos.issr.utils;

import android.content.Context;
import android.content.res.Resources;

import com.atos.issr.BuildConfig;
import com.atos.issr.R;
import com.microblink.MicroblinkSDK;
import com.microblink.intent.IntentDataTransferMode;

/**
 * Created by Jarci on 7. 8. 2018.
 */
public class AppUtils {
    private static boolean licenceApplied = false;
    //FIXME this license id valid till 02.07.2020
    private static final String LICENCE_KEY = "sRwAAAANY29tLmF0b3MuaXNzcv9YI5elhrFv4599ApYVrLKMHE2zfXUaiOG/Fg3Pgg7CmwBWGFaHd4W4c255HzBnGn6bySl/wYcrPLO6cm/6OFOcM//JW1TnIwxlV62q+WB3+012lS9jIPium2q8dAZ1Y9MIXiZ05aCTAw5mwnEWOkM6yoXlfp6qss+Sl1if+1pDj01cvVTHAuMc/Onk0F3jGggjftV/cS0v8A7UM5wwKtLDH2y/irDWkHeIA/l8vhURzDXLsA9YLYFHeioJBk5ltMuVBok3K9Ji0em6ZJXmxg==";

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

    public static void initMicroblink(Context context) {
        if (!licenceApplied) {
            MicroblinkSDK.setLicenseKey(LICENCE_KEY, context);
            MicroblinkSDK.setIntentDataTransferMode(IntentDataTransferMode.OPTIMISED);
            licenceApplied = true;
        }
    }
}
