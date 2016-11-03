package br.usjt.arqdsis.clientep2.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;
import java.util.ArrayList;

import br.usjt.arqdsis.clientep2.R;
import br.usjt.arqdsis.clientep2.model.Cliente;
import br.usjt.arqdsis.clientep2.model.ClienteRequester;

public class MainActivity extends AppCompatActivity {
    private EditText nome;
    public static final String CHAVE = "br.usjt.arqdesis.clientep2.chave";
    public static final String LISTA = "br.usjt.arqdesis.clientep2.lista";
    ClienteRequester requester;
    ArrayList<Cliente> lista;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nome = (EditText)findViewById(R.id.busca_nome_cliente);

    }

    public void buscarClientes(View view){
        requester = new ClienteRequester();
        intent = new Intent(this, ListaClientesActivity.class);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lista = requester.getClientes("http://10.0.2.2:8080/arqdesis_poetas/cliente");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String chave = nome.getText().toString();
                        intent.putExtra(CHAVE, chave);
                        intent.putExtra(LISTA, lista);
                        startActivity(intent);
                    }
                });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
