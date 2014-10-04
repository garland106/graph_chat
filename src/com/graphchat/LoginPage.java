package com.graphchat;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class LoginPage extends Activity
{
	Button loginB = null;
	Button registerB = null;
	EditText usernameEditText;
	EditText passwordEditText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup_page);
		
		loginB = (Button) findViewById(R.id.loginButton);
		registerB = (Button) findViewById(R.id.registerButton);
		usernameEditText = (EditText) findViewById(R.id.userSignUpName);
		passwordEditText = (EditText) findViewById(R.id.userSignUpPassword);
		
		loginB.setOnClickListener(loginListener);
		registerB.setOnClickListener(registerListener);
		
		Parse.initialize(this, "j2hPTdOqQufughrO83ZWMCFgXUratrMTugAv5XTs", "y4fgHJizh6s21DWWidrPgvpZjm4gOpfcgMHHv8g1");
	}
	
	/**
	 * OnClickListeners for the button
	 */
	private OnClickListener loginListener = new View.OnClickListener() 
	{	
		@Override
		public void onClick(View v) 
		{
			String un = usernameEditText.getText().toString();
			String pw = passwordEditText.getText().toString();
			if(un.equals("") || pw.equals(""))
			{
				Toast.makeText(getApplicationContext(), "Invalid Credentials", Toast.LENGTH_SHORT).show();
			}
			else
			{
				ParseUser.logInInBackground(un, pw, new LogInCallback()
				{
					  public void done(ParseUser user, ParseException e) 
					  {
						  if (e == null) 
						    {
						    	Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
						    } 
						    else 
						    {
						    	Toast.makeText(getApplicationContext(), "Invalid Login", Toast.LENGTH_SHORT).show();
						    }
					  }
					});
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
			if(un.equals("") || pw.equals(""))
			{
				Toast.makeText(getApplicationContext(), "Invalid Credentials", Toast.LENGTH_SHORT).show();
			}
			else
			{
				ParseUser user = new ParseUser();
				user.setUsername(un);
				user.setPassword(pw);
				user.setEmail(un);
				  
				user.signUpInBackground(new SignUpCallback() 
				{
				  public void done(ParseException e) 
				  {
				    if (e == null) 
				    {
				    	Toast.makeText(getApplicationContext(), "Register Success", Toast.LENGTH_SHORT).show();
				    } 
				    else 
				    {
				    	Toast.makeText(getApplicationContext(), "Registration Failed", Toast.LENGTH_SHORT).show();
				    }
				  }
				});
			}
		}
	};
}
