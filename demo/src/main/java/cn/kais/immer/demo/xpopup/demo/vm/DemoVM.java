package cn.kais.immer.demo.xpopup.demo.vm;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DemoVM extends ViewModel {

    public MutableLiveData<String> liveData = new MutableLiveData<>();

    @Override
    protected void onCleared() {
        super.onCleared();
    }

}