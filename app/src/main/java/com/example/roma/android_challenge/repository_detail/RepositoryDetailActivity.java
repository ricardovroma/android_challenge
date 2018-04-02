package com.example.roma.android_challenge.repository_detail;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.roma.android_challenge.R;
import com.example.roma.android_challenge.core.ScreenManager;
import com.example.roma.android_challenge.core.api.utils.BaseActivity;
import com.example.roma.android_challenge.core.api.utils.CircleTransform;
import com.example.roma.android_challenge.repository_list.models.RepositoryItemModelRest;
import com.squareup.picasso.Picasso;

public class RepositoryDetailActivity extends BaseActivity implements RepositoryDetailContract.View {

    public static final String REPOSITORY = "REPOSITORY";
    private PresenterImpl presenter;
    private ImageView ivAvatar;
    private TextView tvFullName;
    private TextView tvDescription;
    private RepositoryItemModelRest repositoryItemModelRest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repository_detail);

        repositoryItemModelRest = (RepositoryItemModelRest) getIntent().getSerializableExtra(REPOSITORY);

        ivAvatar = findViewById(R.id.iv_avatar);
        tvFullName = findViewById(R.id.tv_full_name);
        tvDescription = findViewById(R.id.tv_description);

        build();
    }

    private void build() {
        Picasso.with(this).load(repositoryItemModelRest.owner.avatarUrl).transform(new CircleTransform()).into(ivAvatar);
        tvFullName.setText(repositoryItemModelRest.fullName);
        tvDescription.setText(repositoryItemModelRest.description);

        tvFullName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ScreenManager.goToRepositoryHtmlUrl(RepositoryDetailActivity.this, repositoryItemModelRest.htmlUrl);
            }
        });
    }
}
