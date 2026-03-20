package com.miuh.clubs.domain.uc

import com.miuh.clubs.core.data.domain.uc.local.ClubsLocalDBUseCases
import com.miuh.clubs.core.data.domain.uc.local.DeleteByIDUseCaseSuspend
import com.miuh.clubs.core.data.domain.uc.local.GetAllFromDatabaseUseCase
import com.miuh.clubs.core.data.domain.uc.local.InsertClubsInDBUseCaseSuspend
import com.miuh.clubs.domain.uc.local_db_uc.LocalDbUseCases
import org.koin.dsl.module


val useCasesModule = module {



    single<GetAllFromDatabaseUseCase>(
    ) {
        GetAllFromDatabaseUseCase(localDBRepository = get())
    }

    single<InsertClubsInDBUseCaseSuspend>(
    ) {
        InsertClubsInDBUseCaseSuspend(localDBRepository = get())
    }


    single<DeleteByIDUseCaseSuspend>(
    ) {
        DeleteByIDUseCaseSuspend(localDBRepository = get())
    }

    single<LocalDbUseCases> {
        ClubsLocalDBUseCases(
            getAllFromDatabaseUseCase = get(),
            insertClubsInDBUseCase = get(),
            deleteByIDUseCase = get()
        )
    }
}