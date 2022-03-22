package com.nrk.movieshub.data.model

//sealed class ApiResponse<T>(val data:T?, val message: String?) {
//    class Success<T>(data: T) : ApiResponse<T>(data, null)
//    class Error<T>(message: String) : ApiResponse<T>(null, message)
//}

sealed class ApiResponse<out T> {
    data class Success<out T>(val data: T) : ApiResponse<T>()
    data class Error(val code: Int? = null, val error: String? = null) : ApiResponse<Nothing>()
    object NetworkError : ApiResponse<Nothing>()
}