package fja.edu.com.cadastrolegal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import fja.edu.com.cadastrolegal.model.EstadoCivilDto;
import fja.edu.com.cadastrolegal.model.PessoaDto;
import fja.edu.com.cadastrolegal.repository.PessoaRepository;
import fja.edu.com.cadastrolegal.util.Utilidades;

public class Cadastrar extends AppCompatActivity {

    EditText txtNome;
    EditText txtEndereco;
    RadioButton rbSexoMasc;
    RadioButton rbSexoFem;
    EditText txtDtNasc;
    Spinner spEstadoCivil;
    CheckBox chkAtivo;
    Button btnSalvar;
    Button btnVoltar;
    RadioGroup rgSexo;

    DatePickerDialog dpkData;

    protected void vincularComponentes(){
        txtNome = (EditText)this.findViewById(R.id.cadTxtNome);
        txtEndereco = (EditText)this.findViewById(R.id.cadTxtEndereco);
        txtDtNasc = (EditText)this.findViewById(R.id.cadTxtEndereco);
        rbSexoMasc = (RadioButton)this.findViewById(R.id.cadRbMasc);
        rbSexoFem = (RadioButton)this.findViewById(R.id.cadRbFem);
        spEstadoCivil = (Spinner)this.findViewById(R.id.cadSpinEstCivil);
        chkAtivo = (CheckBox)this.findViewById(R.id.cadChkAtivo);
        btnSalvar = (Button)this.findViewById(R.id.cadBtnSalvar);
        btnVoltar = (Button)this.findViewById(R.id.cadBtnVoltar);
        rgSexo = (RadioGroup)this.findViewById(R.id.cadGrRbSexo);
    }

    protected void carregarestadoCivil(){
        ArrayAdapter<EstadoCivilDto> arrayAdapter;
        List<EstadoCivilDto> itens = new ArrayList<EstadoCivilDto>();
        itens.add(new EstadoCivilDto("S", getString(R.string.solteiro )));
        itens.add(new EstadoCivilDto("C", getString(R.string.casado )));
        itens.add(new EstadoCivilDto("V", getString(R.string.viuvo )));
        itens.add(new EstadoCivilDto("D", getString(R.string.divorciado )));

        arrayAdapter = new ArrayAdapter<EstadoCivilDto>(this,
                android.R.layout.simple_spinner_item, itens);
        spEstadoCivil.setAdapter(arrayAdapter);
    }

    protected void limaCampos(){
        txtNome.setText(null);
        txtDtNasc.setText(null);
        txtEndereco.setText(null);
        rgSexo.clearCheck();
        chkAtivo.setChecked(false);
    }

    protected void salvaRegistro(){
        if(txtNome.getText().toString().trim().isEmpty()){
            Utilidades.Alerta(this,getString(R.string.nome_obrigatorio));
            txtNome.requestFocus();
        }else if(txtEndereco.getText().toString().trim().isEmpty()){
            Utilidades.Alerta(this,getString(R.string.endereco_obrigatorio));
            txtEndereco.requestFocus();
        }else if(txtDtNasc.getText().toString().trim().isEmpty()){
            Utilidades.Alerta(this,getString(R.string.dtnascimento_obrigatorio));
            txtDtNasc.requestFocus();
        }else if(!rbSexoFem.isChecked() && !rbSexoMasc.isChecked()){
            Utilidades.Alerta(this,getString(R.string.sexo_obrigatorio));
        }else{
            PessoaDto pessoa = new PessoaDto();
            pessoa.setNome(txtNome.getText().toString());
            pessoa.setEndereco(txtEndereco.getText().toString());
            pessoa.setDatanascimento(txtDtNasc.getText().toString());
            if(rbSexoMasc.isChecked()){
                pessoa.setSexo("M");
            }else{
                pessoa.setSexo("F");
            }
            EstadoCivilDto ecv = (EstadoCivilDto)spEstadoCivil.getSelectedItem();
            pessoa.setEstadocivil(ecv.getCodigo());
            if(chkAtivo.isActivated()){
                pessoa.setAtivo("1");
            }else{
                pessoa.setAtivo("0");
            }
            new PessoaRepository(this).salvar(pessoa);
            Utilidades.Alerta(this,getString(R.string.salvo_ok));
            this.limaCampos();
            //Teste...
        }

    }

    protected void geraEventos(){
        final Calendar dtNasc = Calendar.getInstance();
        int ano = dtNasc.get(Calendar.YEAR);
        int mes = dtNasc.get(Calendar.MONTH);
        int dia = dtNasc.get(Calendar.DAY_OF_MONTH);

        dpkData = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String mesPicker;
                if(String.valueOf(month+1).length() == 1){
                    mesPicker = "0" + (month+1);
                }else {
                    mesPicker = String.valueOf(month);
                }
                txtDtNasc.setText(dayOfMonth + "/" + mesPicker + "/" + year);
            }
        }, ano, mes,dia);

        txtDtNasc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dpkData.show();
            }
        });

        txtDtNasc.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                dpkData.show();
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvaRegistro();
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaMain = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(telaMain);
                finish();
            }
        });

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        vincularComponentes();
        carregarestadoCivil();
        geraEventos();

    }


}
