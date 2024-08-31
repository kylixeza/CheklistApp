package com.kylix.checklistapp.data.api

import com.kylix.checklistapp.data.api.request.ChecklistRequest
import com.kylix.checklistapp.data.api.response.BaseResponse
import com.kylix.checklistapp.data.api.response.ChecklistResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ChecklistApiService {

    @POST("checklist")
    suspend fun saveChecklist(
        @Body body: ChecklistRequest
    ): BaseResponse<Unit>

    @GET("checklist")
    suspend fun getChecklist(): BaseResponse<List<ChecklistResponse>>

    @DELETE("checklist/{checklistId}")
    suspend fun deleteChecklist(
        @Path("checklistId") checklistId: Int
    ): BaseResponse<Unit>
}