package cn.kais.immer.demo.xpopup.demo;

import cn.kais.immer.demo.xpopup.demo.fragment.BaseFragment;

/**
 * Description:
 * Create by dance, at 2018/12/9
 */
public class PageInfo {

    public String title;
    public BaseFragment fragment;

    public PageInfo(String title, BaseFragment fragment) {
        this.title = title;
        this.fragment = fragment;
    }

}
