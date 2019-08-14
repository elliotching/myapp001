package hexa.app001;

import android.database.Cursor;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class NetworkHelper {
    
    private static final String BASE_URL = "http://www.omdbapi.com";
    //    private DisposableObserver<SearchResponse> mObserver;
    private OkHttpClient mOkHttpClient;
    private Retrofit mRetrofit;
    
//    public NetworkHelper(DisposableObserver<SearchResponse> mObservable) {
//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
//        mOkHttpClient = new OkHttpClient.Builder()
//            .addInterceptor(logging)
//            .build();
//        mRetrofit = new Retrofit.Builder()
//            .baseUrl("http://www.omdbapi.com")
//            .addConverterFactory(JacksonConverterFactory.create())
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .client(mOkHttpClient)
//            .build();
//        this.mObserver = mObservable;
//    }
    public NetworkHelper() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.level(HttpLoggingInterceptor.Level.BODY);
        mOkHttpClient = new OkHttpClient.Builder()
            .addInterceptor(logging)
            .build();
        mRetrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(mOkHttpClient)
            .build();
    }
//    
//    public DisposableObserver<SearchResponse> getObserverMovies() {
//        
//        return mObserver;
//    }
    
    public Retrofit getRetrofit() {
        if(mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(mOkHttpClient)
                .build();
        }
        return mRetrofit;
    }
}
