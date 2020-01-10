package com.howu.lazyloadfragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public abstract class BaseLazyfragment extends Fragment {
    private static final String TAG = "BaseLazyfragment";

    private View mRoot;
    //是否第一次加载
    private boolean isFirstLoad = true;
    //标志位，View已经初始化完成
    private boolean isPrepared;

    private Context mContext;

    private ProgressDialog mPragressDialog;//进度加载

    @Override
    public void onAttach(@NonNull Context context) {
        Log.d(TAG, "onAttach: ");
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext =getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        //如果mRoot不为空，则不需要重新创建，保持Fragment已有的状态
       if (mRoot !=null){
           return mRoot;
       }
        mRoot =inflater.inflate(getLayoutId(),container,false);
        initWidget(mRoot);
        isPrepared = true;
        isFirstLoad = true;
        return mRoot;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onActivityCreated: ");
        super.onActivityCreated(savedInstanceState);
    }


    protected abstract int getLayoutId();
    protected void initWidget(View viewRoot){}

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        Log.d(TAG, "setUserVisibleHint: ");
        super.setUserVisibleHint(isVisibleToUser);
//        if (isVisibleToUser&&isFirstLoad){
//            lazyLoad();
//            isFirstLoad = false;
//        }
    }

    @Override
    public void onStart() {
        Log.d(TAG, "onStart: ");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume: ");
        super.onResume();
        if (isFirstLoad&&isPrepared){
            isFirstLoad = false;
            lazyLoad();
        }
    }

    @Override
    public void onStop() {
        Log.d(TAG, "onStop: ");
        super.onStop();
    }

    @Override
    public void onPause() {
        Log.d(TAG, "onPause: ");
        super.onPause();
    }

    @Override
    public void onDetach() {
        Log.d(TAG, "onDetach: ");
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }

    protected abstract void lazyLoad();
    public boolean isPrepared(){
        return isPrepared;
    }
    public boolean isFirstLoad(){
        return isFirstLoad;
    }
    public void setIsFirstLoad(boolean isFirstLoad){
        this.isFirstLoad = isFirstLoad;
    }


    protected void showProgressDialog(String msg){
        if(mPragressDialog == null){
            mPragressDialog = new ProgressDialog(mContext);
        }
        if (mPragressDialog.isShowing()){
            mPragressDialog.dismiss();
        }
        mPragressDialog.setMessage(msg);
        mPragressDialog.show();
    }

    protected void hideProgressDialog(){
        if (mPragressDialog!=null){
            mPragressDialog.dismiss();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isFirstLoad = false;
        isPrepared = false;
    }
}
