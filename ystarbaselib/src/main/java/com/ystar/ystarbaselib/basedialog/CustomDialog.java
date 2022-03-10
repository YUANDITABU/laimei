package com.ystar.ystarbaselib.basedialog;


import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import androidx.annotation.Nullable;


import com.ystar.ystarbaselib.R;
import com.ystar.ystarbaselib.databinding.DialogCustom1Binding;


/**
 * 参考dialog
 * author : Ystar
 * time   : 2021-01-28
 */
public class CustomDialog extends BaseRXDialogFrament<DialogCustom1Binding> {

    @Override
    public DialogCustom1Binding getViewBinding() {
        return DialogCustom1Binding.inflate(getLayoutInflater());
    }

    public static CustomDialog getInstance() {
        //具体点进去看文档配置
        CustomDialog userinfoUtilsDilog = new CustomDialog();
        userinfoUtilsDilog.setDimEnabled(false);//背景是否昏暗
        userinfoUtilsDilog.setCancelable(true);
        userinfoUtilsDilog.setCanceledOnTouchOutside(true);
         userinfoUtilsDilog.setAnimations(R.style.base_dialogWindowAnim);
        userinfoUtilsDilog.setGravity(Gravity.BOTTOM);
        userinfoUtilsDilog.setPadding(0, 0, 0, 50);
        return userinfoUtilsDilog;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

    }


}
