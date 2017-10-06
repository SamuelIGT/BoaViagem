package br.ufc.quixada.boaviagem.activity;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.ufc.quixada.boaviagem.R;

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
        TextView txtLogo = (TextView) findViewById(R.id.txt_logo_login);

        animateLogo(txtLogo);


        logar();
    }

    private void animateLogo(TextView txt) {
        ObjectAnimator colorAnim = ObjectAnimator.ofInt(txt, "textColor",
                Color.parseColor("#E44F50"), Color.parseColor("#91D8F0"));
        colorAnim.setEvaluator(new ArgbEvaluator());
        colorAnim.setDuration(4000);
        colorAnim.setRepeatCount(ObjectAnimator.INFINITE);
        colorAnim.setRepeatMode(ObjectAnimator.RESTART);
        colorAnim.start();
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
