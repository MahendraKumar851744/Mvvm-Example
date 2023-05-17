package com.androidai.testapplication.di

import android.content.Context
import com.androidai.testapplication.MyApplication
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn

@Module
@InstallIn(MyApplication::class)
object GlideModule {
    @Provides
    fun provideGlide(requestManager: RequestManager): RequestManager = requestManager

    @Provides
    fun provideRequestManager(context: Context): RequestManager =
        Glide.with(context)
}
