package me.chaozhouzhang.dagger2test;

import dagger.Component;

/**
 * Created by zhangchaozhou on 17/5/15 16:09
 * chaozhouzhang@qq.com
 */




@Component(modules = TestModule.class)
public interface TestComponent {
    void inject(MainActivity mainActivity);
}
