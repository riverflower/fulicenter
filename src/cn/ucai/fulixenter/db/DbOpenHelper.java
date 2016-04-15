/**
 * Copyright (C) 2013-2014 EaseMob Technologies. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.ucai.fulixenter.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import cn.ucai.fulixenter.applib.controller.HXSDKHelper;

public class DbOpenHelper extends SQLiteOpenHelper{

	private static final int DATABASE_VERSION = 4;
	private static DbOpenHelper instance;

	private static final String USERNAME_TABLE_CREATE = "CREATE TABLE "
			+ EMUserDao.TABLE_NAME + " ("
			+ EMUserDao.COLUMN_NAME_NICK + " TEXT, "
			+ EMUserDao.COLUMN_NAME_AVATAR + " TEXT, "
			+ EMUserDao.COLUMN_NAME_ID + " TEXT PRIMARY KEY);";
	
	private static final String INIVTE_MESSAGE_TABLE_CREATE = "CREATE TABLE "
			+ InviteMessgeDao.TABLE_NAME + " ("
			+ InviteMessgeDao.COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ InviteMessgeDao.COLUMN_NAME_FROM + " TEXT, "
			+ InviteMessgeDao.COLUMN_NAME_GROUP_ID + " TEXT, "
			+ InviteMessgeDao.COLUMN_NAME_GROUP_Name + " TEXT, "
			+ InviteMessgeDao.COLUMN_NAME_REASON + " TEXT, "
			+ InviteMessgeDao.COLUMN_NAME_STATUS + " INTEGER, "
			+ InviteMessgeDao.COLUMN_NAME_ISINVITEFROMME + " INTEGER, "
			+ InviteMessgeDao.COLUMN_NAME_TIME + " TEXT); ";
			
	private static final String ROBOT_TABLE_CREATE = "CREATE TABLE "
			+ EMUserDao.ROBOT_TABLE_NAME + " ("
			+ EMUserDao.ROBOT_COLUMN_NAME_ID + " TEXT PRIMARY KEY, "
			+ EMUserDao.ROBOT_COLUMN_NAME_NICK + " TEXT, "
			+ EMUserDao.ROBOT_COLUMN_NAME_AVATAR + " TEXT);";
			
	private static final String CREATE_PREF_TABLE = "CREATE TABLE "
            + EMUserDao.PREF_TABLE_NAME + " ("
            + EMUserDao.COLUMN_NAME_DISABLED_GROUPS + " TEXT, "
            + EMUserDao.COLUMN_NAME_DISABLED_IDS + " TEXT);";
	
	private DbOpenHelper(Context context) {
		super(context, getUserDatabaseName(), null, DATABASE_VERSION);
	}
	
	public static DbOpenHelper getInstance(Context context) {
		if (instance == null) {
			instance = new DbOpenHelper(context.getApplicationContext());
		}
		return instance;
	}
	
	private static String getUserDatabaseName() {
        return  HXSDKHelper.getInstance().getHXId() + "_demo.db";
    }
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(USERNAME_TABLE_CREATE);
		db.execSQL(INIVTE_MESSAGE_TABLE_CREATE);
		db.execSQL(CREATE_PREF_TABLE);
		db.execSQL(ROBOT_TABLE_CREATE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if(oldVersion < 2){
		    db.execSQL("ALTER TABLE "+ EMUserDao.TABLE_NAME +" ADD COLUMN "+
		            EMUserDao.COLUMN_NAME_AVATAR + " TEXT ;");
		}
		
		if(oldVersion < 3){
		    db.execSQL(CREATE_PREF_TABLE);
        }
		if(oldVersion < 4){
			db.execSQL(ROBOT_TABLE_CREATE);
		}
	}
	
	public void closeDB() {
	    if (instance != null) {
	        try {
	            SQLiteDatabase db = instance.getWritableDatabase();
	            db.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        instance = null;
	    }
	}
	
}