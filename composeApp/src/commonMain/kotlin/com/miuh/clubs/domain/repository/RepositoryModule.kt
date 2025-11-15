package com.miuh.clubs.domain.repository

import com.miuh.clubs.core.data.domain.repositories.local.LocalDBRepository
import com.miuh.clubs.domain.repository.local.LocalRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<LocalRepository> {
        LocalDBRepository(dao = get())
    }

}