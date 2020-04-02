package fja.edu.com.cadastrolegal.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DaoUtil extends SQLiteOpenHelper {
    private static final String NOME_BANCO = "Cadastro.db";
    private static final  int VERSAO = 1;

    public DaoUtil(@Nullable Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    public SQLiteDatabase getConexaoDataBase(){
        return this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder sqlddl = new StringBuilder();
        sqlddl.append("CREATE TABLE Pessoa (");
        sqlddl.append(" idPessoa      INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sqlddl.append(" dsNome        TEXT NOT NULL, ");
        sqlddl.append(" dsEndereco    TEXT NOT NULL, ");
        sqlddl.append(" flSexo        TEXT NOT NULL, ");
        sqlddl.append(" dtNascimento  TEXT NOT NULL, ");
        sqlddl.append(" flEstadoCivil TEXT NOT NULL, ");
        sqlddl.append(" flAtivo       TEXT NOT NULL) ");

        db.execSQL(sqlddl.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Pessoa");
        onCreate(db);
    }
}
