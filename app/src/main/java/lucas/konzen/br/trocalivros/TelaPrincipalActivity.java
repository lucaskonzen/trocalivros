package lucas.konzen.br.trocalivros;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

public class TelaPrincipalActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth firebaseAuth;
   // private DatabaseReference databaseReference;
  //  private TextView textViewUserEmail;
   // private TextView textViewNome;

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);


        firebaseAuth = FirebaseAuth.getInstance();

        //estou logado?
        if(firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }

       // FirebaseUser user = firebaseAuth.getCurrentUser();

       // databaseReference = FirebaseDatabase.getInstance().getReference();


        //textView email do usuario logado
      //  textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
       // textViewUserEmail.setText(user.getEmail());


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        fragmentManager = getSupportFragmentManager();

        // chama fragment maps e coloca no container da tela principal
       // if(savedInstanceState == null){
           FragmentTransaction transaction = fragmentManager.beginTransaction();

             transaction.replace(R.id.container, new MapsFragment(),"MapsFragment");

             transaction.commitAllowingStateLoss();
       // }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tela_principal, menu);
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

    private void showFragment (Fragment fragment, String name){


        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.replace(R.id.container, fragment, name);

        transaction.commit();

    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id)
        {
            case R.id.nav_mapa:
                showFragment(new MapsFragment(),"MapsFragments");
                break;

            case R.id.nav_novo_anuncio:
                showFragment(new CadastroProdutoFragment(),"CadastroProdutoFragment");
                break;

            case R.id.nav_meus_anuncios:
                break;

            case R.id.nav_mensagens:
                break;

            case R.id.nav_logoff:
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(this,LoginActivity.class));
                break;
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
