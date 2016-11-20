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

public class Login extends AppCompatActivity implements View.OnClickListener {


    private Button buttonSignin;
    private EditText editTextEmail;
    private EditText editTextSenha;
    private TextView textViewSignup;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        //estou logado?
        if(firebaseAuth.getCurrentUser()!= null){
            //atividade perfil aqui
            finish();
            startActivity(new Intent(getApplicationContext(),TelaPrincipal.class ));
        }

        buttonSignin = (Button) findViewById(R.id.buttonSigin);
        editTextEmail = (EditText) findViewById(R.id.edtTextEmail);
        editTextSenha = (EditText) findViewById(R.id.edtTextSenha);
        textViewSignup = (TextView) findViewById(R.id.textVielSignUp);

        progressDialog = new ProgressDialog(this);


        buttonSignin.setOnClickListener(this);
        textViewSignup.setOnClickListener(this);


    }
    private void userLogin() {
        String email = editTextEmail.getText().toString().trim();
        String senha = editTextSenha.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            // Email esta vazio
            Toast.makeText(this, "Por favor digite o email", Toast.LENGTH_SHORT).show();
            //parar a execução da função adicional
            return;
        }
        if (TextUtils.isEmpty(email)) {
            // Senha esta vazio
            Toast.makeText(this, "Por favor digite a senha", Toast.LENGTH_SHORT).show();
            //parar a execução da função adicional
            return;
        }
        //validaçao do if ok

        progressDialog.setMessage("Entrando...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            // incicia a atividade perfil
                            finish();
                            startActivity(new Intent(getApplicationContext(),TelaPrincipal.class ));

                        }
                    }
                });
    }


    @Override
    public void onClick(View view) {
        if(view == buttonSignin){
            userLogin();
        }
        if(view == textViewSignup){
            startActivity(new Intent(this ,RegistroUsuario.class));
        }
    }
}
