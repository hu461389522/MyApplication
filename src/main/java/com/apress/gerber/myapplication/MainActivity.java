package com.apress.gerber.myapplication;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static java.lang.Double.parseDouble;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    Button btn_0;
    Button btn_1;//1数字按钮
    Button btn_2;//2数字按钮
    Button btn_3;//3数字按钮
    Button btn_4;//4数字按钮
    Button btn_5;//5数字按钮
    Button btn_6;//6数字按钮
    Button btn_7;//7数字按钮
    Button btn_8;//8数字按钮
    Button btn_9;//9数字按钮
    Button btn_clear;//清楚按钮
    Button btn_del;//删除按钮
    Button btn_plus;//加号按钮
    Button btn_minus;//减号按钮
    Button btn_multiply;//乘号数字按钮
    Button btn_divide;//除号按钮
    Button btn_equal;//等于按钮
    Button btn_point;//小数点按钮
    EditText et_input;//显示输入内容显示屏
    boolean clear_flag;//清空标示

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_0 = (Button) findViewById(R.id.btn_0);
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_6 = (Button) findViewById(R.id.btn_6);
        btn_7 = (Button) findViewById(R.id.btn_7);
        btn_8 = (Button) findViewById(R.id.btn_8);
        btn_9 = (Button) findViewById(R.id.btn_9);
        btn_point = (Button) findViewById(R.id.btn_point);
        btn_clear = (Button) findViewById(R.id.btn_clear);
        btn_del = (Button) findViewById(R.id.btn_del);
        btn_plus = (Button) findViewById(R.id.btn_plus);
        btn_divide = (Button) findViewById(R.id.btn_divide);
        btn_minus = (Button) findViewById(R.id.btn_minus);
        btn_multiply = (Button) findViewById(R.id.btn_multiply);
        btn_equal = (Button) findViewById(R.id.btn_equal);
        //以上是实例化按钮

        et_input = (EditText) findViewById(R.id.et_input);//实例化显示屏

        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_point.setOnClickListener(this);
        btn_plus.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_multiply.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        btn_equal.setOnClickListener(this);
        //设置按钮的点击事件


    }

    @Override
    public void onClick(View v) {
        String str = et_input.getText().toString();
        switch (v.getId()) {
            case R.id.btn_0:
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
            case R.id.btn_point:
                if (clear_flag) {
                    clear_flag = false;
                    str = "";
                    et_input.setText("");
                }
                et_input.setText(str + ((Button) v).getText());
                break;
            case R.id.btn_plus:
            case R.id.btn_minus:
            case R.id.btn_divide:
            case R.id.btn_multiply:
                if (clear_flag) {
                    clear_flag = false;
                    str = "";
                    et_input.setText("");
                }
                et_input.setText(str + " " + ((Button) v).getText() + " ");
                break;
            case R.id.btn_clear:
                clear_flag = false;
                et_input.setText("");
                break;
            case R.id.btn_del:
                if (clear_flag) {
                    clear_flag = false;
                    et_input.setText("");
                } else if (str != null && !str.equals("")) {
                    et_input.setText(str.substring(0, str.length() - 1));
                }
                break;
            case R.id.btn_equal:
                getResult();
                break;
        }
    }

    //单独运算结果
    private void getResult() {
        String exp = et_input.getText().toString();

        if (exp == null || exp.equals("")) {
            return;
        }
        if (!exp.contains(" ")) {
            return;
        }
        if (clear_flag) {
            clear_flag = false;
            return;
        }
        double result = 0;
        clear_flag = true;
        String s1 = exp.substring(0, exp.indexOf(" "));//s1用于保存第一个运算数
        String op = exp.substring(exp.indexOf(" ") + 1, exp.indexOf(" ") + 2);//op用于保存运算符
        String s2 = exp.substring(exp.indexOf(" ") + 3);//s2用于保存第二个运算数

        if (!s1.equals("") && !s2.equals("")) {
            double d1 = parseDouble(s1);
            double d2 = parseDouble(s2);
            if (op.equals("＋")) {
                result = d1 + d2;
            } else if (op.equals("－")) {
                result = d1 - d2;
            } else if (op.equals("×")) {
                result = d1 * d2;
            } else if (op.equals("÷")) {
                if (d2 == 0) {
                    result = 0;
                } else {
                    result = d1 / d2;
                }
            }

            if (!s1.contains(".") && !s2.contains(".") && !op.equals("÷")) {
                int r = (int) result;
                et_input.setText(r + "");

            } else {
                et_input.setText(result + "");
            }
        } else if (!s1.equals("") && s2.equals("")) {
            et_input.setText(exp);
        } else if (s1.equals("") && !s2.equals("")) {
            double d2 = parseDouble(s2);
            if (op.equals("＋")) {

                result = 0 + d2;
            } else if (op.equals("－")) {
                result = 0 - d2;
            } else if (op.equals("×")) {
                result = 0;
            } else if (op.equals("÷")) {
                result = 0;
            }
            if (!s2.contains(".")) {
                int r = (int) result;
                et_input.setText(r + "");

            } else {
                et_input.setText(result + "");
            }
        } else {
            et_input.setText("");
        }

        return;
    }

}
