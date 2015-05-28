package gko.app.gexam.committed.com_fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.google.zxing.client.android.CaptureActivity;
import com.melnykov.fab.FloatingActionButton;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import gko.app.gexam.Database.Json_to_SQlite;
import gko.app.gexam.Database.OpenHelper;
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

    private Json_to_SQlite json_to_sQlite = new Json_to_SQlite();
    public static final String URL_JSON = "http://gexam.esy.es/GExam/db_connect.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_com_frag);

        new SimpleTask().execute();




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

    private class SimpleTask extends AsyncTask<String, Void, String> {


        ProgressDialog objPD;
        @Override
        protected void onPreExecute() {
            // Create Show ProgressBar


            objPD = new ProgressDialog(ComFragActivity.this);
            objPD.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            objPD.setTitle("Loading...");
            objPD.setMessage("ກະລຸນາລໍຖ້າ...");
            objPD.setCancelable(false);
            objPD.setIndeterminate(false);




            objPD.show();

        }

        protected String doInBackground(String... urls) {

            deleteAll();

            return JSON();
        }

        protected void onPostExecute(String jsonString)  {
            // Dismiss ProgressBar
//            Log.d("Emergency", jsonString);
//            Toast.makeText(getActivity(), jsonString, Toast.LENGTH_LONG).show();





            json_to_sQlite.Student_Unblock(jsonString, ComFragActivity.this);

            objPD.dismiss();


        }







        public String JSON() {

            InputStream objInputStream = null;
            String strJSON = "";

            try {

                HttpClient objHttpClient = new DefaultHttpClient();
                HttpPost objHttpPost = new HttpPost(URL_JSON);
                HttpResponse objHttpResponse = objHttpClient.execute(objHttpPost);
                HttpEntity objHttpEntity = objHttpResponse.getEntity();
                objInputStream = objHttpEntity.getContent();

                Log.d("GExam", "Connected HTTP Success !");


            } catch (Exception e) {

                Log.d("GExam", "Error Connect to : " + e.toString());
            }


            //create strJSON
            try {

                BufferedReader objBufferesReader = new BufferedReader(new InputStreamReader(objInputStream, "UTF-8"));
                StringBuilder objStrBuilder = new StringBuilder();
                String strLine = null;

                while ((strLine = objBufferesReader.readLine()) != null) {
                    objStrBuilder.append(strLine);
                }

                objInputStream.close();
                strJSON = objStrBuilder.toString();

                Log.d("Emergency", "Connected JSON Success !");


            } catch (Exception e) {
                Log.d("Emergency", "Error Convert To JSON :" + e.toString());
            }


            return strJSON;

        }







    }

    public void deleteAll() {

        OpenHelper openHelper = new OpenHelper(ComFragActivity.this);
        SQLiteDatabase db = openHelper.getReadableDatabase();
        db.execSQL("delete from student_unblock");
//        db.execSQL("TRUNCATE table student_unblock");
        db.close();

    }

}