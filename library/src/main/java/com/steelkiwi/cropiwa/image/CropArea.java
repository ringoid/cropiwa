package com.steelkiwi.cropiwa.image;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.RectF;

import com.steelkiwi.cropiwa.util.CropIwaLog;

/**
 * @author yarolegovich
 * 25.02.2017.
 */
public class CropArea {

    public static CropArea create(RectF coordinateSystem, RectF imageRect, RectF cropRect) {
        return new CropArea(
                moveRectToCoordinateSystem(coordinateSystem, imageRect),
                moveRectToCoordinateSystem(coordinateSystem, cropRect));
    }

    private static Rect moveRectToCoordinateSystem(RectF system, RectF rect) {
        float originX = system.left, originY = system.top;
        return new Rect(
                Math.round(rect.left - originX), Math.round(rect.top - originY),
                Math.round(rect.right - originX), Math.round(rect.bottom - originY));
    }

    private final Rect imageRect;
    private final Rect cropRect;

    private CropArea(Rect imageRect, Rect cropRect) {
        this.imageRect = imageRect;
        this.cropRect = cropRect;
    }

    Bitmap applyCropTo(Bitmap bitmap) throws IllegalArgumentException {
        Bitmap immutableCropped = Bitmap.createBitmap(bitmap,
                findRealCoordinate(bitmap.getWidth(), cropRect.left, imageRect.width()),
                findRealCoordinate(bitmap.getHeight(), cropRect.top, imageRect.height()),
                findRealCoordinate(bitmap.getWidth(), cropRect.width(), imageRect.width()),
                findRealCoordinate(bitmap.getHeight(), cropRect.height(), imageRect.height()));
        Bitmap.Config config = immutableCropped.getConfig();
        if (config == null) {
            CropIwaLog.breadcrumb("Failed to get config of bitmap");
            throw new IllegalArgumentException("Failed to get config of bitmap");
        }
        return immutableCropped.copy(config, true);
    }


    private int findRealCoordinate(int imageRealSize, int cropCoordinate, float cropImageSize) {
        return Math.round((imageRealSize * cropCoordinate) / cropImageSize);
    }

}
