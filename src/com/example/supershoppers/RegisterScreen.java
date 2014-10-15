package com.example.supershoppers;

import utils.LoginDataBaseAdapter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterScreen extends ActionBarActivity implements OnClickListener {
	EditText etusername, etpassword;
   TextView  tvusername, tvpassword;
	Button btnlogin, btnsubmit;
	String username, password;
	LoginDataBaseAdapter loginDatabaseAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);

		etusername = (EditText) findViewById(R.id.etusername);
		etpassword = (EditText) findViewById(R.id.etpassword);
		btnsubmit = (Button) findViewById(R.id.registersubmit);
		btnsubmit.setOnClickListener(this);
		btnlogin = (Button) findViewById(R.id.registerlogin);
		btnlogin.setOnClickListener(this);
		//creating an instance of sqlite Database
		loginDatabaseAdapter = new LoginDataBaseAdapter(this);
		loginDatabaseAdapter = loginDatabaseAdapter.open();
		
	
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String username = etusername.getText().toString();
		String password = etpassword.getText().toString();
		//validation
		if(username.equals("")||password.equals("")){
			Toast.makeText(getApplicationContext(), "fill the fields", Toast.LENGTH_LONG).show();
		}
		else
		{
			//save the users data in Database
			loginDatabaseAdapter.insertEntry(username,password);
			Toast.makeText(getApplicationContext(), "Account created", Toast.LENGTH_LONG).show();
		}
		
	}
	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		loginDatabaseAdapter.close();
	}
	

	
	}

