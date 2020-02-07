package com.pruebas.abmp;

import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.pruebas.abmp.models.Animal;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import android.view.Menu;
import android.widget.Switch;

/*
Actividades
Esteticas
FUnciona 1 lista
funcionan 3listas
funcionan Filtros : Familia, Genero, Orden, Amenaza
Funciona onclickListener
esteticas Amenaza
on back pressed y on resume
splash screen e icono de app, home
strings
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        HomeFragment.OnFragmentInteractionListener,
        AnimalsList.OnFragmentInteractionListener,
        AnimalViewFragment.OnFragmentInteractionListener,
        MapFragment.OnFragmentInteractionListener{

    private HomeFragment hf;
    private AnimalsList af;
    private AnimalsList bf;
    private AnimalsList mf;
    private MapFragment mapf;
    private Boolean animalFlag;
    private static final String AMPHIBIANS = "Amphibia";
    private static final String BIRDS = "aves";
    private static final String MAMMALS = "Mammalia";
    private NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        hf = new HomeFragment();
        af = new AnimalsList(AMPHIBIANS,"N");
        bf = new AnimalsList(BIRDS,"N");
        mf = new AnimalsList(MAMMALS,"N");
        mapf = new MapFragment();
        animalFlag=false;


        getSupportFragmentManager().beginTransaction().add(R.id.lycontainer, hf).commit();

        navigationView.setCheckedItem(R.id.nav_home);
        setTitle(R.string.menu_home);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        if(navigationView.getCheckedItem().getItemId()==R.id.nav_amphibians && animalFlag){
            animalFlag = false;
            getSupportFragmentManager().beginTransaction().replace(R.id.lycontainer, af).commit();
        }else if(navigationView.getCheckedItem().getItemId()==R.id.nav_birds && animalFlag)
        {
            animalFlag = false;
            getSupportFragmentManager().beginTransaction().replace(R.id.lycontainer, bf).commit();
        }
        else if(navigationView.getCheckedItem().getItemId()==R.id.nav_mammals && animalFlag)
        {
            animalFlag = false;
            getSupportFragmentManager().beginTransaction().replace(R.id.lycontainer, mf).commit();
        }
        else super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            ft.replace(R.id.lycontainer,hf).commit();
            setTitle(R.string.menu_home);
        } else if (id == R.id.nav_amphibians) {
            ft.replace(R.id.lycontainer,af).commit();
            setTitle(R.string.menu_Amphibians);
        } else if (id == R.id.nav_birds) {
            ft.replace(R.id.lycontainer,bf).commit();
            setTitle(R.string.menu_Birds);
        } else if (id == R.id.nav_mammals) {
            ft.replace(R.id.lycontainer,mf).commit();
            setTitle(R.string.menu_Mammals);
        }else if (id == R.id.nav_where) {
            ft.replace(R.id.lycontainer,mapf).commit();
            setTitle(R.string.menu_where);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void showViewAnimal(Animal a ){
        animalFlag=true;
        getSupportFragmentManager().beginTransaction().replace(R.id.lycontainer,new AnimalViewFragment(a)).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
