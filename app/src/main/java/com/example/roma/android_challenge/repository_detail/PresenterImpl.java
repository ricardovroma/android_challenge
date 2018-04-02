package com.example.roma.android_challenge.repository_detail;


public class PresenterImpl implements RepositoryDetailContract.Presenter {

    private final RepositoryDetailContract.View view;

    public PresenterImpl(RepositoryDetailContract.View view) {
        this.view = view;
    }
}
