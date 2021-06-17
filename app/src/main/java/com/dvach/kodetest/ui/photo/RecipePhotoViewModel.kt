package com.dvach.kodetest.ui.photo

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dvach.kodetest.data.RecipePhotoModel


class RecipePhotoViewModel(private val model: RecipePhotoModel) : ViewModel() {

    private var _isError: MutableLiveData<Boolean> = MutableLiveData()
    var isError: LiveData<Boolean> = _isError

    fun saveImage(bitmap: Bitmap, context: Context, activity: Activity) {
        checkPermissionAndDownloadBitmap(bitmap, context, activity)
    }

    private fun checkPermissionAndDownloadBitmap(
        bitmap: Bitmap,
        context: Context,
        activity: Activity
    ) {
        _isError.postValue(false)
        try {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissions(
                    activity,
                    arrayOf(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ), 2
                )
            } else {
                model.saveImage(bitmap, context)
            }
        } catch (e: Exception) {
            _isError.postValue(true)
        }

    }

}