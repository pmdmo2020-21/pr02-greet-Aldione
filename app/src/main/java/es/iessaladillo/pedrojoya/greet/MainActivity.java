package es.iessaladillo.pedrojoya.greet;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import es.iessaladillo.pedrojoya.greet.databinding.MainActivityBinding;

public class MainActivity extends AppCompatActivity {

    private MainActivityBinding binding;
    private int times;
    private String treatmentSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MainActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        treatmentSelected = (String) binding.treatmentMr.getText();

        setupViews();

    }

    private void setupViewsPremium() {
        if(binding.swtPremium.isChecked()){
            binding.progressBar.setVisibility(View.GONE);
            binding.lblCount.setVisibility(View.GONE);
        } else {
            binding.progressBar.setVisibility(View.VISIBLE);
            binding.lblCount.setVisibility(View.VISIBLE);
            times=0;
            showTimes();
        }


    }

    private void setupViews() {
        binding.swtPremium.setOnClickListener(v -> setupViewsPremium());
        binding.btnGreet.setOnClickListener(v -> incrementAndShow());
        binding.treatmentSelect.setOnCheckedChangeListener((group, checkedId) -> selectTreatment(checkedId));
    }

    private void selectTreatment(int checkedId) {
        if(checkedId == binding.treatmentMr.getId()){
            binding.imgPhoto.setImageResource(R.drawable.ic_mr);
            treatmentSelected = (String) binding.treatmentMr.getText();
        } else if (checkedId == binding.treatmentMrs.getId()){
            binding.imgPhoto.setImageResource(R.drawable.ic_mrs);
            treatmentSelected = (String) binding.treatmentMrs.getText();
        } else {
            binding.imgPhoto.setImageResource(R.drawable.ic_ms);
            treatmentSelected = (String) binding.treatmentMs.getText();
        }



    }

    private void incrementAndShow() {
        if(times < 10 && !binding.swtPremium.isChecked() && validateTxt()){
            times++;
            showTimes();
            showGreet();
        } else if (binding.swtPremium.isChecked() && validateTxt()){
            showGreet();
        } else if(binding.txtName.getText().length() != 0 && validateTxt()){
            buyPremium();
        }
        
    }

    private boolean validateTxt() {
        if(binding.txtName.getText().length() == 0 || binding.txtSurname.getText().length() == 0 || !binding.txtName.getText().toString().matches("[a-zA-Z][a-zA-Z ]+") || !binding.txtSurname.getText().toString().matches("[a-zA-Z][a-zA-Z ]+")){
            return false;
        } else {
            return true;
        }
    }

    private void buyPremium() {
        binding.lblGreet.setText(String.valueOf("Buy premium subscription to go on greeting!!"));
    }

    private void showTimes() {
        binding.lblCount.setText(String.valueOf(times+" of 10"));
        binding.progressBar.setProgress(times);

    }

    private void showGreet() {
        if(binding.chkPolitely.isChecked()){
            binding.lblGreet.setText(String.valueOf("Good morning "+treatmentSelected+" "+binding.txtName.getText()+" "+binding.txtSurname.getText()+".\nPleased to meet you"));
        } else {
            binding.lblGreet.setText(String.valueOf("Hello "+binding.txtName.getText()+" "+binding.txtSurname.getText()+ ". What's up?"));
        }

    }


}