package tresna.mobile.movieapps;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import tresna.mobile.movieapps.movienowplaying.main.NowFragment;
import tresna.mobile.movieapps.movietoprated.main.TopRatedFragment;
import tresna.mobile.movieapps.movieupcoming.main.UpFragment;
import tresna.mobile.movieapps.searchmovie.main.SearchActivity;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    NowFragment nowFragment = new NowFragment();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frame, nowFragment, NowFragment.class.getSimpleName());
                    fragmentTransaction.commit();

                    return true;
                case R.id.navigation_dashboard:

                    UpFragment upFragment = new UpFragment();
                    FragmentManager fragmentManager2 = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
                    fragmentTransaction2.replace(R.id.frame, upFragment, UpFragment.class.getSimpleName());
                    fragmentTransaction2.commit();

                    return true;
                case R.id.navigation_notifications:

                    TopRatedFragment topRatedFragment = new TopRatedFragment();
                    FragmentManager fragmentManager1 = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
                    fragmentTransaction1.replace(R.id.frame, topRatedFragment, TopRatedFragment.class.getSimpleName());
                    fragmentTransaction1.commit();

                    return true;

                case R.id.navigation_setting:
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if (savedInstanceState == null) {
            Fragment fragment = new NowFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.frame, fragment).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);

        MenuItem menuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                intent.putExtra("query", s);
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        return true;
    }

}