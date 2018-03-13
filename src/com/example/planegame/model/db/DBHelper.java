package com.example.planegame.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public  class DBHelper extends SQLiteOpenHelper{
	private static final String DB_NAME = "datestorage.db";
	private static final int DB_VERSION = 1;

	public DBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}
	
	public DBHelper(Context context,String name){
		this(context,name,DB_VERSION);
	}
	public DBHelper(Context context, String name, int version){
		super(context, name, null, version);
	}

	public DBHelper(Context context, String name,CursorFactory factory, int version) {
		super(context, name, factory, version );
	}


	private static final String CREATE_USERINFO_TABLE_SQL = "create table if not exists " 
			+"userinfo(username verchar(20) primary key ," 
			+"military int not null," 
			+ "money int not null ,"  
			+"map int not null);";
	
	private static final String CREATE_OWNPLANES_TABLE_SQL = "create table if not exists "
			+ "ownplanes(planenumber int primary key);";
	
	private static final String CREATE_RECORDS_TABLE_SQL = "create table if not exists "
			+"records(guanqia int pirmary key, general integer,challenge int);";
			
	
	//��һ�δ�����ʱ����
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_USERINFO_TABLE_SQL);
		db.execSQL(CREATE_OWNPLANES_TABLE_SQL);
		db.execSQL(CREATE_RECORDS_TABLE_SQL);
	}
	
	//ÿ�δ����ݿ�ʱ����
	@Override
	public void onOpen(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		super.onOpen(db);
	}

	//�����ݿ���£�����汾������Ҫ�޸ı�ṹʱ����
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop table if exists userinfo" );
		db.execSQL("drop table if exists records" );
		db.execSQL("drop table if exists ownplanes" );
		onCreate(db);
		
	}
	
}