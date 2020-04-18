package fja.edu.com.cadastrolegal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import fja.edu.com.cadastrolegal.model.PessoaDto;
import fja.edu.com.cadastrolegal.repository.PessoaRepository;
import fja.edu.com.cadastrolegal.util.LinhaAdapter;

public class ConsultarActivity extends AppCompatActivity {
    ListView lstPessoas;
    Button btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);

        lstPessoas = (ListView)this.findViewById(R.id.conLstPessoas);
        btnVoltar = (Button)this.findViewById(R.id.conBtnVoltar);
        this.MontaLista();
        this.CriaEventoBotaoVoltar();
    }

    protected void MontaLista(){
        PessoaRepository pessoaRepository = new PessoaRepository(this);
        List<PessoaDto> pessoasDto = pessoaRepository.getTodos();
        lstPessoas.setAdapter(new LinhaAdapter(this,pessoasDto));
    }

    protected void CriaEventoBotaoVoltar(){
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaMain = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(telaMain);
                finish();
            }
        });
    }



}
