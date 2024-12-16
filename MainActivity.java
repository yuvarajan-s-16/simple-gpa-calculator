package com.example.gpa;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private LinearLayout subjectContainer;
    private Button addSubjectButton;
    private Button calculateCGPAButton;
    private TextView cgpaResultTextView;
    private ArrayList<LinearLayout> subjectLayouts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        subjectContainer = findViewById(R.id.subject_container);
        addSubjectButton = findViewById(R.id.add_subject);
        calculateCGPAButton = findViewById(R.id.calculate_cgpa);
        cgpaResultTextView = findViewById(R.id.cgpa_result);

        addSubjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addSubject();
            }
        });

        calculateCGPAButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateCGPA();
            }
        });
    }

    private void addSubject() {
        LinearLayout subjectLayout = new LinearLayout(this);
        subjectLayout.setOrientation(LinearLayout.HORIZONTAL);



        EditText gradeEditText = new EditText(this);
        gradeEditText.setHint("Grade");
        subjectLayout.addView(gradeEditText);

        EditText creditEditText = new EditText(this);
        creditEditText.setHint("Credit");
        subjectLayout.addView(creditEditText);

        subjectContainer.addView(subjectLayout);
        subjectLayouts.add(subjectLayout);
    }

    private void calculateCGPA() {
        double totalGradePoints = 0.0;
        double totalCredits = 0.0;

        for (LinearLayout subjectLayout : subjectLayouts) {

            EditText gradeEditText = (EditText) subjectLayout.getChildAt(0);
            EditText creditEditText = (EditText) subjectLayout.getChildAt(1);

            String grade = gradeEditText.getText().toString();
            double credit = Double.parseDouble(creditEditText.getText().toString());
            double gradePoint = getGradePoint(grade);
            totalGradePoints += gradePoint * credit;
            totalCredits += credit;
        }

        double cgpa = totalGradePoints / totalCredits;
        cgpaResultTextView.setText("CGPA: " + String.format("%.2f", cgpa));
    }

    private double getGradePoint(String grade) {
        // Implement the grade point mapping here
        // For example:
        if (grade.equals("O")|| grade.equals("o")) {
            return 10;
        } else if (grade.equals("A+")||grade.equals("a+")) {
            return 9;
        }
        else if (grade.equals("A")||grade.equals("a")) {
            return 8;
        }else if (grade.equals("B+")||grade.equals("b+")) {
            return 7;
        }else if (grade.equals("B")||grade.equals("b")) {
            return 6;
        }
        else
            return 0.0;
    }
}
