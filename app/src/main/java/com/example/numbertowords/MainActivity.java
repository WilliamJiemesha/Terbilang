package com.example.numbertowords;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity{

    Handler myHandler = new Handler();
    Runnable WorkingRunnable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText NumberInsertBox = (EditText) findViewById(R.id.NumberInserted);
        NumberInsertBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Normal textchange method
                NumberInsertBox.removeTextChangedListener(this);
                try {
                    EditText numbers = (EditText) findViewById(R.id.NumberInserted);
                    String SimpanAngka = numbers.getText().toString().replace(",", "");
                    Double TestingOnlyNotUsed = Double.parseDouble(SimpanAngka);
                    if (SimpanAngka.length() > 3) {
                        String ProcessingNumbers = SimpanAngka;
                        String HasilAkhirnya = "";
                        Integer Penghitung = 0;
                        for (int i = ProcessingNumbers.length() - 1; i >= 0; i--) {
                            Penghitung++;
                            if (Penghitung % 3 == 0) {
                                if (i != 0) {
                                    HasilAkhirnya = "," + ProcessingNumbers.charAt(i) + HasilAkhirnya;
                                } else {
                                    HasilAkhirnya = ProcessingNumbers.charAt(i) + HasilAkhirnya;
                                }
                            } else {
                                HasilAkhirnya = ProcessingNumbers.charAt(i) + HasilAkhirnya;
                            }
                        }
                        boolean focussed = NumberInsertBox.hasFocus();
                        if (focussed) {
                            NumberInsertBox.clearFocus();
                        }
                        NumberInsertBox.setText(HasilAkhirnya);
                        if (focussed) {
                            NumberInsertBox.requestFocus();
                            int pos = NumberInsertBox.getText().length();
                            NumberInsertBox.setSelection(pos);
                        }
                    } else {
                        boolean focussed = NumberInsertBox.hasFocus();
                        if (focussed) {
                            NumberInsertBox.clearFocus();
                        }
                        NumberInsertBox.setText(SimpanAngka);
                        if (focussed) {
                            NumberInsertBox.requestFocus();
                            int pos = NumberInsertBox.getText().length();
                            NumberInsertBox.setSelection(pos);
                        }
                    }
                } catch (Exception ex) {

                }
                NumberInsertBox.addTextChangedListener(this);
//Start Delay

                timer.cancel();
                timer = new Timer();
                timer.schedule(
                        new TimerTask() {
                            @Override
                            public void run() {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            EditText numbers = (EditText) findViewById(R.id.NumberInserted);
                                            String AngkaHasil = numbers.getText().toString().replace(",", "");
                                            TextView Results = (TextView) findViewById(R.id.ResultText);
                                            try {
                                                Long TestingOnly = Long.parseLong(AngkaHasil);
                                                String ResultString = ProcessingRecursive(AngkaHasil).replace("SeRibu", "Seribu");
                                                Results.setText(ResultString);
                                            } catch (Exception ex) {
                                                Results.setText(" ");
                                            }
                                        } catch (Exception ex) {

                                        }
                                    }
                                });

                            }
                        }, DelayTime
                );
