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


public class Registro extends AppCompatActivity implements View.OnClickListener{

    private Button butonRegister;
    private EditText editTextEmail;
    private EditText editTextSenha;
    private TextView textViewSignin;

    private ProgressDialog progressDialog;

    //define objeto firebese auth
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        //inicializa objeto firebase auth
        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()!= null){
            //atividade perfil aqui
            finish();
            startActivity(new Intent(getApplicationContext(),Perfil.class ));
        }

        progressDialog = new ProgressDialog(this);

        butonRegister = (Button) findViewById(R.id.buttonRegister);


        editTextEmail = (EditText) findViewById(R.id.edtTextEmail);
        editTextSenha = (EditText) findViewById(R.id.edtTextSenha);

        textViewSignin = (TextView) findViewById(R.id.textVielSignin);

        butonRegister.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);

    }


    private void registerUser(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextSenha.getText().toString().trim();

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
        //verifica se email e password  nao estao vazios
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
                                finish();
                               startActivity(new Intent(getApplicationContext(),Perfil.class ));
                           }

                        }
                        else {
                            //display some message here
                            Toast.makeText(Registro.this,"Erro ao registrar", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }


    @Override
    public void onClick(View view) {

        if (view == butonRegister) {
            registerUser();
        }
        if (view == textViewSignin) {
            startActivity(new Intent(this,Login.class));
        }



    }
}
