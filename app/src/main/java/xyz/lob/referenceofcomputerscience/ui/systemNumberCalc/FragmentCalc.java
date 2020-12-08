package xyz.lob.referenceofcomputerscience.ui.systemNumberCalc;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.math.BigInteger;
import java.util.Map;
import java.util.TreeMap;

import xyz.lob.referenceofcomputerscience.R;

public class FragmentCalc extends Fragment {

    public static final String ARG_SYS = "sys";
    private Sys sys;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            sys = Sys.getInstance(getArguments().getInt(ARG_SYS));
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View calcView = inflater.inflate(R.layout.fragment_calc, container, false);

        TextView textView = calcView.findViewById(R.id.textView13);
        textView.setText(sys.getSysText());

        TextView tv1 = calcView.findViewById(R.id.textView14);
        TextView tv2 = calcView.findViewById(R.id.textView16);
        TextView tv3 = calcView.findViewById(R.id.textView18);
        String[] text = sys.getCalcSysText();
        tv1.setText(text[0]);
        tv2.setText(text[1]);
        tv3.setText(text[2]);

        EditText editText = calcView.findViewById(R.id.editTextNumber2);
        if(sys.sys == 16){
            editText.setInputType(InputType.TYPE_CLASS_TEXT);
        }
        TableLayout tableLayout = (TableLayout) calcView.findViewById(R.id.calcTable);
        tableLayout.setColumnShrinkable(1,true);

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
                String[] arr;
                Log.e("crashh", s.toString());
                if(s.length() > 0)
                    arr = sys.getCalcNumber(s.toString());
                else
                    arr =  sys.getCalcNumber(Integer.toString(0));
                iv1.setText(arr[0]);
                iv2.setText(arr[1]);
                iv3.setText(arr[2]);
            }
        });

        return calcView;
    }

    @SuppressWarnings("unused")
    public static FragmentCalc newInstance(int sys) {
        FragmentCalc fragment = new FragmentCalc();
        Bundle args = new Bundle();
        args.putInt(ARG_SYS, sys);
        fragment.setArguments(args);
        return fragment;
    }

    private enum Sys{
        BINARY(2),
        OCTAL(8),
        DESIMAL(10),
        HEX(16);


        static Sys getInstance(int i){
            Sys sys = null;
            for(Sys val : Sys.values())
                sys = val.sys == i? val : sys;
            return sys;
        }

        Sys(int sys){
            this.sys = sys;
        }

        private int sys;
        private Map<Sys, String> calcSysText = new TreeMap<>();
        private Map<Sys, String> calcSysNum = new TreeMap<>();

        private void init() {
            calcSysText.clear();
            calcSysText.put(BINARY, "Двоичная");
            calcSysText.put(OCTAL, "Восмиричная");
            calcSysText.put(DESIMAL, "Десятична");
            calcSysText.put(HEX, "Шестнадцатиричная");
        }

        private void calc(BigInteger num){
            calcSysNum.clear();
            calcSysNum.put(BINARY, num.toString(BINARY.sys));
            calcSysNum.put(OCTAL, num.toString(OCTAL.sys));
            calcSysNum.put(DESIMAL, num.toString(DESIMAL.sys));
            calcSysNum.put(HEX, num.toString(HEX.sys));
        }

        public String getSysText(){
            return "Перевод из " + sys + " системы";
        }

        public String[] getCalcSysText(){
            init();
            calcSysText.remove(this);
            String[] text = new String[3];
            int i = 0;
            for(Map.Entry<Sys, String> entry : calcSysText.entrySet()){
                text[i++] = entry.getValue();
            }
            return text;
        }

        public String[] getCalcNumber(String num){
            BigInteger integer = new BigInteger(num, sys);
            calc(integer);
            calcSysNum.remove(this);
            String[] text = new String[3];
            int i = 0;
            for(Map.Entry<Sys, String> entry : calcSysNum.entrySet()){
                text[i++] = entry.getValue();
            }
            return text;
        }
    }

}
