package jingou.jo.com.domess;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import jingou.jo.com.domess.bean.NewsBean;
import jingou.jo.com.domess.utils.RetrofitUtil;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Button button,button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button= (Button) findViewById(R.id.but1);
        button1= (Button) findViewById(R.id.but2);
//        有用rxjava
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RetrofitUtil.getInstance().getTestService()
                        .getNewsWithRxJava("8bf17cf1c321723f060d5dc5c4da871a", "top")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<NewsBean>() {
                            private Disposable mDisposable;
                            @Override
                            public void onSubscribe(Disposable d) {
                              mDisposable=d;
                            }

                            @Override
                            public void onNext(NewsBean value) {
                                Log.e("使用Rxjava",value.toString());
//                                使用完后注销
                                mDisposable.dispose();
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        });

//        没有用rxjava的
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<ResponseBody> call=RetrofitUtil.getInstance().getTestService()
                        .getNewsWithoutRxJava("8bf17cf1c321723f060d5dc5c4da871a", "top");
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            String string= response.body().string().toString();
                            Log.e("假装有分割线","_______________________________________________________________________________________________________");

                            Log.e("没有用RXjava的",string);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
            }
        });
    }
}
