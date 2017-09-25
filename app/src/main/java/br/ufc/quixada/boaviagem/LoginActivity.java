package br.ufc.quixada.boaviagem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private EditText loginField;
    private EditText passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        btnLogin = (Button) findViewById(R.id.btn_login);
        loginField = (EditText) findViewById(R.id.usuario);
        passwordField = (EditText) findViewById(R.id.senha);

        logar();
    }
    public void logar(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login = loginField.getText().toString();
                String password = passwordField.getText().toString();
                if(login.equals(getString(R.string.default_user)) &&
                        password.equals(getString(R.string.default_password))){

                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }else{
                    Toast toast = Toast.makeText(LoginActivity.this, getString(R.string.login_error), Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });
    }
}
