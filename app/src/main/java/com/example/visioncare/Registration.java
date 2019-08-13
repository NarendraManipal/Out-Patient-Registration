package com.example.visioncare;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.threeten.bp.format.DateTimeFormatter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

//import android.app.ProgressDialog;

public class Registration extends AppCompatActivity {

    private EditText et_pin, et_place, et_state, et_country, et_year, et_month, et_day, et_date, et_time, et_username, et_parentName, et_address, et_phoneM, et_phoneR, et_DOB;
    private Spinner spinner1, spinner2, spinner3, spinner4;
    private String pincode, pincodeResponse, district, state, country, post, dayOfyear, monthOfYear, yearNumber, op_time, op_date, user, parentName, op_address, op_phoneM,op_DOB, op_phoneR;
    String opDOB, opdate, optime;

    //Declaration for DOB
    Calendar cal;
    DatePickerDialog datePickerDialog;
    int day, month, year;

    final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Button btn_registration = findViewById(R.id.btn_registration);

        et_DOB = findViewById(R.id.et_DOB);
        et_year = findViewById(R.id.et_year);
        et_month = findViewById(R.id.et_month);
        et_day = findViewById(R.id.et_day);
        et_date = findViewById(R.id.et_date);
        et_time = findViewById(R.id.et_time);
        et_place = findViewById(R.id.et_place);
        et_state = findViewById(R.id.et_state);
        et_country = findViewById(R.id.et_country);
        et_pin = findViewById(R.id.et_pin);
        et_username = findViewById(R.id.et_username);
        et_parentName = findViewById(R.id.et_parentName);
        et_address = findViewById(R.id.et_address);
        et_phoneM = findViewById(R.id.et_phoneM);
        et_phoneR = findViewById(R.id.et_phoneR);

        spinner2 = findViewById(R.id.spinner2);
        spinner4 = findViewById(R.id.spinner4);

        et_month.setFilters(new InputFilter[]{new MinMaxFilter("0", "11")});
        et_day.setFilters(new InputFilter[]{new MinMaxFilter("0", "31")});

        //date display
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        et_date.setText(dateFormat.format(new Date()));

        //Time display
        @SuppressLint("SimpleDateFormat") SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss aa");
        et_time.setText(timeFormat.format(new Date()));

        spinner1 = findViewById(R.id.spinner1);
        spinner3 = findViewById(R.id.spinner3);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int genderIndex = spinner1.getSelectedItemPosition();
                    spinner3.setSelection(genderIndex);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        et_pin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                pincodeCalculation();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        et_DOB.setOnClickListener(view -> dob());

        et_DOB.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String dateOfBirth = et_DOB.getText().toString();
                //Toast.makeText(getApplicationContext(), dateOfBirth, Toast.LENGTH_LONG).show();

