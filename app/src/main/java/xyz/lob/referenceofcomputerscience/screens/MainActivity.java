package xyz.lob.referenceofcomputerscience.screens;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import java.util.HashMap;
import java.util.Map;

import xyz.lob.referenceofcomputerscience.GoItemMenu;
import xyz.lob.referenceofcomputerscience.R;
import xyz.lob.referenceofcomputerscience.ui.recycle.PostsFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private DrawerLayout drawerLayout;
    private NavController navController;
    private Toolbar toolbar;
    private final Map<Integer, GoItemMenu> mapMenuItem = new HashMap<>();

    private void goPostsFragment(String title){
        Bundle bundle = new Bundle();
        bundle.putString(PostsFragment.ARG_CATEGORY, title);
        navController.navigate(R.id.nav_postFragment, bundle);
    }

    {
        mapMenuItem.put(R.id.nav_home, title -> navController.navigate(R.id.nav_home));
        mapMenuItem.put(R.id.nav_re_linux,this::goPostsFragment);
        mapMenuItem.put(R.id.nav_re_win, this::goPostsFragment);
        mapMenuItem.put(R.id.nav_re_net, this::goPostsFragment);
        mapMenuItem.put(R.id.nav_re_pc, this::goPostsFragment);
        mapMenuItem.put(R.id.nav_re_logic, this::goPostsFragment);
        mapMenuItem.put(R.id.nav_re_formuls, this::goPostsFragment);
        mapMenuItem.put(R.id.nav_re_glossary, this::goPostsFragment);
        mapMenuItem.put(R.id.nav_re_systemNumber, title -> navController.navigate(R.id.nav_calcSystemNumber));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_home)
                .setDrawerLayout(drawerLayout)
                .build();
        navController = ((NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment)).getNavController();
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        mapMenuItem.get(item.getItemId()).goItemMenu(item.getTitle().toString());
//        switch (item.getItemId()) {
//            case R.id.nav_home:{
//                navController.navigate(R.id.nav_home);
//                break;
//            }
//            case R.id.nav_re_linux: {
//                navController.navigate(R.id.nav_postFragment);
//                toolbar.setTitle("Linux");
//                break;
//            }
//            case R.id.nav_re_win: {
//                navController.navigate(R.id.nav_postFragment);
//                toolbar.setTitle("Windows");
//                break;
//            }
//            case R.id.nav_re_systemNumber:{
//                navController.navigate(R.id.nav_calcSystemNumber);
//            }
//        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        toolbar.setTitle(title);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


}