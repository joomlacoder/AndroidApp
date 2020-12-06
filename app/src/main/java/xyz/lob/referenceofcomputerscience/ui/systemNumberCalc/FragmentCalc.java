package xyz.lob.referenceofcomputerscience.ui.systemNumberCalc;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import xyz.lob.referenceofcomputerscience.App;
import xyz.lob.referenceofcomputerscience.R;

public class FragmentCalc extends Fragment {
    String i3;
    String i2;
    String i1;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View calcView = inflater.inflate(R.layout.fragment_calc, container, false);

        TextView h1 = calcView.findViewById(R.id.textView13);
        h1.setText("Из десятичной в :");
        TextView tv1 = calcView.findViewById(R.id.textView14);
        TextView tv2 = calcView.findViewById(R.id.textView16);
        TextView tv3 = calcView.findViewById(R.id.textView18);
        String[] text = App.getInstance().getResources().getStringArray(R.array.num);
        tv1.setText(text[0]);
        tv2.setText(text[1]);
        tv3.setText(text[2]);

        EditText editText = calcView.findViewById(R.id.editTextNumber2);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                TextView iv1 = calcView.findViewById(R.id.textView15);
                TextView iv2 = calcView.findViewById(R.id.textView17);
                TextView iv3 = calcView.findViewById(R.id.textView19);
                calc(Integer.parseInt(s.toString()));
                iv1.setText(i1);
                iv2.setText(i2);
                iv3.setText(i3);
            }
        });
//        editText.setOnKeyListener((v, keyCode, event) -> {
//            if (event.getAction() == KeyEvent.ACTION_DOWN &&
//                    (keyCode == KeyEvent.KEYCODE_ENTER)) {
//                // сохраняем текст, введенный до нажатия Enter в переменную
//                calc(Integer.parseInt(editText.getText().toString()));
//                return true;
//            }
//            return false;
//        });


        return calcView;
    }

    private void calc(int num) {
        i1 = Integer.toString(num, 2);
        i2 = Integer.toString(num, 8);
        i3 = Integer.toString(num, 16);
    }
}
