package com.kylix.checklistapp.data.api

import com.kylix.checklistapp.data.api.request.ChecklistItemRequest
import com.kylix.checklistapp.data.api.request.ChecklistRequest
import com.kylix.checklistapp.data.api.response.BaseResponse
import com.kylix.checklistapp.data.api.response.ChecklistItemResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ChecklistItemApiService {
    @GET("/checklist/{checklistId}/item")
    suspend fun getAllItems(@Path("checklistId") checklistId: String): BaseResponse<List<ChecklistItemResponse>>

    @POST("/checklist/{checklistId}/item")
    suspend fun saveItem(@Path("checklistId") checklistId: String, @Body item: ChecklistItemRequest): BaseResponse<Unit>

    @GET("/checklist/{checklistId}/item/{id}")
    suspend fun getItemById(@Path("checklistId") checklistId: String, @Path("id") itemId: String): BaseResponse<ChecklistItemResponse>

    @PUT("/checklist/{checklistId}/item/{id}")
    suspend fun updateItemStatus(@Path("checklistId") checklistId: String, @Path("id") itemId: String): BaseResponse<Unit>

    @DELETE("/checklist/{checklistId}/item/{id}")
    suspend fun deleteItem(@Path("checklistId") checklistId: String, @Path("id") itemId: String): BaseResponse<Unit>

    @PUT("/checklist/{checklistId}/item/rename/{id}")
    suspend fun renameItem(@Path("checklistId") checklistId: String, @Path("id") itemId: String, @Body newName: ChecklistRequest): BaseResponse<Unit>
}