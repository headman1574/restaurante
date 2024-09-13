package com.example.layout;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edtConta;
    private EditText edtPessoa;
    private EditText edtTaxa;
    private EditText edtCover;
    private TextView txtTotal;
    private TextView txtPorpessoa;
    private Button btCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        edtConta = findViewById(R.id.edtConta);
        edtCover = findViewById(R.id.edtCover);
        edtPessoa = findViewById(R.id.edtPessoa);
        edtTaxa = findViewById(R.id.edtTaxa);
        txtPorpessoa = findViewById(R.id.txtPorpessoa);
        txtTotal = findViewById(R.id.txtTotal);
        btCalcular = findViewById(R.id.btCalcular);


        btCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBill();
            }
        });
    }

    private void calculateBill() {
        try {
            //  aqui ele obtem o valor inserido
            double billAmount = Double.parseDouble(edtConta.getText().toString());
            double couvert = Double.parseDouble(edtCover.getText().toString());
            double serviceTax = Double.parseDouble(edtTaxa.getText().toString());
            int peopleCount = Integer.parseInt(edtPessoa.getText().toString());

            // aqui é opcional coloca se quiser
            if (peopleCount <= 0) {
                Toast.makeText(this, "Número de pessoas deve ser maior que zero!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Calcular a taxa de serviço
            double serviceAmount = (serviceTax / 100) * billAmount;

            // Calcular o total
            double totalAmount = billAmount + couvert + serviceAmount;

            // Calculo por pessoa
            double amountPerPerson = totalAmount / peopleCount;

            // resultados
            txtTotal.setText(String.format("R$ %.2f", totalAmount));
            txtPorpessoa.setText(String.format("R$ %.2f", amountPerPerson));

        } catch (NumberFormatException e) {
            //
            Toast.makeText(this, "Por favor, insira todos os valores corretamente!", Toast.LENGTH_SHORT).show();
        }
    }
}
