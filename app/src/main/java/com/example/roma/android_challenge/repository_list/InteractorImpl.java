package com.example.roma.android_challenge.repository_list;

import com.example.roma.android_challenge.core.api.RepositoryAPI;
import com.example.roma.android_challenge.repository_list.models.RepositoryEnvelopModelRest;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class InteractorImpl implements RepositoryContract.Interactor {

    private static RepositoryEnvelopModelRest repositoryEnvelopModelRest;
    private final RepositoryContract.Presenter presenter;
    private final RepositoryAPI api;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public InteractorImpl(RepositoryContract.Presenter presenter) {
        this.presenter = presenter;
        api = new RepositoryAPI();
    }

    @Override
    public void load(int page) {


        mCompositeDisposable.add(
                api.service.repository("language:Java", "stars", page)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<RepositoryEnvelopModelRest>() {
                            @Override
                            public void accept(RepositoryEnvelopModelRest repositoryEnvelopModelRest) {
                                InteractorImpl.repositoryEnvelopModelRest = repositoryEnvelopModelRest;
                                presenter.build(repositoryEnvelopModelRest.items);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {

                            }
                        })
        );
    }

    @Override
    public void morePage(int page) {
        mCompositeDisposable.add(
                api.service.repository("language:Java", "stars", page)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<RepositoryEnvelopModelRest>() {
                            @Override
                            public void accept(RepositoryEnvelopModelRest repositoryEnvelopModelRest) {
                                InteractorImpl.repositoryEnvelopModelRest = repositoryEnvelopModelRest;
                                presenter.morePageBuild(repositoryEnvelopModelRest.items);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {

                            }
                        })
        );
    }

    @Override
    public void shouldLoadMoreItems() {

    }

    @Override
    public void onDestroy() {
        mCompositeDisposable.clear();
    }
}
