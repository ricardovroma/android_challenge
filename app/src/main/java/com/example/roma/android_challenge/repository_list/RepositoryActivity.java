package com.example.roma.android_challenge.repository_list;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.roma.android_challenge.R;
import com.example.roma.android_challenge.core.ScreenManager;
import com.example.roma.android_challenge.core.api.utils.EndlessRecyclerViewScrollListener;
import com.example.roma.android_challenge.repository_list.models.RepositoryItemModelRest;
import com.example.roma.android_challenge.core.api.utils.BaseActivity;

import java.util.ArrayList;

public class RepositoryActivity extends BaseActivity implements RepositoryContract.View {

    private PresenterImpl presenter;
    private RecyclerView rvRepository;
    private View defaultLoader;
    private EndlessRecyclerViewScrollListener scrollListener;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSwipeRefreshLayout = findViewById(R.id.srl_repository);
        rvRepository = findViewById(R.id.rv_repository);
        defaultLoader = findViewById(R.id.default_loader);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.load();
            }
        });

        rvRepository.setLayoutManager(new LinearLayoutManager(this));
        RepositoryAdapter mAdapter = new RepositoryAdapter(null, this, new RepositoryAdapter.Listener() {
            @Override
            public void onClickListener(RepositoryItemModelRest repositoryItemModelRest, View ivAvatar) {
                ScreenManager.gotoRepositoryDetail(RepositoryActivity.this, repositoryItemModelRest, ivAvatar);
            }
        });
        rvRepository.setAdapter(mAdapter);
        scrollListener = new EndlessRecyclerViewScrollListener((LinearLayoutManager) rvRepository.getLayoutManager()) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                presenter.morePage(++page);
            }
        };
        rvRepository.addOnScrollListener(scrollListener);

        presenter = new PresenterImpl(RepositoryActivity.this);
        presenter.load();
    }

    @Override
    public void showLoading() {
        defaultLoader.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        defaultLoader.setVisibility(View.GONE);
    }

    @Override
    public void onError(String message) {
        Snackbar snackbar = Snackbar.make(rvRepository, message, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("Tentar novamente", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.load();
            }
        });
    }

    public void build(ArrayList<RepositoryItemModelRest> items) {
        ((RepositoryAdapter) rvRepository.getAdapter()).setItems(items);
        ((RepositoryAdapter) rvRepository.getAdapter()).notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    public void morePageBuild(ArrayList<RepositoryItemModelRest> items) {
        ArrayList<RepositoryItemModelRest> mItems = ((RepositoryAdapter) rvRepository.getAdapter()).getItems();
        for (RepositoryItemModelRest item : items) {
            mItems.add(item);
        }
        ((RepositoryAdapter) rvRepository.getAdapter()).setItems(mItems);
        ((RepositoryAdapter) rvRepository.getAdapter()).notifyDataSetChanged();
    }
}
