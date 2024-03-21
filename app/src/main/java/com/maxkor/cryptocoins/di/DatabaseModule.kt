//package com.maxkor.cryptocoins.di
//
//import android.content.Context
//import com.maxkor.cryptocoins.data.db.CryptocoinsDatabase
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.qualifiers.ApplicationContext
//import dagger.hilt.components.SingletonComponent
//import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponent::class)
// object DatabaseModule {
//
//    @Provides
//    @Singleton
//    fun provideCryptocoinsDatabase(
//        @ApplicationContext context: Context,
//    ): CryptocoinsDatabase = CryptocoinsDatabase.getInstance(context)
//
////    @Provides
////    fun provideStudentsDao(
////        cryptocoinsDatabase: CryptocoinsDatabase,
////    ): CryptocoinsDao = cryptocoinsDatabase.cryptocoinsDao()
//
//}