//End Delay
            }

            private Timer timer = new Timer();
            private int DelayTime = 1000;

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    public String Ikutannya(Integer A) {
        if (A >= 13) {
            return "Trilliun";
        } else if (A >= 10) {
            return "Miliar";
        } else if (A >= 7) {
            return "Juta";
        } else if (A >= 4) {
            return "Ribu";
        } else {
            return "";
        }
    }

    public String ProcessNumbersToStringSatuanLagi(Integer A) {
        if (A == 1) {
            return "Se";
        } else if (A == 2) {
            return "Dua ";
        } else if (A == 3) {
            return "Tiga ";
        } else if (A == 4) {
            return "Empat ";
        } else if (A == 5) {
            return "Lima ";
        } else if (A == 6) {
            return "Enam ";
        } else if (A == 7) {
            return "Tujuh ";
        } else if (A == 8) {
            return "Delapan ";
        } else if (A == 9) {
            return "Sembilan ";
        } else {
            return "";
        }
    }

    public String ProcessNumbersToStringSatuan(Integer A) {
        if (A == 1) {
            return "Satu";
        } else if (A == 2) {
            return "Dua";
        } else if (A == 3) {
            return "Tiga";
        } else if (A == 4) {
            return "Empat";
        } else if (A == 5) {
            return "Lima";
        } else if (A == 6) {
            return "Enam";
        } else if (A == 7) {
            return "Tujuh";
        } else if (A == 8) {
            return "Delapan";
        } else if (A == 9) {
            return "Sembilan";
        } else {
            return "";
        }
    }

    public String ProcessingRecursive(String B) {
        try {
            Integer A = B.length();
            if (A > 3) {
                if (A % 3 != 0) {
                    if (A % 3 == 1) {
                        if (A == 4) {
                            Character simpang0 = B.charAt(0);
                            return ProcessNumbersToStringSatuanLagi(Integer.parseInt(simpang0.toString())) + Ikutannya(A) + " " + ProcessingRecursive(B.substring(1));
                        } else {
                            Character simpang0 = B.charAt(0);
                            return ProcessNumbersToStringSatuan(Integer.parseInt(simpang0.toString())) + " " + Ikutannya(A) + " " + ProcessingRecursive(B.substring(1));
                        }
                    } else {
                        Character simpan0 = B.charAt(0);
                        Character simpan1 = B.charAt(1);
                        return " " + ProcessNumbersToStringPuluhan(Integer.parseInt(simpan0.toString()), Integer.parseInt(simpan1.toString())) + " " + Ikutannya(A) + " " + ProcessingRecursive(B.substring(2));
                    }
                } else {
                    Character simpan0 = B.charAt(0);
                    Character simpan1 = B.charAt(1);
                    Character simpan2 = B.charAt(2);
                    String nol = simpan0.toString();
                    String satu = simpan1.toString();
                    String dua = simpan2.toString();
                    if (nol.equals("0") && satu.equals("0") && !"0".equals(dua)) {
                        return ProcessNumbersToStringSatuanLagi(Integer.parseInt(simpan2.toString())) + Ikutannya(A) + ProcessingRecursive(B.substring(3));
                    } else if (nol.equals("0") && satu.equals("0") && dua.equals("0")) {
                        return "" + ProcessingRecursive(B.substring(3));
                    } else {
                        return ProcessNumbersToStringRatusan(Integer.parseInt(simpan0.toString())) + " " + ProcessNumbersToStringPuluhan(Integer.parseInt(simpan1.toString()), Integer.parseInt(simpan2.toString())) + " " + Ikutannya(A) + " " + ProcessingRecursive(B.substring(3));
                    }
                }
            } else {
                if (A == 2) {
                    Character simpan0 = B.charAt(0);
                    Character simpan1 = B.charAt(1);
                    return ProcessNumbersToStringPuluhan(Integer.parseInt(simpan0.toString()), Integer.parseInt(simpan1.toString()));
                } else if (A == 1) {
                    Character simpan0 = B.charAt(0);
                    return ProcessNumbersToStringSatuan(Integer.parseInt(simpan0.toString()));

                } else {
                    Character simpan0 = B.charAt(0);
                    Character simpan1 = B.charAt(1);
                    Character simpan2 = B.charAt(2);
                    return ProcessNumbersToStringRatusan(Integer.parseInt(simpan0.toString())) + " " + ProcessNumbersToStringPuluhan(Integer.parseInt(simpan1.toString()), Integer.parseInt(simpan2.toString())) + Ikutannya(A);
                }
            }
        } catch (Exception ex) {
            return "...Wrong Input";
        }
    }

    public String ProcessNumbersToStringPuluhan(Integer A, Integer B) {
        if (A == 1) {
            return ProcessNumbersToStringBelasan(B);
        } else if (A == 2) {
            return "Dua Puluh " + ProcessNumbersToStringSatuan(B);
        } else if (A == 3) {
            return "Tiga Puluh " + ProcessNumbersToStringSatuan(B);
        } else if (A == 4) {
            return "Empat Puluh " + ProcessNumbersToStringSatuan(B);
        } else if (A == 5) {
            return "Lima Puluh " + ProcessNumbersToStringSatuan(B);
        } else if (A == 6) {
            return "Enam Puluh " + ProcessNumbersToStringSatuan(B);
        } else if (A == 7) {
            return "Tujuh Puluh " + ProcessNumbersToStringSatuan(B);
        } else if (A == 8) {
            return "Delapan Puluh " + ProcessNumbersToStringSatuan(B);
        } else if (A == 9) {
            return "Sembilan Puluh " + ProcessNumbersToStringSatuan(B);
        } else {
            return ProcessNumbersToStringSatuan(B);
        }
    }

    public String ProcessNumbersToStringBelasan(Integer A) {
        if (A == 1) {
            return "Sebelas";
        } else if (A == 2) {
            return "Dua Belas";
        } else if (A == 3) {
            return "Tiga Belas";
        } else if (A == 4) {
            return "Empat Belas";
        } else if (A == 5) {
            return "Lima Belas";
        } else if (A == 6) {
            return "Enam Belas";
        } else if (A == 7) {
            return "Tujuh Belas";
        } else if (A == 8) {
            return "Delapan Belas";
        } else if (A == 9) {
            return "Sembilan Belas";
        } else {
            return "Sepuluh";
        }
    }

    public String ProcessNumbersToStringRatusan(Integer A) {
        if (A == 1) {
            return "Seratus";
        } else if (A == 2) {
            return "Dua Ratus";
        } else if (A == 3) {
            return "Tiga Ratus";
        } else if (A == 4) {
            return "Empat Ratus";
        } else if (A == 5) {
            return "Lima Ratus";
        } else if (A == 6) {
            return "Enam Ratus";
        } else if (A == 7) {
            return "Tujuh Ratus";
        } else if (A == 8) {
            return "Delapan Ratus";
        } else if (A == 9) {
            return "Sembilan Ratus";
        } else {
            return "";
        }
    }

}
