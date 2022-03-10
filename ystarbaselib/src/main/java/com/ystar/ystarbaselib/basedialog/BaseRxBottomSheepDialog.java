package com.ystar.ystarbaselib.basedialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBinding;

import com.blankj.utilcode.util.ScreenUtils;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.ystar.ystarbaselib.R;

import static com.blankj.utilcode.util.SnackbarUtils.getView;


/**
 * @author ystar
 * 创建日期：2021/4/4
 * 描述：BottomSheetDialogFragment 底部弹出
 */
public abstract class BaseRxBottomSheepDialog<VB extends ViewBinding> extends BottomSheetDialogFragment {
    protected VB binding;

    int defuletHeight = (ScreenUtils.getScreenHeight() / 3) * 2; //默认高度
    BottomSheepBuilder mBottomSheepBuilder;
    protected BottomSheetBehavior mBottomSheetBehavior;
    public BaseRxBottomSheepDialog() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = getViewBinding();
         return binding.getRoot();
    }





    public abstract VB getViewBinding();



    @Override
    public void onStart() {
        super.onStart();
        View view=  getView();
        if (getDialog() != null) {
            View bottomSheet = getDialog().findViewById(R.id.design_bottom_sheet);
            if (mBottomSheepBuilder == null) {
                bottomSheet.getLayoutParams().height =  ViewGroup.LayoutParams.WRAP_CONTENT;
            } else {
                if (mBottomSheepBuilder.isFullSreen) {
                    bottomSheet.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
                } else {
                    bottomSheet.getLayoutParams().height = mBottomSheepBuilder.height;
                }
            }
        }

        view.post(new Runnable() {
            @Override
            public void run() {
                View parent = (View) view.getParent();
                CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) (parent).getLayoutParams();
                CoordinatorLayout.Behavior behavior = params.getBehavior();
                mBottomSheetBehavior = (BottomSheetBehavior) behavior;
                if (mBottomSheepBuilder!=null&&!mBottomSheepBuilder.isDrag)
               mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                   @Override
                   public void onStateChanged(@NonNull View view, int i) {
                       //禁止拖拽，
                       if (i == BottomSheetBehavior.STATE_DRAGGING) {
                           //设置为收缩状态
                           mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                       }

                   }

                   @Override
                   public void onSlide(@NonNull View view, float v) {

                   }
               });
                if (mBottomSheepBuilder == null)
                    mBottomSheetBehavior.setPeekHeight(defuletHeight);
                else {
                    if (mBottomSheepBuilder.isFullSreen) {
                        mBottomSheetBehavior.setPeekHeight(ScreenUtils.getScreenHeight());
                    } else
                        mBottomSheetBehavior.setPeekHeight(mBottomSheepBuilder.height);
                }


                parent.setBackgroundResource(R.color.base_transparent);
            }
        });


    }

    /**
     * 窗口背景色
     */
    private void set(BottomSheepBuilder bottomSheepBuilder) {

        WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
        //   params.windowAnimations = R.style.ad_dialog_anim;
        getDialog().getWindow().setAttributes(params);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_PANEL);
        getDialog().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        //背景灰暗
        if (bottomSheepBuilder.isDimEnabled) {
            getDialog().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        } else {
            getDialog().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        }
        getDialog().getWindow().setBackgroundDrawableResource(R.color.base_transparent);
        getDialog().setCanceledOnTouchOutside(bottomSheepBuilder.isCanceledOnTouchOutside);

    }




    public class BottomSheepBuilder {
        boolean isDimEnabled = true;
        int height = defuletHeight;
        boolean isCanceledOnTouchOutside = true;
        boolean isFullSreen = false;
        boolean isDrag=true; //是否可以拖动
        public BottomSheepBuilder setIsDrag(boolean isDrag) {
            this.isDrag = isDrag;
            return this;
        }
        public BottomSheepBuilder setFullSreen(boolean isFullSreen) {
            this.isFullSreen = isFullSreen;
            return this;
        }


        public BottomSheepBuilder setDimEnabled(boolean dimEnabled) {
            isDimEnabled = dimEnabled;
            return this;
        }

        public BottomSheepBuilder setHeight(int height) {
            this.height = height;
            return this;
        }

        public BottomSheepBuilder setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
            isCanceledOnTouchOutside = canceledOnTouchOutside;
            return this;
        }


        public void build() {
            mBottomSheepBuilder = this;
            set(this);
        }

    }


}
