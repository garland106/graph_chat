package com.graphchat;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class MainActivity extends Activity 
{
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.signup_page);
		Parse.initialize(this, "j2hPTdOqQufughrO83ZWMCFgXUratrMTugAv5XTs", "y4fgHJizh6s21DWWidrPgvpZjm4gOpfcgMHHv8g1");
		
		
		ParseUser user = new ParseUser();
		user.setUsername("Sy Luu'Brien");
		user.setPassword("password");
		user.setEmail("email@example.com");
		  
		// other fields can be set just like with ParseObject
		user.put("phone", "650-555-0000");
		user.signUpInBackground(new SignUpCallback() {
		  public void done(ParseException e) {
		    if (e == null) {
		      // Hooray! Let them use the app now.
		    } else {
		      // Sign up didn't succeed. Look at the ParseException
		      // to figure out what went wrong
		    }
		  }
		});

	}

}
