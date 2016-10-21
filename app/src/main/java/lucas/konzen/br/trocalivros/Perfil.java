package lucas.konzen.br.trocalivros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Perfil extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    private TextView textViewUserEmail;
    private Button buttonLogout;
    private EditText editTextNome;
    private Spinner spinnerEstado;
    private Spinner spinnerCidade;
    private Button buttonSalvar;

    ArrayAdapter<CharSequence> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        firebaseAuth = FirebaseAuth.getInstance();

        //estou logado?
        if(firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this,Login.class));
        }

        FirebaseUser user =firebaseAuth.getCurrentUser();

        databaseReference = FirebaseDatabase.getInstance().getReference();

        //display email logado
        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
        textViewUserEmail.setText(user.getEmail());

        editTextNome =(EditText) findViewById(R.id.editTextNome);
        spinnerEstado =(Spinner) findViewById(R.id.spinnerEstado);
        spinnerCidade =(Spinner) findViewById(R.id.spinnerCidade);

        adapter = ArrayAdapter.createFromResource(this,R.array.estados_nomes,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEstado.setAdapter(adapter);



        buttonSalvar = (Button) findViewById(R.id.buttonSalvar);
        buttonLogout = (Button) findViewById(R.id.buttonLogout);

        // adicionando listener ao botao
        buttonLogout.setOnClickListener(this);
        buttonSalvar.setOnClickListener(this);

        spinnerEstado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



    }
    private void salvarInformacaoUsuario(){
        String nome = editTextNome.getText().toString().trim();


        InformacaoUsuario infomacaoUsuario = new InformacaoUsuario(nome);

        FirebaseUser user =firebaseAuth.getCurrentUser();

        databaseReference.child(user.getUid()).setValue(infomacaoUsuario);

        Toast.makeText(this,"Informações Salvas",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View view) {


        if(view == buttonLogout){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this,Login.class));
        }
        if(view == buttonSalvar){
            salvarInformacaoUsuario();
        }
    }
}
