package com.dvach.kodetest.data

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream


class RecipePhotoModel {

    fun saveImage(bitmap: Bitmap, context: Context) { // saving image
        val fOs: OutputStream?
        val name = "${System.currentTimeMillis()}.jpg"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val resolver: ContentResolver =
                context.contentResolver
            val contentValues = ContentValues()
            contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, name)
            contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/png")
            contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
            fOs = resolver.openOutputStream(
                resolver.insert(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    contentValues
                )!!
            )
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOs)
        } else {
            val file = File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
                    .toString() + File.separator,
                "$name.png"
            )
            fOs = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOs)
            MediaStore.Images.Media.insertImage(
                context.contentResolver,
                file.absolutePath,
                file.name,
                file.name
            )
        }
        fOs!!.flush()
        fOs.close()
    }

}
