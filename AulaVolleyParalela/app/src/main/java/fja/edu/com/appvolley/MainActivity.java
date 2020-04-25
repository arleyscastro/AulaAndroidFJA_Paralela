package fja.edu.com.appvolley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
/*
 [
  { "id": "12",
    "nome": "Roberto Carlos",
    "email": "roberto@mpb.com.br",
    "sexo": "M"
  },
  { "id": "34",
    "nome": "Maria Betânia",
    "email": "maria@mpb.com.br",
    "sexo": "f"
  },
  { "id": "56",
    "nome": "Tom Jobim",
    "email": "tom@mpb.com.br",
    "sexo": "M"
  },
  { "id": "78",
    "nome": "Noel Rosa",
    "email": "noel@mpb.com.br",
    "sexo": "M"
  }
]
*/
    private Button btOk;
    private TextView lblResult;

    private String url = "http://www.mocky.io/v2/5ea41aa34f0000dd4cd9fa67";

    //Variáveis e objetos do Volley
    private RequestQueue meuQue;
    private StringRequest sr;
    private JsonArrayRequest jsAr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btOk = (Button)findViewById(R.id.btnConectar);
        lblResult = (TextView)findViewById(R.id.lblResultado);

        btOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Metodos de acesso
                //enviaRecebeSimples();
                enviaRecebeArray();
            }
        });
    }


    private void enviaRecebeSimples(){
        meuQue = Volley.newRequestQueue(this);
        sr = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        lblResult.setText(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        meuQue.add(sr);
    }

    private void enviaRecebeArray(){
        meuQue = Volley.newRequestQueue(this);

        jsAr = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            StringBuilder txt = new StringBuilder();
                            for (int i=0; i < response.length(); i++ ){
                                JSONObject jobj = response.getJSONObject(i);
                                int id = jobj.getInt("id");
                                String nome = jobj.getString("nome");
                                String email = jobj.getString("email");
                                String sexo = jobj.getString("sexo");

                                txt.append("ID     :").append(id).append("\n");
                                txt.append("NOME   :").append(nome).append("\n");
                                txt.append("E-MAIL :").append(email).append("\n");
                                if(sexo.equalsIgnoreCase("M")){
                                    txt.append("SEXO    :").append("Masculino").append("\n");
                                }else{
                                    txt.append("SEXO    :").append("Feminino").append("\n");
                                }
                                txt.append("---------------------------------------------\n");
                            }
                            lblResult.setText(txt.toString());

                        } catch (JSONException e){
                            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );

        meuQue.add(jsAr);
    }

    private void chamaDelete(){
        meuQue = Volley.newRequestQueue(this);

        sr = new StringRequest(
                Request.Method.DELETE,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Código .......

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        meuQue.add(sr);
    }

}
