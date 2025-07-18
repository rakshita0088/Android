package com.example.carrentalcompany.domain.di

import com.example.carrentalcompany.data.repository.CustomerSpeedRepository
import com.example.carrentalcompany.data.repository.SpeedLimitRepositoryImpl
import com.example.carrentalcompany.domain.useCase.SpeedLimitUseCase
import com.example.carrentalcompany.utils.FirebaseService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideFirebaseService(): FirebaseService {
        return FirebaseService() //here we will do Firebase setup
    }
    @Provides
    fun provideSpeedLimitRepository(firebaseService: FirebaseService): CustomerSpeedRepository {
        return SpeedLimitRepositoryImpl(firebaseService)
    }
    @Provides
    fun provideSpeedLimitUseCase(speedLimitRepository: CustomerSpeedRepository): SpeedLimitUseCase {
        return SpeedLimitUseCase(speedLimitRepository)
    }
}