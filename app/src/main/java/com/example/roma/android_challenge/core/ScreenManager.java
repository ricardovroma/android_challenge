package com.example.roma.android_challenge.core;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Browser;

import com.example.roma.android_challenge.repository_detail.RepositoryDetailActivity;
import com.example.roma.android_challenge.repository_list.models.RepositoryItemModelRest;

import java.util.ArrayList;

public class ScreenManager {
    public static void gotoRepositoryDetail(Context ctx, RepositoryItemModelRest item) {
        Intent it = new Intent(ctx, RepositoryDetailActivity.class);
        it.putExtra(RepositoryDetailActivity.REPOSITORY, item);
        ctx.startActivity(it);
    }

    public static void goToRepositoryHtmlUrl(Context context, String url) {
        Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        Bundle bundle = new Bundle();
        context.startActivity(it);
    }
}
