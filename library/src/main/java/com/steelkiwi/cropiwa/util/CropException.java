package com.steelkiwi.cropiwa.util;

public class CropException extends RuntimeException {

    private String breadcrumbs = CropIwaLog.getBreadcrumbs();

    public CropException(Throwable cause) {
        super(cause);
    }

    public String getBreadcrumbs() {
        return breadcrumbs;
    }
}
