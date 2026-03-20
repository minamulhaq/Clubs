package com.miuh.clubs.domain.uc.local_db_uc

interface LocalDBUseCaseFlow<in P, out R> {
    operator fun invoke(params: P? = null): R
}