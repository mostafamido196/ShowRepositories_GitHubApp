package com.example.odcgithubrepoapp.presentation.mapper

import android.util.Log
import com.example.odcgithubrepoapp.presentation.model.CustomRemoteExceptionUiModel


fun com.samy.domain.model.CustomRemoteExceptionDomainModel.toCustomExceptionRemoteUiModel(): CustomRemoteExceptionUiModel {
    Log.d("Exception", this.toString())
    return when (this) {
        is com.samy.domain.model.CustomRemoteExceptionDomainModel.NoInternetConnectionRemoteException -> CustomRemoteExceptionUiModel.NoInternetConnection
        is com.samy.domain.model.CustomRemoteExceptionDomainModel.TimeOutExceptionRemoteException -> CustomRemoteExceptionUiModel.Timeout
        is com.samy.domain.model.CustomRemoteExceptionDomainModel.ServiceUnavailableRemoteException -> CustomRemoteExceptionUiModel.ServiceUnreachable
        is com.samy.domain.model.CustomRemoteExceptionDomainModel.AccessDeniedRemoteException -> CustomRemoteExceptionUiModel.ServiceUnreachable
        is com.samy.domain.model.CustomRemoteExceptionDomainModel.ServiceNotFoundRemoteException -> CustomRemoteExceptionUiModel.ServiceUnreachable
        is com.samy.domain.model.CustomRemoteExceptionDomainModel.UnknownRemoteException -> CustomRemoteExceptionUiModel.Unknown
    }
}