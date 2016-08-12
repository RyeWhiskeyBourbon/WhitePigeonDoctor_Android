package com.mydemo.zhongyujiaoyu.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.mydemo.zhongyujiaoyu.R;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

/**
 * Created by Chunlei.Li on 15/12/31.
 */
public abstract class BaseFragmentActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout container = new FrameLayout(this);
        container.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT));
        container.setId(R.id.content);
        setContentView(container);

        if (getFragment() == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.content, createFragment(), "fragment");
            transaction.commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.onBackPressed();
            return true;
        }
        return false;
    }

    protected Fragment getFragment() {
        return getSupportFragmentManager().findFragmentByTag("fragment");
    }

    protected abstract Fragment createFragment();
}
