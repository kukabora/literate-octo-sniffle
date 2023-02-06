package com.example.donorapplication.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.nio.file.Files;
import java.io.File;

public class ImageToByteArray {
    public static byte[] imageToBytes(File file) throws Exception{
        byte[] fileContent = Files.readAllBytes(file.toPath());
        return fileContent;
    }

    public static Bitmap bytesToImage(byte[] bytes) throws Exception{
        Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        return bmp;
    }
}