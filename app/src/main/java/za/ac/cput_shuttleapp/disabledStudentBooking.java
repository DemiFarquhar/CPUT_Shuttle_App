package za.ac.cput_shuttleapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Button;
import android.widget.TimePicker;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.List;

import static za.ac.cput_shuttleapp.R.drawable.logo;

public class disabledStudentBooking extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //Spinner
    String[] disability = {"Intellectual disability", "Vision impairment", "Blindness", "Physical disability", "other"};
    String[] from = {"District Six Campus", "Residence"};
    String[] to = {"District Six Campus", "Residence"};

    private Button buttonBack;
    private Button buttonNext;
    private Button buttonConfirm;
    private Database database;
    TextView date;
    DatePickerDialog datePickerDialog;
    TextView time;
    TimePickerDialog mTimePicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disabled_student_booking);

        database = new Database(this);
        AddData();

        ImageView myImageView = (ImageView) findViewById(R.id.imageView);
        myImageView.setImageResource(logo);

        TextView tv = findViewById(R.id.textView);
        tv.setMovementMethod(new ScrollingMovementMethod());
        TextView tv2 = findViewById(R.id.textView2);
        tv2.setMovementMethod(new ScrollingMovementMethod());

        addListenerOnButton();


        //Creating the instance of Spinner
        Spinner disability = (Spinner) findViewById(R.id.spinner2);
        disability.setOnItemSelectedListener(this);
        TextView tv3 = findViewById(R.id.textView3);
        tv3.setMovementMethod(new ScrollingMovementMethod());
        //Creating the ArrayAdapter
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, (List) disability);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        disability.setAdapter(aa);

        Spinner From = (Spinner) findViewById(R.id.spinner);
        From.setOnItemSelectedListener(this);
        TextView tv4 = findViewById(R.id.textView4);
        tv4.setMovementMethod(new ScrollingMovementMethod());
        ArrayAdapter aa2 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, from);
        aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        From.setAdapter(aa2);

        Spinner To = (Spinner) findViewById(R.id.spinner);
        To.setOnItemSelectedListener(this);
        TextView tv5 = findViewById(R.id.textView5);
        tv4.setMovementMethod(new ScrollingMovementMethod());
        ArrayAdapter aa3 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, to);
        aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        To.setAdapter(aa3);

        date = (TextView) findViewById(R.id.date);
        date.setOnClickListener(new OnClickListener() {


            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(disabledStudentBooking.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });


        time = (TextView) findViewById(R.id.time);
        time.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mCurrentTime = Calendar.getInstance();
                int hour = mCurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mCurrentTime.get(Calendar.MINUTE);

                mTimePicker = new TimePickerDialog(disabledStudentBooking.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        time.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Time");
                mTimePicker.show();
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void addListenerOnButton() {
        buttonBack = (Button) findViewById(R.id.button);
        buttonNext = (Button) findViewById(R.id.buttonNext);
        buttonConfirm = (Button) findViewById(R.id.buttonConfirm);
        buttonBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }


        });
    }

    private void AddData() {
        buttonConfirm.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                String DISABILITY = disability.getClass().toString();
                String FROM = from.getClass().toString();
                String TO = to.getClass().toString();
                String DATE = date.getClass().toString();
                String TIME = time.getText().toString();
                database.insertData(DISABILITY, FROM, TO, DATE, TIME);


            }

        });
    }
}











