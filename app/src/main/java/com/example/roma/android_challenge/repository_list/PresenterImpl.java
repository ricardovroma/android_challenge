package com.example.roma.android_challenge.repository_list;

import com.example.roma.android_challenge.repository_list.models.RepositoryItemModelRest;

import java.util.ArrayList;

public class PresenterImpl implements RepositoryContract.Presenter {

    private final RepositoryContract.View view;
    private final InteractorImpl interactor;

    public PresenterImpl(RepositoryContract.View view) {
        this.view = view;
        interactor = new InteractorImpl(PresenterImpl.this);
    }

    @Override
    public void load() {
        view.showLoading();
        interactor.load(1);
    }

    @Override
    public void shouldLoadMoreItems() {
        interactor.shouldLoadMoreItems();
    }

    @Override
    public void build(ArrayList<RepositoryItemModelRest> items) {
        view.hideLoading();
        view.build(items);
    }

    @Override
    public void morePageBuild(ArrayList<RepositoryItemModelRest> items) {
        view.hideLoading();
        view.morePageBuild(items);
    }

    @Override
    public void morePage(int page) {
        view.showLoading();
        interactor.morePage(page);
    }
}
