package kashyap.`in`.androidbestpractices.injection.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import kashyap.`in`.androidbestpractices.BuildConfig
import kashyap.`in`.androidbestpractices.common.constants.*
import kashyap.`in`.androidbestpractices.data.MyDatabase
import kashyap.`in`.androidbestpractices.injection.ApplicationContext
import kashyap.`in`.androidbestpractices.injection.ApplicationScope
import kashyap.`in`.androidbestpractices.network.HeaderInterceptor
import kashyap.`in`.androidbestpractices.network.webservice.ApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class RepositoryModule {

    @ApplicationScope
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @ApplicationScope
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @ApplicationScope
    @Provides
    fun provideOkHttpInterceptors(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }
    }

    @ApplicationScope
    @Provides
    fun provideHttpClient(
        interceptor: Interceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    @ApplicationScope
    @Provides
    fun provideHeaderInterceptor(@ApplicationContext context: Context): Interceptor {
        return HeaderInterceptor(context)
    }

    @ApplicationScope
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MyDatabase {
        return Room.databaseBuilder(context, MyDatabase::class.java, DATABASE_NAME).build()
    }
}