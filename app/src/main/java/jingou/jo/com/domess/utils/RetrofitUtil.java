package jingou.jo.com.domess.utils;

import jingou.jo.com.domess.constance.AppConstance;
import jingou.jo.com.domess.service.TestService;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/9/12.
 */

public class RetrofitUtil {
//    这里就应该是 实现Retrofit的操作吧
private volatile static RetrofitUtil sInstance;
private Retrofit retrofit;
    private final TestService service;

        private RetrofitUtil(){
      retrofit=new Retrofit.Builder()
                .baseUrl(AppConstance.APP_URL)
                .addConverterFactory(GsonConverterFactory.create())//设置支持Gson
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//设置支持Rxjava
                .build();
        service = retrofit.create(TestService.class);
    }
    public static RetrofitUtil getInstance(){
        if (sInstance==null){
            synchronized (RetrofitUtil.class){
                if (sInstance==null){
                    sInstance=new RetrofitUtil();
                }
            }
        }
        return  sInstance;
    }
    public  TestService getTestService(){
        return service;
    }


}
