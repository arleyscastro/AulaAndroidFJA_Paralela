package fja.edu.com.cadastrolegal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import fja.edu.com.cadastrolegal.util.Utilidades;

public class MainActivity extends AppCompatActivity {

    ListView listViewOpcoes;

    protected void CriaComponentes(){
        listViewOpcoes = (ListView)this.findViewById(R.id.listViewOpcao);
    }

    protected void CarragarItensDaLista(){
        String[] itens = new String[4];
        itens[0]=getString(R.string.cadastrar);
        itens[1]=getString(R.string.consultar);
        itens[2]=getString(R.string.alterar);
        itens[3]=getString(R.string.deletar);
        ArrayAdapter<String> arrayItens = new
                ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                itens);
        listViewOpcoes.setAdapter(arrayItens);

    }

    protected void CriaEventosDoMenu(){
        listViewOpcoes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view,
                                    int position,
                                    long id) {

                String opcao = ( (TextView) view).getText().toString();
                Intent novatela = null;
                boolean ok = false;
                if(opcao.equalsIgnoreCase(getString(R.string.cadastrar))){
                    novatela = new Intent(MainActivity.this,
                            Cadastrar.class);
                    ok=true;
                }
                if(opcao.equalsIgnoreCase(getString(R.string.consultar))){
                    Utilidades.Alerta(MainActivity.this, opcao);
                }
                if(opcao.equalsIgnoreCase(getString(R.string.alterar))){
                    Utilidades.Alerta(MainActivity.this, opcao);
                }
                if(opcao.equalsIgnoreCase(getString(R.string.deletar))){
                    Utilidades.Alerta(MainActivity.this, opcao);
                }
                if(ok){
                    startActivity(novatela);
                    finish();
                }else{
                    Toast.makeText(MainActivity.this,
                            "Opção não reconhecida",
                            Toast.LENGTH_LONG).show();
                }

            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.CriaComponentes();
        this.CarragarItensDaLista();
        this.CriaEventosDoMenu();
    }

}
