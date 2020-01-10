package com.howu.lazyloadfragment;


import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class FirstFragment extends BaseLazyfragment {
    private static final String TAG = "FirstFragment";
    private ImageView imageView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_first;
    }

    @Override
    protected void initWidget(View viewRoot) {
        super.initWidget(viewRoot);
        imageView = viewRoot.findViewById(R.id.img_first);
    }

    @Override
    protected void lazyLoad() {
        //模拟请求数据
        showProgressDialog("请稍候");
        Observable.timer(500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Glide.with(getActivity()).load(R.drawable.first).asBitmap().into(imageView);
                        hideProgressDialog();
                    }
                });
    }


}
