package cn.kais.immer.demo.xpopup.demo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Description:
 * Create by dance, at 2018/12/9
 */
public abstract class BaseFragment extends Fragment {

    View view;
    boolean isInit = false;
//    StateLayout stateLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        if (view == null) {
//            view = inflater.inflate(getLayoutId(), container, false);
//            stateLayout = new StateLayout(getContext()).wrap(view).showLoading();
//        }
//        return stateLayout;

        if (view == null) {
            view = inflater.inflate(getLayoutId(), container, false);
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        safeInit();
    }

    private void safeInit() {
        if (getUserVisibleHint() && view != null) {
            if (!isInit) {
                isInit = true;
                init(view);
//                stateLayout.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        stateLayout.showContent();
//                    }
//                }, 300);
            }
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        safeInit();
    }

    protected abstract int getLayoutId();

    public abstract void init(View view);

    public void toast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 手机home 键 回到手机主页
     */
    public void toHome() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


}
