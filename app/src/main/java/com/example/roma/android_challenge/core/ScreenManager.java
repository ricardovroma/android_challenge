package com.example.roma.android_challenge.core;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Browser;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;

import com.example.roma.android_challenge.repository_detail.RepositoryDetailActivity;
import com.example.roma.android_challenge.repository_list.models.RepositoryItemModelRest;

import java.util.ArrayList;

public class ScreenManager {
    public static void gotoRepositoryDetail(Activity activity, RepositoryItemModelRest item, View view) {
        Intent it = new Intent(activity, RepositoryDetailActivity.class);
        it.putExtra(RepositoryDetailActivity.REPOSITORY, item);

        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(activity, view, "avatar");
        activity.startActivity(it, options.toBundle());
    }

    public static void goToRepositoryHtmlUrl(Context context, String url) {
        Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        Bundle bundle = new Bundle();
        context.startActivity(it);
    }
}
