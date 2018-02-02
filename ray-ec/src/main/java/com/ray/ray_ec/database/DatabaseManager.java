package com.ray.ray_ec.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * Created by wrf on 2018/1/30.
 */

public class DatabaseManager {

    private DaoSession mDaoSession;
    private UserProfileDao mDao;

    private DatabaseManager(){

    }

    private static final class Holder{
        private static final DatabaseManager INSTANCE = new DatabaseManager();
    }

    public static DatabaseManager getInstance(){
        return Holder.INSTANCE;
    }

    public DatabaseManager init(Context context){
        initDao(context);
        return this;
    }

    private void initDao(Context context){
        final ReleaseOpenHelper helper = new ReleaseOpenHelper(context,"ray_ec");
        final Database writableDb = helper.getWritableDb();
        mDaoSession = new DaoMaster(writableDb).newSession();
        mDao = mDaoSession.getUserProfileDao();
    }

    public UserProfileDao getDao() {
        return mDao;
    }
}
