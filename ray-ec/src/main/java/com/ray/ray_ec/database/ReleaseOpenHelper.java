package com.ray.ray_ec.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by wrf on 2018/1/30.
 */

public class ReleaseOpenHelper extends DaoMaster.OpenHelper {

    public ReleaseOpenHelper(Context context, String name) {
        super(context, name);
    }

    public ReleaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

}
