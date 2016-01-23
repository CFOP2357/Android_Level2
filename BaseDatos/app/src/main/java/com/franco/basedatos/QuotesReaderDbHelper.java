package com.franco.basedatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class QuotesReaderDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Quotes.db";
    public static final int DATABASE_VERSION = 1;

    /**
     * Estructura del constructor de la clase SQLiteOpenHelper
     Recibe el contexto con el cual se relacionará el helper. Luego invoca a super , donde se envía
     el contexto y 3 parámetros adicionales:

     name: String que representa el nombre del archivo con extensión .db donde se almacenará la base
     de datos, que a su vez corresponde al nombre de la base de datos.
     factory: Asignamos null, por ahora no es necesario comprender el funcionamiento de este
     parámetro.
     version: Entero que representa la versión de la base de datos. Su valor inicial por defecto es
     1.
     Si en algún momento la versión es mayor se llama al método onUpgrade() para actualizar la base
     de datos a la nueva versión. Si es menor, se llama a downUpgrade() para volver a una
     versión previa.
     * @param context
     */
    public QuotesReaderDbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Crear la tabla Quotes
        db.execSQL(QuotesDataSource.CREATE_QUOTES_SCRIPT);
        //Insertar registros iniciales
        db.execSQL(QuotesDataSource.INSERT_QUOTES_SCRIPT);

        /*  Nota: Usamos execSQL() ya que las sentencias son
            para uso interno y no están relacionadas con entradas
            proporcionadas por los usuarios
        */
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            /*  Añade los cambios que se realizarán en el esquema
                en tu proxima versión
             */
    }

}
