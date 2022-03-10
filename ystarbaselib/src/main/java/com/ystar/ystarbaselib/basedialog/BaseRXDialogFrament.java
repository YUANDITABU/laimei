package com.ystar.ystarbaselib.basedialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import com.mylhyl.circledialog.AbsBaseCircleDialog;


/**
 *
 * 基类dialog
 * author : Ystar
 * time   : 2021-01-28
 */
public abstract class BaseRXDialogFrament<VB extends ViewBinding> extends AbsBaseCircleDialog {
    protected VB binding;
   protected static AppCompatActivity mActivity;
    public BaseRXDialogFrament() {

    }



    @Override
    public View createView(Context context, LayoutInflater inflater, ViewGroup container) {
        binding = getViewBinding();
        return binding.getRoot();
    }



    public abstract VB getViewBinding();





}
