package jingou.jo.com.domess.service;

import io.reactivex.Observable;
import jingou.jo.com.domess.bean.NewsBean;
import jingou.jo.com.domess.constance.AppConstance;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/9/12.
 */

public interface TestService {
//    使用rxjava
    @POST(AppConstance.NEWS_URL)
    Observable<NewsBean> getNewsWithRxJava(@Query("key") String kry,@Query("type") String type);
//        不使用rxjava
    @POST(AppConstance.NEWS_URL)
    Call<ResponseBody> getNewsWithoutRxJava(@Query("key") String key,@Query("type") String type);

}
