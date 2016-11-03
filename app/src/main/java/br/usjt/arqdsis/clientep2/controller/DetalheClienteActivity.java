package br.usjt.arqdsis.clientep2.controller;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

import br.usjt.arqdsis.clientep2.R;
import br.usjt.arqdsis.clientep2.controller.ListaClientesActivity;
import br.usjt.arqdsis.clientep2.model.Cliente;
import br.usjt.arqdsis.clientep2.model.ClienteRequester;
import br.usjt.arqdsis.clientep2.model.Util;

public class DetalheClienteActivity extends AppCompatActivity {
    ImageView clienteImageView;
    Cliente cliente;
    ClienteRequester requester;
    Bitmap imagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_cliente);
        Intent intent = getIntent();
        cliente = new Cliente(1, intent.getStringExtra(ListaClientesActivity.NOME),
                intent.getStringExtra(ListaClientesActivity.FONE),
                intent.getStringExtra(ListaClientesActivity.EMAIL));
        clienteImageView = (ImageView) findViewById(R.id.cliente_image_view);


        requester = new ClienteRequester();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    imagem = requester.getImage("http" +
                            "://10.0.2.2:8080/arqdesis_poetas/img/"+cliente.getFoto()+".jpg");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            clienteImageView.setImageBitmap(imagem);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();



        TextView nome = (TextView)findViewById(R.id.txt_cliente_nome);
        TextView fone = (TextView)findViewById(R.id.txt_cliente_fone);
        TextView email = (TextView)findViewById(R.id.txt_cliente_email);

        nome.setText(cliente.getNome());
        fone.setText(cliente.getFone());
        email.setText(cliente.getEmail());
    }
}
