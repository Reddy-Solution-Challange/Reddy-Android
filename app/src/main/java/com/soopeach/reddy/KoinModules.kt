package com.soopeach.reddy

import com.google.gson.GsonBuilder
import com.soopeach.reddy.data.LocalHistoryDataStore
import com.soopeach.reddy.data.UserLocalDataStore
import com.soopeach.reddy.data.datasource.ArticleDataSource
import com.soopeach.reddy.data.datasource.ArticleDataSourceImpl
import com.soopeach.reddy.data.datasource.CheckDataSource
import com.soopeach.reddy.data.datasource.CheckDataSourceImpl
import com.soopeach.reddy.data.datasource.PostDataSource
import com.soopeach.reddy.data.datasource.PostDataSourceImpl
import com.soopeach.reddy.data.datasource.UserDataSource
import com.soopeach.reddy.data.datasource.UserDataSourceImpl
import com.soopeach.reddy.data.respository.ArticleRepositoryImpl
import com.soopeach.reddy.data.respository.CheckRepositoryImpl
import com.soopeach.reddy.data.respository.PostRepositoryImpl
import com.soopeach.reddy.data.respository.UserRepositoryImpl
import com.soopeach.reddy.data.service.ArticleService
import com.soopeach.reddy.data.service.CheckService
import com.soopeach.reddy.data.service.PostService
import com.soopeach.reddy.data.service.UserService
import com.soopeach.reddy.domain.respository.ArticleRepository
import com.soopeach.reddy.domain.respository.CheckRepository
import com.soopeach.reddy.domain.respository.PostRepository
import com.soopeach.reddy.domain.respository.UserRepository
import com.soopeach.reddy.domain.usecase.GetAccessTokenUseCase
import com.soopeach.reddy.domain.usecase.SignUpUseCase
import com.soopeach.reddy.domain.usecase.article.GetAllArticleTitlesUseCase
import com.soopeach.reddy.domain.usecase.article.GetArticleDetailUseCase
import com.soopeach.reddy.domain.usecase.article.GetTodayArticlesUseCase
import com.soopeach.reddy.domain.usecase.article.PostEmotionUseCase
import com.soopeach.reddy.domain.usecase.camera.CheckHowToRecycleUseCase
import com.soopeach.reddy.domain.usecase.camera.GetSavedResultDetailUseCase
import com.soopeach.reddy.domain.usecase.camera.GetSavedResultsUseCase
import com.soopeach.reddy.domain.usecase.camera.RequestSaveResultUseCase
import com.soopeach.reddy.domain.usecase.post.GetRecyclingPostUseCase
import com.soopeach.reddy.domain.usecase.post.GetRecyclingPostsUseCase
import com.soopeach.reddy.domain.usecase.post.GetSearchedRecyclingPostsUseCase
import com.soopeach.reddy.domain.usecase.search.DeleteSearchedKeywordUseCase
import com.soopeach.reddy.domain.usecase.search.GetSearchedKeywordsUseCase
import com.soopeach.reddy.domain.usecase.search.UpdateSearchedKeywordUseCase
import com.soopeach.reddy.presentation.viewmodel.CheckViewModel
import com.soopeach.reddy.presentation.viewmodel.NewsViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

val viewModelModule = module {
    single { CheckViewModel(get()) }
    single { NewsViewModel(get(), get()) }
}

val repositoryModule = module {
    single<UserRepository> { UserRepositoryImpl(get()) }
    single<ArticleRepository> { ArticleRepositoryImpl(get()) }
    single<CheckRepository> { CheckRepositoryImpl(get()) }
    single<PostRepository> { PostRepositoryImpl(get()) }

}

val dataSourceModule = module {
    single<UserDataSource> { UserDataSourceImpl(get(), get(), get()) }
    single<ArticleDataSource> { ArticleDataSourceImpl(get()) }
    single<CheckDataSource> { CheckDataSourceImpl(get()) }
    single<PostDataSource> { PostDataSourceImpl(get()) }
}

val localDataStoreModule = module {
    single<UserLocalDataStore> { UserLocalDataStore(get()) }
    single<LocalHistoryDataStore> { LocalHistoryDataStore(get()) }
}

val useCaseModule = module {

    single { SignUpUseCase(get()) }
    single { GetAccessTokenUseCase(get()) }

    single { GetAllArticleTitlesUseCase(get()) }
    single { GetArticleDetailUseCase(get(), get()) }
    single { GetTodayArticlesUseCase(get(),  get()) }
    single { PostEmotionUseCase(get(), get()) }

    single { CheckHowToRecycleUseCase(get()) }
    single { RequestSaveResultUseCase(get(), get()) }
    single { GetSavedResultsUseCase(get(), get()) }
    single { GetSavedResultDetailUseCase(get(), get()) }

    single { GetSearchedKeywordsUseCase(get()) }
    single { UpdateSearchedKeywordUseCase(get()) }
    single { DeleteSearchedKeywordUseCase(get()) }

    single { GetRecyclingPostsUseCase(get()) }
    single { GetRecyclingPostUseCase(get()) }
    single { GetSearchedRecyclingPostsUseCase(get()) }

}

val networkModule = module {

    val BASE_URL = "http://34.22.111.89:8080/"

    single {

        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.HEADERS
            })
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .setLenient()
                        .create(),
                ),
            )
            .client(get())
            .build()
    }

    single { get<Retrofit>().create(ArticleService::class.java) }
    single { get<Retrofit>().create(UserService::class.java) }
    single { get<Retrofit>().create(CheckService::class.java) }
    single { get<Retrofit>().create(PostService::class.java) }

}
