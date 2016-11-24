package lucas.konzen.br.trocalivros;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RegistroUsuarioActivity extends AppCompatActivity implements View.OnClickListener{

    private Button butonRegister;
    private EditText editTextEmail;
    private EditText editTextSenha;
    private TextView editTextNome;
    private TextView textViewSignin;

    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;

    private ProgressDialog progressDialog;

    //define objeto firebese auth


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        //inicializa objeto firebase auth
        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()!= null){
            //atividade principal aqui
            finish();
            startActivity(new Intent(getApplicationContext(),TelaPrincipalActivity.class ));
        }

        FirebaseUser user =firebaseAuth.getCurrentUser();

        databaseReference = FirebaseDatabase.getInstance().getReference();




        progressDialog = new ProgressDialog(this);

        butonRegister = (Button) findViewById(R.id.buttonRegister);


        editTextEmail = (EditText) findViewById(R.id.edtTextEmail);
        editTextSenha = (EditText) findViewById(R.id.edtTextSenha);
        editTextNome =(EditText) findViewById(R.id.editTextNome);

        textViewSignin = (TextView) findViewById(R.id.textVielSignin);

        butonRegister.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);

    }


    private void registerUser(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextSenha.getText().toString().trim();
      //  String nome = editTextNome.getText().toString().trim();

        //verifica se nome, email e password  nao estao vazios
        if(TextUtils.isEmpty(email)){
            // Email esta vazio
            Toast.makeText(this, "Por favor digite o email",Toast.LENGTH_SHORT).show();
            //parar a execução da função adicional
            return;
        }
        if(TextUtils.isEmpty(password )){
            // Senha esta vazio
            Toast.makeText(this, "Por favor digite a password ",Toast.LENGTH_SHORT).show();
            //parar a execução da função adicional
            return;
        }
      /*  if(TextUtils.isEmpty(nome)){
            // Email esta vazio
            Toast.makeText(this, "Por favor digite seu nome e sobrenome",Toast.LENGTH_SHORT).show();
            //parar a execução da função adicional
            return;
        }*/


        //displaying a progress dialog

        progressDialog.setMessage("Registrando, Aguarde...");
        progressDialog.show();

        //criando novo usuario
        firebaseAuth.createUserWithEmailAndPassword(email,password )
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checando se teve susseço
                        if (task.isSuccessful()){
                            if(firebaseAuth.getCurrentUser()!= null){

                               // salvarInformacaoUsuario();


                                finish();
                               startActivity(new Intent(getApplicationContext(),TelaPrincipalActivity.class ));
                           }

                        }
                        else {

                            Toast.makeText(RegistroUsuarioActivity.this,"Erro ao registrar", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });




    }

  /*  private void salvarInformacaoUsuario(){
        String nome = editTextNome.getText().toString().trim();


        InformacaoUsuario infomacaoUsuario = new InformacaoUsuario(nome);

        FirebaseUser user =firebaseAuth.getCurrentUser();

        databaseReference.child(user.getUid()).setValue(infomacaoUsuario);

        Toast.makeText(this,"Informações Salvas",Toast.LENGTH_SHORT).show();


    }*/

    @Override
    public void onClick(View view) {

        if (view == butonRegister) {
            registerUser();
        }
        if (view == textViewSignin) {
            startActivity(new Intent(this,LoginActivity.class));
        }



    }
}
