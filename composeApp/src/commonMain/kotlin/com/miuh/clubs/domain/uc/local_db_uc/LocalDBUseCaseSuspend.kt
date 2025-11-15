package com.miuh.clubs.domain.uc.local_db_uc

interface LocalDBUseCaseSuspend<in P, out R> {
    suspend operator fun invoke(params: P? = null): R
}