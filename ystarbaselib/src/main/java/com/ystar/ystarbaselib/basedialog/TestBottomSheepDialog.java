package com.ystar.ystarbaselib.basedialog;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.ystar.ystarbaselib.databinding.DialogTestdialogBinding;
import com.ystar.ystarbaselib.utils.ClickUtils;
import com.ystar.ystarbaselib.utils.MyToastUtil;

/**
 * @author ystar  BaseRxBottomSheepDialog 实例
 * 创建日期：2021/5/6
 * 描述：
 */
public class TestBottomSheepDialog extends BaseRxBottomSheepDialog<DialogTestdialogBinding> {



    @Override
    public DialogTestdialogBinding getViewBinding() {
        return DialogTestdialogBinding.inflate(getLayoutInflater());
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new BottomSheepBuilder()
                .setHeight(1000)
                .setDimEnabled(false)
                .build();
        ClickUtils.setClick(binding.test1,()->{
            MyToastUtil.showCenter("TRST11111");
            dismiss();
        });
    }

}
