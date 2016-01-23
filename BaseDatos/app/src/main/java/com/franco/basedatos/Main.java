package com.franco.basedatos;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SimpleCursorAdapter;

import com.franco.basedatos.QuotesDataSource.ColumnQuotes;

public class Main extends ListActivity {
    //Código de envío
    public final static int ADD_REQUEST_CODE = 1;
    //Atributos para datos
    private QuotesDataSource dataSource;
    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(android.R.layout.list_content);

        //Crear nuevo objeto QuotesDataSource
        dataSource = new QuotesDataSource(this);

        //Iniciando el nuevo Adaptador
        adapter = new SimpleCursorAdapter(
                this,
                android.R.layout.two_line_list_item,
                dataSource.getAllQuotes(),
                new String[]{ColumnQuotes.BODY_QUOTES,ColumnQuotes.AUTHOR_QUOTES},
                new int[]{android.R.id.text1, android.R.id.text2},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER

        );

        setListAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch(id) {
            case R.id.action_add:
                initForm();//Iniciando la actividad Form
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_REQUEST_CODE) {

            if (resultCode == RESULT_OK) {
                //Insertando el registro con los datos del formulario
                String body = data.getStringExtra("body");
                String author = data.getStringExtra("author");

                dataSource.saveQuoteRow(body,author);
                //Refrescando la lista manualmente
                adapter.changeCursor(dataSource.getAllQuotes());

            }
        }

    }

    private void initForm(){
        //Iniciando la actividad Form
        Intent intent = new Intent(this, Form.class);

        //Inicio de la actividad esperando un resultado
        startActivityForResult(intent, ADD_REQUEST_CODE);
    }
}
