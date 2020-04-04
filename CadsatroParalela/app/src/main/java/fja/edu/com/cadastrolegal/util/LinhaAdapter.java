package fja.edu.com.cadastrolegal.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fja.edu.com.cadastrolegal.ConsultarActivity;
import fja.edu.com.cadastrolegal.R;
import fja.edu.com.cadastrolegal.model.PessoaDto;
import fja.edu.com.cadastrolegal.repository.PessoaRepository;

public class LinhaAdapter  extends BaseAdapter {
    private static LayoutInflater layoutInflater = null;
    List<PessoaDto> pessoas = new ArrayList<PessoaDto>();
    PessoaRepository pessoaRepository;
    ConsultarActivity consultar;

    public LinhaAdapter(ConsultarActivity consultarActivity, List<PessoaDto> pessoaDtos) {
        this.pessoas = pessoaDtos;
        this.consultar = consultarActivity;
        layoutInflater = (LayoutInflater) this.consultar.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        pessoaRepository = new PessoaRepository(consultar);
    }

    @Override
    public int getCount() {
        return this.pessoas.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final View viewLinha = layoutInflater.inflate(R.layout.activy_linha,null);

        TextView txtCodigo = (TextView)viewLinha.findViewById(R.id.linLblCodigo);
        TextView txtNome = (TextView)viewLinha.findViewById(R.id.linLblNome);
        TextView txtEndereco = (TextView)viewLinha.findViewById(R.id.linLblEndereco);
        TextView txtSexo = (TextView)viewLinha.findViewById(R.id.linLblSexo);
        TextView txtEstadoCivil = (TextView)viewLinha.findViewById(R.id.linLblEstadoCivil);
        TextView txtDtNascimento = (TextView)viewLinha.findViewById(R.id.linLblDtNascimento);
        TextView txtAtivo = (TextView)viewLinha.findViewById(R.id.linLblAtivo);

        Button btnDeletar = (Button)viewLinha.findViewById(R.id.linBtnDeletar);
        Button btnEditar = (Button)viewLinha.findViewById(R.id.linBtnEditar);

        txtCodigo.setText(pessoas.get(position).getCodigo());
        txtNome.setText(pessoas.get(position).getNome());
        txtEndereco.setText(pessoas.get(position).getEndereco());
        if(pessoas.get(position).getSexo().equalsIgnoreCase("M")){
            txtSexo.setText(String.valueOf(R.string.masculino));
        }else{
            txtSexo.setText(String.valueOf(R.string.feminino));
        }
        txtEstadoCivil.setText(this.GetEstadoCivil(pessoas.get(position).getEstadocivil()));

        txtDtNascimento.setText(pessoas.get(position).getDatanascimento());
        if(pessoas.get(position).getAtivo().equalsIgnoreCase("1")){
            txtAtivo.setText(String.valueOf(R.string.registroativo));
        }else{
            txtAtivo.setText(String.valueOf(R.string.registroinativo));
        }

        //Evento dos Botões...
        btnDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pessoaRepository.excluir(pessoas.get(position).getCodigo());
                Toast.makeText(consultar, String.valueOf(R.string.registrodeletado),Toast.LENGTH_LONG).show();
                pessoas.clear();
                pessoas = pessoaRepository.getTodos();
                notifyDataSetChanged();
            }
        });

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Criar a tela de editar.....
            }
        });

        return null;
    }

    private String GetEstadoCivil(String cod){
        if(cod.equalsIgnoreCase("S")){
            return String.valueOf(R.string.solteiro);
        }else if(cod.equalsIgnoreCase("V")){
            return String.valueOf(R.string.viuvo);
        }else if(cod.equalsIgnoreCase("C")){
            return String.valueOf(R.string.casado);
        }else{
            return String.valueOf(R.string.divorciado);
        }
    }
}
