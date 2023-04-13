package coatocl.exaatocl.roomdatabsewithmvvm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity
{
    FloatingActionButton floatingActionButton;
    RecyclerView recycler;
    Adapter adapter;
    LinearLayoutManager linearLayoutManager;
    ViewModel viewModel;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButton = findViewById(R.id.floatingActionButton);
        recycler = findViewById(R.id.recycler);

        floatingActionButton.setOnClickListener(v -> {
            Intent intentGo = new Intent(this,Second_Activity.class);
            startActivityForResult(intentGo,101010);
        });

        linearLayoutManager = new LinearLayoutManager(this);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(linearLayoutManager);

        adapter = new Adapter();
        recycler.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(ViewModel.class);

        viewModel.getAllCourse().observe(this, models -> adapter.submitList(models));

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                viewModel.delete(adapter.getCourseAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this,"deleted",Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recycler);

        adapter.setOnItemClickListener(model -> {

            Intent intent = new Intent(MainActivity.this, Second_Activity.class);
            intent.putExtra("extra_id", model.getId());
            intent.putExtra("extra_name", model.getModelName());
            intent.putExtra("extra_duration", model.getModelDuration());
            intent.putExtra("extra_course", model.getModelCourse());
            intent.putExtra("extra_field", model.getModelField());

//            Log.d("extra_duration",extra_duration);

            startActivityForResult(intent, 2);
            Toast.makeText(this,"Go",Toast.LENGTH_SHORT).show();

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        if(requestCode == 1 && resultCode ==RESULT_OK)
//        {
//            assert data != null;
//            String courseName = data.getStringExtra("extra_name");
//            String courseCourse = data.getStringExtra("extra_course");
//            String courseDuration = data.getStringExtra("extra_duration");
//            String courseFiled = data.getStringExtra("extra_field");
//
//            CustomModel model = new CustomModel(courseName,courseDuration,courseCourse,courseFiled);
//            viewModel.insert(model);
//
//            Toast.makeText(this, "Course saved", Toast.LENGTH_SHORT).show();
//        }
        if(requestCode == 101010 && resultCode == RESULT_OK)
        {
            assert data != null;
            String courseName = data.getStringExtra("extra_name");
            String courseCourse = data.getStringExtra("extra_course");
            String courseDuration = data.getStringExtra("extra_duration");
            String courseFiled = data.getStringExtra("extra_field");

            CustomModel model = new CustomModel(courseName, courseDuration, courseCourse,courseFiled);
            viewModel.insert(model);

            Toast.makeText(this, "Course saved", Toast.LENGTH_SHORT).show();
        }
        else if (requestCode == 2 && resultCode == RESULT_OK) {
            assert data != null;
            int id = data.getIntExtra("extra_id" , -1);
            if (id == -1) {
                Toast.makeText(this, "Course can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }
            String courseName = data.getStringExtra("extra_name");
            String courseDuration = data.getStringExtra("extra_duration");
            String courseCourse = data.getStringExtra("extra_course");
            String courseFiled = data.getStringExtra("extra_field");
            CustomModel model = new CustomModel(courseName,courseDuration,courseCourse,courseFiled);
            model.setId(id);
            viewModel.update(model);

            Toast.makeText(this, "Course updated", Toast.LENGTH_SHORT).show();
        }
        else
            {
            Toast.makeText(this, "Course not saved", Toast.LENGTH_SHORT).show();
        }
    }
}
