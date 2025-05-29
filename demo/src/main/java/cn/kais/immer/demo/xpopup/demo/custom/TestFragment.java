package cn.kais.immer.demo.xpopup.demo.custom;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import cn.kais.immer.demo.R;
import cn.kais.immer.demo.xpopup.demo.fragment.BaseFragment;

public class TestFragment extends BaseFragment {

    public static TestFragment create(String text) {
        TestFragment testFragment = new TestFragment();
        Bundle bundle = new Bundle();
        bundle.putString("text", text);
        testFragment.setArguments(bundle);
        return testFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_test;
    }

    @Override
    public void init(View view) {
        String text = getArguments().getString("text", "XPopup");
        view.<TextView>findViewById(R.id.tv).setText(text);
    }

}
