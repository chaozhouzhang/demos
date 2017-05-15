package me.chaozhouzhang.dagger2test;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zhangchaozhou on 17/5/15 16:08
 * chaozhouzhang@qq.com
 */

@Module
public class TestModule {
    private  TestContract.View mView;


    public TestModule(TestContract.View view) {
        mView = view;
    }



    @Provides
    TestContract.View provideTestView() {

        return mView;
    }
}
