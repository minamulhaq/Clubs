package com.miuh.clubs.core.data.domain.uc.remote

import com.miuh.clubs.core.data.GenType
import com.miuh.clubs.core.data.schema.SchemaOverallStat
import com.miuh.clubs.core.util.Error
import com.miuh.clubs.core.util.Result
import com.miuh.clubs.domain.uc.remote_db_uc.ClubsRemoteRepository
import com.miuh.clubs.domain.uc.remote_db_uc.NetworkingUseCase

class GetClubOverallStatsUseCase(
    private val repository: ClubsRemoteRepository
) : NetworkingUseCase<GenType, Int, Unit?, Result<SchemaOverallStat, Error>> {
    override suspend fun invoke(
        p: GenType?,
        q: Int?,
        r: Unit?
    ): Result<SchemaOverallStat, Error> {
        return repository.getClubOverallStatsUseCase(p!!, q!!)
    }
}