package br.ufjf.dcc196.izabel.calculapreco;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText editTextPreco;
    private TextView textViewPrecoFinal;
    private CheckBox checkBoxParaPresente;
    private CheckBox checkBoxenvioExpresso;
    private RadioGroup radioGroupPagamento;
    private EditText editTextPeso;
    private TextView textViewvalorFrete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextPreco = findViewById(R.id.editTextPreco);
        textViewPrecoFinal = findViewById(R.id.textViewPrecoFinal);
        checkBoxParaPresente = findViewById(R.id.checkBoxEmbrulhaPresente);
        checkBoxenvioExpresso = findViewById(R.id.checkBoxEnvioExpresso);
        radioGroupPagamento = findViewById(R.id.radioGroupPagamento);
        editTextPeso = findViewById(R.id.editTextPeso);
        textViewvalorFrete = findViewById(R.id.textViewFrete);


        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcular(v);
            }
        };
        checkBoxParaPresente.setOnClickListener(listener);
        checkBoxenvioExpresso.setOnClickListener(listener);
    }

    public void calcular (View origin){

        Double preco = 0.0;
        Double peso = 0.0;
        Double valorFrete = 0.0;

        try {
            preco = Double.parseDouble(editTextPreco.getText().toString());
            peso = Double.parseDouble(editTextPeso.getText().toString());
        } catch (Exception e){
            editTextPreco.requestFocus();
        }

        Double precoFinal = preco;

        if(checkBoxParaPresente.isChecked()){
            precoFinal += 5.00;
        }

        if(checkBoxenvioExpresso.isChecked()){
            valorFrete += 10.00;
        }

        switch (radioGroupPagamento.getCheckedRadioButtonId()){

            case R.id.radioButton1xCartao:
                precoFinal += 0.03*preco;
                break;

            case R.id.radioButton3xCartao:
                precoFinal += 0.06*preco;
                break;

            case R.id.radioButton6xCartao:
                precoFinal += 0.09*preco;
                break;
        }

        valorFrete += 10.00 + 4.00 * peso;
        precoFinal += valorFrete;

        Locale locale = new Locale("pt", "BR");

        textViewvalorFrete.setText("Valor do frete: " + NumberFormat.getCurrencyInstance(locale).format(valorFrete));
        textViewPrecoFinal.setText(NumberFormat.getCurrencyInstance(locale).format(precoFinal));
    }
}