                getAge(dateOfBirth);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btn_registration.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                registrationActivity();
            }
        });

    }

    //Method for pincode
    public void pincodeCalculation()
    {
        pincode = et_pin.getText().toString();

        Retrofit retrofitPincode = new Retrofit.Builder()
                .baseUrl(PinCodeApi.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(okHttpClient)
                .build();

        PinCodeApi pinCodeApi = retrofitPincode.create(PinCodeApi.class);

        Call<String> callPincode = pinCodeApi.getPostOffice(pincode);

        callPincode.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> callPincode, Response<String> responsePincode) {
                pincodeResponse = responsePincode.body();

                try {
                    JSONArray jsonArray = new JSONArray(pincodeResponse);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);

                    post = jsonObject.getString("PostOffice");
                    JSONArray postArray = new JSONArray(post);
                    JSONObject postObject = postArray.getJSONObject(0);

                    district = postObject.getString("District");
                    state = postObject.getString("State");
                    country = postObject.getString("Country");

                    et_place.setText(district);
                    et_state.setText(state);
                    et_country.setText(country);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> callPincode, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    //Method for Date of Birth
    public void dob()
    {
        cal = Calendar.getInstance();

        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);

        datePickerDialog = new DatePickerDialog(Registration.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int nYear, int nMonth, int nDay) {
                String dayString = String.valueOf(nDay);
                String monthString = String.valueOf(nMonth+1);

                if(dayString.length() == 1)
                    dayString = "0" + dayString;
                if(monthString.length() == 1)
                    monthString = "0" + monthString;

                et_DOB.setText(dayString + "/" + monthString + "/" + nYear);
            }
        }, day, month, year);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
        datePickerDialog.updateDate(1990, 00, 01);
        datePickerDialog.show();
    }

    //Age calculation
    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void getAge(String birthDate)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy").withLocale(Locale.UK);
        org.threeten.bp.LocalDate today = org.threeten.bp.LocalDate.now();
        org.threeten.bp.LocalDate ageFormat = org.threeten.bp.LocalDate.parse(birthDate, formatter);
        org.threeten.bp.Period period = org.threeten.bp.Period.between(ageFormat, today);

        int ageYears = period.getYears();
        int ageMonths = period.getMonths();
        int ageDays = period.getDays();

        et_year.setText(Integer.toString(ageYears));
        et_month.setText(Integer.toString(ageMonths));
        et_day.setText(Integer.toString(ageDays));
    }


    //Registration operation
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void registrationActivity()
    {

        //showProgressDialog();

        op_DOB = et_DOB.getText().toString().trim();
        district = et_place.getText().toString();
        state = et_state.getText().toString();
        country = et_country.getText().toString();
        pincode = et_pin.getText().toString().trim();
        dayOfyear = et_day.getText().toString().trim();
        int dayNo = Integer.parseInt(dayOfyear.trim());
        monthOfYear = et_month.getText().toString().trim();
        int monthNo = Integer.parseInt(monthOfYear.trim());
        yearNumber = et_year.getText().toString().trim();
        int yearNo = Integer.parseInt(yearNumber.trim());
        op_date = et_date.getText().toString();
        op_time = et_time.getText().toString();
        user = et_username.getText().toString();
        parentName = et_parentName.getText().toString();
        op_address = et_address.getText().toString();
        op_phoneM = et_phoneM.getText().toString();
        op_phoneR = et_phoneR.getText().toString();
        String contact_relation = spinner1.getSelectedItem().toString();
        String parent_initial = spinner2.getSelectedItem().toString();
        String contact_sex = spinner3.getSelectedItem().toString();
        String doctor_name = spinner4.getSelectedItem().toString();



        OPDetailsDTO opDetailsObj = new OPDetailsDTO();
        opDetailsObj.userMaster = new UserMasterDTO();
        opDetailsObj.contactDetails = new ContactDetailsDTO();
        opDetailsObj.contactDetails.countryMaster = new CountryMasterDTO();
        opDetailsObj.doctorMaster = new DoctorMasterDTO();
        opDetailsObj.contactDetails.stateMaster = new StateMasterDTO();


        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormatSend = new SimpleDateFormat("yyyy-MM-dd");

        @SuppressLint("SimpleDateFormat") SimpleDateFormat timeFormatSend = new SimpleDateFormat("hh:mm:ss");

        opDetailsObj.setOP_ID(0);
        opDetailsObj.setOP_NO(0);
        opDetailsObj.setOP_DATE(LocalDateTime.parse(dateFormatSend.format(new Date()) + "T" + timeFormatSend.format(new Date())));
        opDetailsObj.setOP_TIME(LocalDateTime.parse(dateFormatSend.format(new Date()) + "T" + timeFormatSend.format(new Date())));
        opDetailsObj.setOP_NAME(user);
        opDetailsObj.setOP_TYPE("N");
        opDetailsObj.setOP_TTYPE(0);
        opDetailsObj.setDEPT_ID(5);
        opDetailsObj.setREFERAL_ID(1);
        opDetailsObj.setSCHEME_ID(1);
        opDetailsObj.setCOUNTER_ID(1);
        opDetailsObj.setTRANS_TYPE("");
        opDetailsObj.doctorMaster.setDOCT_ID(1);
        opDetailsObj.userMaster.setUSER_ID(0);
        opDetailsObj.contactDetails.setCONTACT_ID(1);
        opDetailsObj.contactDetails.setCONTACT_INITIAL("");
        opDetailsObj.contactDetails.setCONTACT_NAME(user);
        opDetailsObj.contactDetails.setCONTACT_RELATION(parentName);
        opDetailsObj.contactDetails.setCONTACT_CAREOF(contact_relation);
        opDetailsObj.contactDetails.setCONTACT_ADDRESS1(district);
        opDetailsObj.contactDetails.setCONTACT_ADDRESS2(state);
        opDetailsObj.contactDetails.setCONTACT_ADDRESS3(country);
        opDetailsObj.contactDetails.setCONTACT_ADDRESS4("");
        opDetailsObj.contactDetails.setCONTACT_PLACE(district);
        opDetailsObj.contactDetails.setCONTACT_PHONE1(op_phoneM);
        opDetailsObj.contactDetails.setCONTACT_PHONE2(op_phoneR);
        opDetailsObj.contactDetails.setCONTACT_AGEY(yearNo);
        opDetailsObj.contactDetails.setCONTACT_AGEM(monthNo);
        opDetailsObj.contactDetails.setCONTACT_AGED(dayNo);
        opDetailsObj.contactDetails.setCONTACT_DOB(opDOB);
        opDetailsObj.contactDetails.setCONTACT_SEX(contact_sex);
        opDetailsObj.contactDetails.countryMaster.setCOUNTRY_ID(1);
        opDetailsObj.contactDetails.stateMaster.setSTATE_ID(1);


        ObjectWriter objectMapper = new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY).writer().withDefaultPrettyPrinter();
        try {
            String jsonData = objectMapper.writeValueAsString(opDetailsObj);
            Toast.makeText(getApplicationContext(), jsonData, Toast.LENGTH_LONG).show();
            String TAG = "";
            Log.e(TAG, jsonData);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        /*Gson gson = new Gson();
        String jsonData = gson.toJson(opDetailsObj);*/
        //Toast.makeText(getApplicationContext(), jsonData, Toast.LENGTH_LONG).show();


        Retrofit retrofitBuilder = new Retrofit.Builder()
                .baseUrl(OPDetailsApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OPDetailsApi opDetailsApi = retrofitBuilder.create(OPDetailsApi.class);

        Call<ResponseBody> call = opDetailsApi.postOpDetails(opDetailsObj);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                //hideProgressDialog();
                //Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

}
