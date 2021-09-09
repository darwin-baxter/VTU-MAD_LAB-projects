package com.example.calculator_091;
//Mohit Gupta 1CR18IS091
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button zero, one, two, three, four, five, six, seven, eight, nine;
    Button add, sub, mul, div, mod, pow;
    Button clear, equal, dot;

    TextView input;

    String operand1 = "";
    String operand2 = "";
    String operator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        zero = (Button)findViewById(R.id.zero);
        one = (Button)findViewById(R.id.one);
        two = (Button)findViewById(R.id.two);
        three = (Button)findViewById(R.id.three);
        four = (Button)findViewById(R.id.four);
        five = (Button)findViewById(R.id.five);
        six = (Button)findViewById(R.id.six);
        seven = (Button)findViewById(R.id.seven);
        eight = (Button)findViewById(R.id.eight);
        nine = (Button)findViewById(R.id.nine);
        add = (Button)findViewById(R.id.add);
        sub = (Button)findViewById(R.id.sub);
        mul = (Button)findViewById(R.id.mul);
        div = (Button)findViewById(R.id.div);
        mod = (Button)findViewById(R.id.mod);
        pow = (Button)findViewById(R.id.pow);
        dot = (Button)findViewById(R.id.dot);
        clear = (Button)findViewById(R.id.clear);
        equal = (Button)findViewById(R.id.equal);
        input = (TextView)findViewById(R.id.input);

        // Event listeners for operands
        zero.setOnClickListener(this::setValue);
        one.setOnClickListener(this::setValue);
        two.setOnClickListener(this::setValue);
        three.setOnClickListener(this::setValue);
        four.setOnClickListener(this::setValue);
        five.setOnClickListener(this::setValue);
        six.setOnClickListener(this::setValue);
        seven.setOnClickListener(this::setValue);
        eight.setOnClickListener(this::setValue);
        nine.setOnClickListener(this::setValue);
        dot.setOnClickListener(this::setValue);

        // Event listener for operators
        add.setOnClickListener(this::setOperator);
        sub.setOnClickListener(this::setOperator);
        mul.setOnClickListener(this::setOperator);
        div.setOnClickListener(this::setOperator);
        mod.setOnClickListener(this::setOperator);
        pow.setOnClickListener(this::setOperator);

        // Event listener for AC
        clear.setOnClickListener(this::clearAll);

        // Event listener for equal '='
        equal.setOnClickListener(this::evaluate);
    }


    public void setValue(View v) {

        CharSequence text = ((Button)v).getText();
        if (operator.equals("")) { operand1 += text; }
        else { operand2 += text; }

        // Handle operands with only dot
        if (operand1.equals(".")) { operand1 = "0."; }
        if (operand2.equals(".")) { operand2 = "0."; }

        input.setText(input.getText() + "" + text);
    }

    public void setOperator(View v) {
        String tag = v.getTag().toString();
        CharSequence op = ((Button)v).getText();

        if (input.getText().length() != 0) {
            if (!operator.equals("")) { // If operator already exists
                if (!operand2.equals("")) {
                    evaluate(null);
                    operator = tag;
                }

            }
            input.setText(input.getText() + "" + op);
        }
        operator = tag;
    }

    public void clearAll(View v) {
        operator = "";
        operand1 = "";
        operand2 = "";
        input.setText("");
    }

    public void evaluate(View v) {
        double result = Double.parseDouble(operand1);

        switch (operator) {
            case "add":
                result = Double.parseDouble(operand1) + Double.parseDouble(operand2);
                break;

            case "sub":
                result = Double.parseDouble(operand1) - Double.parseDouble(operand2);
                break;

            case "mul":
                result = Double.parseDouble(operand1) * Double.parseDouble(operand2);
                break;

            case "div":
                result = Double.parseDouble(operand1) / Double.parseDouble(operand2);
                break;

            case "mod":
                result = Double.parseDouble(operand1) % Double.parseDouble(operand2);
                break;

            case "pow":
                Double num1 = Double.parseDouble(operand1);
                Double num2 = Double.parseDouble(operand2);
                result = Math.pow(num1, num2);
                break;
        }

        operand1 = (result%1 != 0) ? String.valueOf(result) : String.valueOf((int)result);
        input.setText(operand1);
        operator = "";
        operand2 = "";
    }
}
