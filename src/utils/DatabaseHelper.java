package utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
		//called when there is no database exist in disk and the helper class
		//needs to create a new one
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(LoginDataBaseAdapter.DATABASE_CREATE);
		
	}
     //Called when there is a database version mismach meaning that the version
	//ot the database on the disk needs to be upgraded to the current version
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		//log the version upgrade
		Log.w("TaskAdapter","upgrading from version" +oldVersion + "to" +
		+newVersion + ",which will destroy all old data");
		//the simplest way is to drop the old data and create a new one
		
		db.execSQL("DROP TABLE IF EXISTS" + "TEMPLATE");
		onCreate(db);
		
	}

}
