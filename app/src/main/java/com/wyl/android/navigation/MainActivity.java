package com.wyl.android.navigation;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.navigation.ui.NavigationViewKt;

import com.google.android.material.navigation.NavigationView;
import com.wyl.android.R;
import com.wyl.android.lifecycle.BaseActivity;
/**
 * 项目名称：android-learn
 * 创建人：江心才子
 * 创建时间：2019-9-23 17:54:15
 * 内容描述：
 * 修改说明：
 */
public class MainActivity extends BaseActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final NavController navController = Navigation.findNavController(this, R.id.fragment);
        NavigationUI.setupActionBarWithNavController(this, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
//        return super.onSupportNavigateUp();
        final NavController navController = Navigation.findNavController(this, R.id.fragment);
        return navController.navigateUp();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
