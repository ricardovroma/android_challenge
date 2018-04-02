package com.example.roma.android_challenge.repository_list;

import android.support.annotation.StringRes;

import com.example.roma.android_challenge.repository_list.models.RepositoryItemModelRest;

import java.util.ArrayList;

public interface RepositoryContract {

    interface View {
        void showLoading();
        void hideLoading();
        void onError(String message);
        void build(ArrayList<RepositoryItemModelRest> items);
        void morePageBuild(ArrayList<RepositoryItemModelRest> items);

    }

    interface Presenter {
        void load();
        void shouldLoadMoreItems();

        void build(ArrayList<RepositoryItemModelRest> items);

        void morePage(int page);
        void morePageBuild(ArrayList<RepositoryItemModelRest> items);
    }

    interface Interactor {
        void load(int page);
        void morePage(int page);
        void shouldLoadMoreItems();
        void onDestroy();
    }
}