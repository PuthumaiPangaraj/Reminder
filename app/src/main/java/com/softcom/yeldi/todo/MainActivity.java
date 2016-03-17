package com.softcom.yeldi.todo;

import android.app.Activity;
import android.content.Intent;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.softcom.yeldi.todo.toclass.ViewHistoryList;

public class MainActivity extends AppCompatActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private NavigationDrawerFragment mNavigationDrawerFragment;

    private CharSequence mTitle;
    ListView list;
    String[] web = {
            "Birthday",
            "Anniversary",
            "Payment",
            "Travel",
            "Personal",
            "Offical",
            "Events"
    } ;
    Integer[] imageId = {
            R.drawable.rem_birth,
            R.drawable.rem_anniversary,
            R.drawable.rem_payment,
            R.drawable.rem_travel,
            R.drawable.rem_personal,
            R.drawable.rem_place,
            R.drawable.rem_birth


    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
      /*  mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
*/
        CustomList adapter = new
                CustomList(MainActivity.this, web, imageId);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                switch (position) {
                    case 0:

                        Intent i = new Intent(getApplicationContext(), Birthday.class);
                        i.putExtra("id", position);
                        startActivity(i);
                        break;

                    case 1:
                        //Use some different intent here
                        Intent j = new Intent(getApplicationContext(), Anniv.class);
                        j.putExtra("id", position);
                        startActivity(j);
                        break;
                    case 2:
                        //Use some different intent here
                        Intent k = new Intent(getApplicationContext(), Payment.class);
                        k.putExtra("id", position);
                        startActivity(k);
                        break;
                    case 3:
                        //Use some different intent here
                        Intent l = new Intent(getApplicationContext(), Payment.class);
                        l.putExtra("id", position);
                        startActivity(l);
                        break;
                    case 4:
                        //Use some different intent here
                        Intent m = new Intent(getApplicationContext(), Payment.class);
                        m.putExtra("id", position);
                        startActivity(m);
                        break;
                    case 5:
                        //Use some different intent here
                        Intent n = new Intent(getApplicationContext(), Payment.class);
                        n.putExtra("id", position);
                        startActivity(n);
                        break;
                    case 6:
                        //Use some different intent here
                        Intent o = new Intent(getApplicationContext(), Payment.class);
                        o.putExtra("id", position);
                        startActivity(o);
                        break;
                    case 7:
                        //Use some different intent here
                        Intent p = new Intent(getApplicationContext(), Payment.class);
                        p.putExtra("id", position);
                        startActivity(p);
                        break;

                    default:
                        break;
                }

                //  Toast.makeText(MainActivity.this, "You Clicked at " + web[+position], Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }
*/
@Override
public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
}
 @Override
    public boolean onOptionsItemSelected(MenuItem item) {
     switch(item.getItemId()) {

         case R.id.action_remainder:
           /*  Intent i = new Intent(this, RemainderList.class);
             this.startActivity(i);*/
             Intent intentlist = new Intent(this,ViewHistoryList.class);
             startActivity(intentlist);
             break;
         default:
             return super.onOptionsItemSelected(item);
     }

     return true;
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
