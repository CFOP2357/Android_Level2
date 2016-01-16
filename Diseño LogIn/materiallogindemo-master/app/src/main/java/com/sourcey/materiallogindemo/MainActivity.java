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
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * An intent is an abstract description of an operation to be performed.
         * It can be used with startActivity to launch an Activity, broadcastIntent to
         * send it to any interested BroadcastReceiver components, and startService(Intent)
         * or bindService(Intent, ServiceConnection, int) to communicate with a background Service.

         An Intent provides a facility for performing late runtime binding between the code
         in different applications. Its most significant use is in the launching of activities,
         where it can be thought of as the glue between activities. It is basically a passive
         data structure holding an abstract description of an action to be performed.
         */
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
