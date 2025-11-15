package com.miuh.clubs.domain.uc

import com.miuh.clubs.core.data.domain.repositories.local.LocalDBRepository
import com.miuh.clubs.core.data.domain.uc.local.ClubsLocalDBUseCases
import com.miuh.clubs.domain.repository.local.LocalRepository
import com.miuh.clubs.domain.uc.local_db_uc.DeleteByIDUseCaseSuspend
import com.miuh.clubs.domain.uc.local_db_uc.GetAllFromDatabaseUseCase
import com.miuh.clubs.domain.uc.local_db_uc.InsertClubsInDBUseCaseSuspend
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