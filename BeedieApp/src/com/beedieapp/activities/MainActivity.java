package com.beedieapp.activities;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.beedieapp.R;
import com.beedieapp.controller.NavigationItemManager;


public class MainActivity extends SherlockFragmentActivity implements ActionBar.OnNavigationListener{
	private TextView mSelected;
    private String[] mLocations;
    private NavigationItemManager navigationItemManager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(R.style.Theme_Sherlock);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		this.navigationItemManager = new NavigationItemManager(this);
		mSelected = (TextView)findViewById(R.id.text);

        mLocations = getResources().getStringArray(R.array.list_menu);
		
		Context context = getSupportActionBar().getThemedContext();
		ArrayAdapter<CharSequence> list = ArrayAdapter.createFromResource(context, R.array.list_menu, R.layout.sherlock_spinner_item);
		list.setDropDownViewResource(R.layout.sherlock_spinner_dropdown_item);

        getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        getSupportActionBar().setListNavigationCallbacks(list, navigationItemManager);
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
//        getSupportMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		mSelected.setText("Selected: " + mLocations[itemPosition]);
        return true;
	}

	
}
