package com.howu.lazyloadfragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class FourthFragment extends BaseLazyfragment {
    private static final String TAG = "FourthFragment";
    private ImageView imageView;



    @Override
    protected int getLayoutId() {
        return R.layout.fragment_fourth;
    }

    @Override
    protected void initWidget(View viewRoot) {
        super.initWidget(viewRoot);
        imageView = viewRoot.findViewById(R.id.img_fourth);

    }

    @Override
    protected void lazyLoad() {
        showProgressDialog("请稍候。。。。");
        //模拟请求数据
        Observable.timer(500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.d(TAG, "accept: "+Thread.currentThread().equals(Looper.getMainLooper().getThread()));
                        Glide.with(getActivity()).load(R.drawable.fourth).asBitmap().into(imageView);
                        hideProgressDialog();
                    }
                });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d("TAG", "onAttach: ======"+TAG);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TAG", "onCreate: ------"+TAG);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("TAG", "onResume: ====="+TAG);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated: -------"+TAG);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("TAG", "onDetach: -------"+TAG);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("TAG", "onCreateView: -0=-=-"+TAG);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("TAG", "onDestroyView: ---------"+TAG);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("TAG", "onPause: ----------"+TAG);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("TAG", "onStart:00000 "+TAG);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("TAG", "onStop: ------"+TAG);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("TAG", "onDestroy: -=-=-=-"+TAG);
    }
}
