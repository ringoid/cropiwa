package com.steelkiwi.cropiwa;

import android.net.Uri;

public interface OnImageLoadListener {

    void onSuccess(Uri uri);
    void onFailure(Throwable e);
}
