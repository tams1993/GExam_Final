package gko.app.gexam.committed.com_fragment;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.google.zxing.client.android.CaptureActivity;
import com.melnykov.fab.FloatingActionButton;

import gko.app.gexam.R;
import gko.app.gexam.committed.com_fragment.tab.SlidingTabLayout;
import gko.app.gexam.student.FontsOverride;


public class ComFragActivity extends ActionBarActivity {

    // Declaring Your View and Variables

    Toolbar toolbar;
    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[]={"ກົດລະບຽບ","ລາຍລະອຽດ","ລາຍຊື່ນັກສຶກສາ"};

    int icons[] = {R.drawable.ic_action_paste,R.drawable.ic_action_info,R.drawable.ic_action_error,R.drawable.ic_action_group};

    int Numboftabs = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_com_frag);




        // Creating The Toolbar and setting it as the Toolbar for the activity

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setTitle("ກຳມະການ");


        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter =  new ViewPagerAdapter(getSupportFragmentManager(),Titles,Numboftabs,icons);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });

        tabs.setCustomTabView(R.layout.custom_tab_com, R.id.tabsText);


        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);







    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_com, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.qr_scan) {
//
//
//
//            startActivity(new Intent(this, CaptureActivity.class));
//            finish();
//        }
//
//        return super.onOptionsItemSelected(item);
//    }


    public class ViewPagerAdapter extends FragmentStatePagerAdapter {

        int icons[] = {R.drawable.ic_action_paste,R.drawable.ic_action_info,R.drawable.ic_action_error,R.drawable.ic_action_group};
        CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
        int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created


        // Build a Constructor and assign the passed Values to appropriate values in the class
        public ViewPagerAdapter(FragmentManager fm,CharSequence mTitles[], int mNumbOfTabsumb, int mIcons[]) {
            super(fm);

            this.Titles = mTitles;
            this.NumbOfTabs = mNumbOfTabsumb;
            this.icons = mIcons;

        }

        //This method return the fragment for the every position in the View Pager
        @Override
        public Fragment getItem(int position) {





            if (position == 0) // if the position is 0 we are returning the First tab
            {
                RuleFragment tabRule = new RuleFragment();
                return tabRule;
            }
            if (position == 1)       // As we are having 2 tabs if the position is now 0 it must be 1 so we are returning second tab
            {

                DetailFragment tabDetail = new DetailFragment();
                return tabDetail;

            }
            if (position == 2)       // As we are having 2 tabs if the position is now 0 it must be 1 so we are returning second tab
            {

                StudentNoPermissionFragment studentNoPermissionFragment = new StudentNoPermissionFragment();
                return studentNoPermissionFragment;

            }

            else {

                StudentListFragment tabStudentList = new StudentListFragment();
                return tabStudentList;

            }

        }

        // This method return the titles for the Tabs in the Tab Strip


        @Override
        public CharSequence getPageTitle(int position) {


            Drawable drawable = getResources().getDrawable(icons[position]);
            drawable.setBounds(0,0,96,96);

            ImageSpan imageSpan = new ImageSpan(drawable);
            SpannableString spannableString = new SpannableString(" ");

            spannableString.setSpan(imageSpan,0,spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


            return spannableString;
        }

        // This method return the Number of tabs for the tabs Strip

        @Override
        public int getCount() {
            return NumbOfTabs;
        }
    }

}