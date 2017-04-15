package com.cakiecakie.lightintnet;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by naehokushin on 17/4/14.
 */

public class ActivityCollector {
    public static List<Activity> activityList = new ArrayList<>();

    public static void add(Activity activity) {
        activityList.add(activity);
    }

    public static void remove(Activity activity) {

        activityList.remove(activity);
        activity.finish();
    }

    public static void removeAll() {
        for (Activity activity : activityList) {
            activity.finish();
        }
    }
}
