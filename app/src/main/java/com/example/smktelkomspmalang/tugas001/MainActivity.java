package com.example.smktelkomspmalang.tugas001;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    CheckedBox cbPL, cbPG, cbW;
    EditText etNama, etID;
    RadioGroup rgJK;
    Button bPinjam;
    TextView tvNama, tvID, tvJK, tvJK1, tvS, tvStatus, tvLB, tvMobil;
    Spinner spMobil;
    int nStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNama = (EditText) findViewById(R.id.editTextNama);
        etID = (EditText) findViewById(R.id.editTextID);
        bPinjam = (Button) findViewById(R.id.buttonPinjam);
        rgJK = (RadioGroup) findViewById(R.id.radioGroupJK);

        cbPL = (CheckBox) findViewById(R.id.checkBoxPL);
        cbPG = (CheckBox) findViewById(R.id.checkBoxPG);
        cbW = (CheckBox) findViewById(R.id.checkBoxW);

        tvNama = (TextView) findViewById(R.id.textViewNama);
        tvStatus = (TextView) findViewById(R.id.textViewStatus);
        tvS = (TextView) findViewById(R.id.textViewS);
        tvID = (TextView) findViewById(R.id.textViewID);
        tvJK = (TextView) findViewById(R.id.textViewJK);
        tvJK1 = (TextView) findViewById(R.id.textViewJK1);
        tvLB = (TextView) findViewById(R.id.textViewLB);
        tvMobil = (TextView) findViewById(R.id.textViewMobil);
        spMobil = (Spinner) findViewById(R.id.spinnerMobil);

        cbPL.setOnCheckedChangeListener(this);
        cbPG.setOnCheckedChangeListener(this);
        cbW.setOnCheckedChangeListener(this);

        bPinjam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doClick();

            }
        });
    }

    private void doClick() {
        //Status
        String hasil = "Status : \n";
        int startlen = hasil.length();
        if (cbPL.isChecked()) hasil += cbPL.getText() + "\n";
        if (cbPG.isChecked()) hasil += cbPG.getText() + "\n";
        if (cbW.isChecked()) hasil += cbW.getText() + "\n";

        if (hasil.length() == startlen) hasil += "Tidak Ada Pilihan";

        tvStatus.setText(hasil);

        //Jenis Kelamin
        String JK = null;
        if (rgJK.getCheckedRadioButtonId() != -1) {
            RadioButton rb = (RadioButton)
                    findViewById(rgJK.getCheckedRadioButtonId());
            JK = rb.getText().toString();
        }

        if (JK == null) {
            tvJK1.setText("Anda Belum Memilih Status");
        } else {
            tvJK.setText("Jenis Kelamin : " + JK);
        }
        if (isValid()) {
            String nama = etNama.getText().toString();
            String ID = etID.getText().toString();

            tvNama.setText("Nama      : " + nama);
            tvID.setText("ID KTP    : " + ID);
            tvMobil.setText("Mobil Yang Dipinjam: " + spMobil.getSelectedItem().toString());
        }
    }

    private boolean isValid() {
        boolean valid = true;

        String nama = etNama.getText().toString();
        String ID = etID.getText().toString();
        //Nama
        if (nama.isEmpty()) {
            etNama.setError("Nama Belum Diisi");
            valid = false;
        } else if (nama.length() < 3) {
            etNama.setError("Nama Minimal 3 Karakter");
            valid = false;
        } else {
            etNama.setError(null);
        }
        //ID KTP
        if (ID.isEmpty()) {
            etID.setError("ID KTP Belum Di isi");
            valid = false;
        } else if (ID.length() < 4) {
            etID.setError("ID Minimal 4 Digit");
            valid = false;
        } else {
            etID.setError(null);
        }

        return valid;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b) nStatus += 1;
        else nStatus -= 1;

        tvS.setText("Status : " + nStatus);
    }


}
