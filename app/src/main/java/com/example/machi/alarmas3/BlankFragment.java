package com.example.machi.alarmas3;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class BlankFragment extends DialogFragment implements View.OnClickListener {

    View view;
    Button bfecha ,bhora,agregar;
    EditText efecha ,ehora;
    private int dia,mes,ano,hora,minutos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_blank, container, false);

        bfecha = view.findViewById(R.id.btnfecha);
        bhora = view.findViewById(R.id.btnhora);
        efecha = view.findViewById(R.id.txtfecha);
        ehora = view.findViewById(R.id.txthora);
        agregar = view.findViewById(R.id.btnagregar);
        agregar.setOnClickListener(this);

        bfecha.setOnClickListener(this);
        bhora.setOnClickListener(this);
        efecha.setOnClickListener(this);
        ehora.setOnClickListener(this);


        Time today = new Time(Time.getCurrentTimezone());
        today.setToNow();
        int monthDay = today.monthDay;
        int month = today.month;
        int year = today.year;
        efecha.setText(monthDay+"/"+month+"/"+year);

        // Inflate the layout for this fragment
        return view;
    }


    @Override
    public void onClick(View v) {

       if (v==bfecha||v==efecha) {
           final Calendar c = Calendar.getInstance();
           dia = c.get(Calendar.DAY_OF_MONTH);
           mes = c.get(Calendar.MONTH);
           ano = c.get(Calendar.YEAR);

           DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
               @Override
               public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                   efecha.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
               }
           }, dia, mes, ano);
           datePickerDialog.show();
       }


       if (v==bhora||v==ehora){

           final Calendar c = Calendar.getInstance();
           hora = c.get(Calendar.HOUR_OF_DAY);
           minutos = c.get(Calendar.MINUTE);

           TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
               @Override
               public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    ehora.setText(hourOfDay+":"+minute);
               }
           },hora,minutos,false);
           timePickerDialog.show();

       }

       if (v==agregar){
           Toast.makeText(getContext(), "Enviado ala base de datos", Toast.LENGTH_SHORT).show();
           Intent intent = new Intent(getContext(),MainActivity.class);
           startActivity(intent);
       }

    }


}
