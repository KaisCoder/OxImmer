package cn.kais.immer.xpopup.interfaces;

public interface OnDragChangeListener {
    void onRelease();

    void onDragChange(int dy, float scale, float fraction);
}
