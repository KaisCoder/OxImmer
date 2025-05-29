package cn.kais.immer.demo;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import cn.kais.immer.java.UltimateBarX;

/**
 * @Author : Zackratos
 * @Date : 2020/7/11 4:10
 * @email : 869649338@qq.com
 * @Describe :
 */
public class DrawerFragment extends Fragment {

    DrawerFragment() {
        super(R.layout.fragment_drawer);
    }

    public static DrawerFragment newInstance() {
        return new DrawerFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        UltimateBarX.statusBarOnly(this)
                .transparent()
                .apply();
    }

}
