package me.chaozhouzhang.dagger2test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements TestContract.View {


    @Inject
    TestPresenter mTestPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerTestComponent.builder()
                .testModule(new TestModule(this))
                .build()
                .inject(this);
        mTestPresenter.loadData();
    }


    @Override
    public void updateUI() {
        Log.e(MainActivity.class.getSimpleName(), "Update UI");
    }
}
