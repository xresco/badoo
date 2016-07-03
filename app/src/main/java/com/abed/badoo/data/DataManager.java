package com.abed.badoo.data;

import com.abed.badoo.data.local.LocalFilesHelper;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Responsible of managing anything related to data like: local files, sharedPref, DB ....
 * For now it only supports local files since it's the only thing i needed
 */
@Singleton
public class DataManager {

    private final LocalFilesHelper mLocalFilesHelper;

    @Inject
    public DataManager(LocalFilesHelper localFilesHelper) {
        mLocalFilesHelper = localFilesHelper;
    }

    public LocalFilesHelper getLocalFilesHelper() {
        return mLocalFilesHelper;
    }

}
