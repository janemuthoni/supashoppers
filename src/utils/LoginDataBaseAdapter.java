package utils;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class LoginDataBaseAdapter {
	static final String DATABASE_NAME = "save.db";
	static final int DATABASE_VERSION = 1;
	public static final int NAME_COLUMN  = 1;
	//create a public field for each of the column
	
	static final String DATABASE_CREATE = "create table"+"SAVE" +
	"(" + "ID"+"integer primary key autoincrement,"+"USERNAME text, PASSWORD text";
	//variables to hold the database instance
	public SQLiteDatabase db;
	//context of application using database
	private final Context context;
	
	//databse open upgrade helper
	private DatabaseHelper dbhelper;
	public LoginDataBaseAdapter(Context _context)
	{
		context = _context;
		dbhelper = new DatabaseHelper(context,DATABASE_NAME, null ,DATABASE_VERSION); 
	}
	public LoginDataBaseAdapter open() throws SQLException 
	{
		db = dbhelper.getWritableDatabase();
		return this;
	}
	public void close()
	{
		db.close();
	}
	public SQLiteDatabase getDatabaseInstance()
	{
		return db;
		
	}
	//inserting username and password
	public void insertEntry(String username, String password)
	{
		ContentValues newValue = new ContentValues();
		//Assign value for each row
		newValue.put("USERNAME",username);
		newValue.put("PASSwORD",password);
		//insert the row into the table
		db.insert("SAVE", null, newValue);
		Toast.makeText(context, "it is saved", Toast.LENGTH_LONG).show();
	}
	public int deleteEntry(String userName)
	{
		//string id =String.valueofID
		String where="USERNAME=?";
		int numberOfEntriesDeleted = db.delete("SAVE", where, new String[]{userName});
		Toast.makeText(context, "number of comment deleted successifully", Toast.LENGTH_SHORT).show();
		return numberOfEntriesDeleted;	
	}
	public String getSingleEntry(String userName)
	{
		Cursor cursor = db.query("LOGIN", null, "USERNAME=?", new String[]{userName},null,null,null);
		if(cursor.getCount()<1)//username Exit
		{
			cursor.close();
			return "NOT EXIT";
		}
		cursor.moveToFirst();
		String password = cursor.getString(cursor.getColumnIndex("PASSWORD"));
		cursor.close();
		return password;
		
	}
	public void updateEntry(String userName,String password)
	{
		//define the updated row content
		ContentValues updateValues = new ContentValues();
		updateValues.put("USERNAME",userName);
		updateValues.put("PASSWORD",password);
		
		String where="USERNAME=?";
		db.update("LOGIN", updateValues, where, new String[]{userName});
		
	}
	


}
