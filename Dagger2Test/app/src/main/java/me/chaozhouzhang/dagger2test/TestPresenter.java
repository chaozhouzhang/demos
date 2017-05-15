package me.chaozhouzhang.dagger2test;

import javax.inject.Inject;

/**
 * Created by zhangchaozhou on 17/5/15 16:06
 * chaozhouzhang@qq.com
 */

public class TestPresenter  {

    private TestContract.View mView;


    @Inject
    public TestPresenter(TestContract.View view) {
        mView = view;
    }


    public void loadData(){
        mView.updateUI();
    }
}
