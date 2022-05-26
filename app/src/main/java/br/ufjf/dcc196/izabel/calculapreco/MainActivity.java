package br.ufjf.dcc196.izabel.calculapreco;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText editTextPreco;
    private TextView textViewPrecoFinal;
    private CheckBox checkBoxParaPresente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextPreco = findViewById(R.id.editTextPreco);
        textViewPrecoFinal = findViewById(R.id.textViewPrecoFinal);
        checkBoxParaPresente = findViewById(R.id.checkBoxEmbrulhaPresente);
    }

    public void calcular (View origin){
        Double preco = Double.parseDouble(editTextPreco.getText().toString());
        Double precoFinal = preco;

        if(checkBoxParaPresente.isChecked()){
            precoFinal += 5.00;
        }

        Locale locale = new Locale("pt", "BR");


        textViewPrecoFinal.setText(NumberFormat.getCurrencyInstance(locale).format(precoFinal));
    }
}