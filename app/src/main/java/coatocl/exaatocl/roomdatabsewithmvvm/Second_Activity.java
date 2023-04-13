package coatocl.exaatocl.roomdatabsewithmvvm;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class Second_Activity extends AppCompatActivity
{
    EditText nameEdit,courseEdit,durationEdit,fieldEdit;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_);

        nameEdit = findViewById(R.id.nameEdit);
        courseEdit = findViewById(R.id.courseEdit);
        durationEdit = findViewById(R.id.durationEdit);
        fieldEdit = findViewById(R.id.fieldEdit);

        button = findViewById(R.id.button);

        Intent intent = getIntent();
        if(intent.hasExtra("extra_id"))
        {
            nameEdit.setText(intent.getStringExtra("extra_name"));
            courseEdit.setText(intent.getStringExtra("extra_course"));
            durationEdit.setText(intent.getStringExtra("extra_duration"));
            fieldEdit.setText(intent.getStringExtra("extra_field"));
        }

        button.setOnClickListener(v -> {
            String cName = nameEdit.getText().toString();
            String cCourse = courseEdit.getText().toString();
            String cDuration = durationEdit.getText().toString();
            String cField = fieldEdit.getText().toString();

            if(cName.isEmpty())
            {
                nameEdit.setError("required");

            }
            if(cCourse.isEmpty())
            {
                courseEdit.setError("required");
            }
            if(cDuration.isEmpty())
            {
                durationEdit.setError("required");
            }
            if(cField.isEmpty())
            {
                fieldEdit.setError("required");
            }
            else
            {
                saveCourse(cName,cDuration,cCourse,cField);

            }
        });

    }

    private void saveCourse(String cName, String cDuration, String cCourse, String cField)
    {
        Intent data = new Intent();

        data.putExtra("extra_name",cName);
        data.putExtra("extra_duration",cDuration);
        data.putExtra("extra_field",cField);
        data.putExtra("extra_course",cCourse);

        int id = getIntent().getIntExtra("extra_id",-1);

        if(id!=-1)
        {
            data.putExtra("extra_id",id);
        }
        setResult(RESULT_OK,data);
        finish();

        Toast.makeText(this,"Data gone to database",Toast.LENGTH_SHORT).show();
    }
}
