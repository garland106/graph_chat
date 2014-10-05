package com.graphchat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.utils.Constants;
import com.utils.ParseAPIUtils;

public class LoginPage extends Activity
{
	Button loginB = null;
	Button registerB = null;
	EditText usernameEditText;
	EditText passwordEditText;
	EditText displayNameEditText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup_page);
		
		loginB = (Button) findViewById(R.id.loginButton);
		registerB = (Button) findViewById(R.id.registerButton);
		usernameEditText = (EditText) findViewById(R.id.userSignUpName);
		passwordEditText = (EditText) findViewById(R.id.userSignUpPassword);
		displayNameEditText = (EditText) findViewById(R.id.userSignUpDisplayname);
		
		loginB.setOnClickListener(loginListener);
		registerB.setOnClickListener(registerListener);
		
		Parse.initialize(this, Constants.APP_ID, Constants.CLIENT_KEY);
		
	}
	
	/**
	 * OnClickListeners for Buttons
	 */
	private OnClickListener loginListener = new View.OnClickListener() 
	{	
		@Override
		public void onClick(View v) 
		{
			String un = usernameEditText.getText().toString();
			String pw = passwordEditText.getText().toString();
			String dn = displayNameEditText.getText().toString();
			if(un.equals("") || pw.equals("") || dn.equals(""))
			{
				Toast.makeText(getApplicationContext(), "Invalid Credentials", Toast.LENGTH_SHORT).show();
			}
			else
			{ 
				ParseAPIUtils.login(un, pw, dn, getApplicationContext());
				Intent intent = new Intent(LoginPage.this, FriendsListActivity.class);
				startActivity(intent);
			}
		}
	};
	
	private OnClickListener registerListener = new View.OnClickListener() 
	{	
		@Override
		public void onClick(View v) 
		{
			String un = usernameEditText.getText().toString();
			String pw = passwordEditText.getText().toString();
			String dn = displayNameEditText.getText().toString();
			if(un.equals("") || pw.equals("") || dn.equals(""))
			{
				Toast.makeText(getApplicationContext(), "Invalid Credentials", Toast.LENGTH_SHORT).show();
			}
			else
			{
				ParseAPIUtils.register(un, pw, dn, getApplicationContext());
			}
		}
	};
}
