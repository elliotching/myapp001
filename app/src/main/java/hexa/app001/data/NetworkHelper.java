package hexa.app001.data;

import android.widget.ImageView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.squareup.picasso.Picasso;

import hexa.app001.R;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class NetworkHelper {
    
    private static final String BASE_URL = "http://www.omdbapi.com";
    private OkHttpClient mOkHttpClient;
    private Retrofit mRetrofit;
    
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
    
    public static void setImageUrl(ImageView imgView, String url, float targetDp){
        targetDp = Res.pxToDp(targetDp);
        Picasso.get()
            .load(url)
            .resize((int)targetDp, 0)
            .placeholder(R.drawable.ic_pending)
            .error(R.drawable.ic_broken)
            .into(imgView);
    }
}
