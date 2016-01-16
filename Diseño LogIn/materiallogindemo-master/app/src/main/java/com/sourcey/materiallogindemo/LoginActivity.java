/**
 Un Paquete en Java es un contenedor de clases que permite agrupar las distintas partes de un
 programa cuya funcionalidad tienen elementos comunes.
 */
package com.sourcey.materiallogindemo;

/**
 * Para poder interactuar entre entre diferentes paquetes, por ejemplo utilizar una clase desde
 * otra clase, necesitamos importar esa clase que necesitamos. Esto no es necesario cuando las
 * clases se encuentran en el mismo paquete, pero cuando esto no es así debemos hacer uso de la
 * cláusula import la cual siempre tiene que ir luego de la clausula package (si existiera) y
 * antes de la declaración de clases
 *
 */
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Annotate fields with @Bind and a view ID for Butter Knife to find and automatically
 * cast the corresponding view in your layout.
 *
 * http://jakewharton.github.io/butterknife/
 */
import butterknife.ButterKnife;
import butterknife.Bind;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    @Bind(R.id.input_email) EditText _emailText;
    @Bind(R.id.input_password) EditText _passwordText;
    @Bind(R.id.btn_login) Button _loginButton;
    @Bind(R.id.link_signup) TextView _signupLink;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        
        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**
                 *  getApplication() and getApplicationContext() return the same object,
                 *  there is no guarantee that this will always be the case (for example,
                 *  in a specific vendor implementation).

                 So if you want the Application class you registered in the Manifest,
                 you should never call getApplicationContext() and cast it to your application,
                 because it may not be the application instance
                 (which you obviously experienced with the test framework).

                 Why does getApplicationContext() exists in the first place ?

                 getApplication() is only available on the Activity class and in the Service class,
                 whereas getApplicationContext() is declared in the Context class.

                 That actually means one thing : when writing code in a broadcast receiver,
                 which is not a context but is given a context in its onReceive method,
                 you can only call getApplicationContext(). Which also means that you are
                 not guaranteed to have access to your application in a BroadcastReceiver.

                 When looking at the Android code, you see that when attached,
                 an activity receives a base context and an application,
                 and those are different parameters. getApplicationContext()
                 delegates it's call to baseContext.getApplicationContext().
                 */
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }

    public void login() {
        /**
         * Log is a logging class that you can utilize in your code to print out messages to the
         * LogCat. Common logging methods include:

         v(String, String) (verbose)
         d(String, String) (debug)
         i(String, String) (information)
         w(String, String) (warning)
         e(String, String) (error)
         */
        Log.d(TAG, "Ingresa");
        if (!validate()) {
            onLoginFailed();
            return;
        }
        _loginButton.setEnabled(false);

        /**
         * A dialog showing a progress indicator and an optional text message or view.
         * Only a text message or a view can be used at the same time.

         The dialog can be made cancelable on back key press.

         The progress range is 0..10000.
         */
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Autentificando...");
        progressDialog.show();

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        // AQUI SE AGREGARA RESPECTIVAMENTE LAS BASES DE DATOS

        /**
         * A Handler allows you to send and process Message and Runnable objects associated with a
         * thread's MessageQueue. Each Handler instance is associated with a single thread and that
         * thread's message queue. When you create a new Handler, it is bound to the
         * thread / message queue of the thread that is creating it -- from that point on,
         * it will deliver messages and runnables to that message queue and execute them as
         * they come out of the message queue.
         */
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    /**
     * When the user is done with the subsequent activity and returns,
     * the system calls your activity's onActivityResult() method. This method
     * includes three arguments:

     The request code you passed to startActivityForResult().
     A result code specified by the second activity. This is either
     RESULT_OK if the operation was successful or RESULT_CANCELED if the user backed out or
     the operation failed for some reason.
     An Intent that carries the result data.

     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                //Se agregara el registro de base de datos
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        /**
         * The method that called finish() will run to completion. The finish() operation will
         * not even begin until you return control to Android
         */
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Fallo logIn", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("ingresa un email valido");
            valid = false;
        } else {
            _emailText.setError(null);
        }
        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("entre 4 y 10 caracteres alfanumericos");
            valid = false;
        } else {
            _passwordText.setError(null);
        }
        return valid;
    }
}
