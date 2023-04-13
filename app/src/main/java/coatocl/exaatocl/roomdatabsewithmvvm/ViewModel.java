package coatocl.exaatocl.roomdatabsewithmvvm;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class ViewModel extends AndroidViewModel
{
    private final Repository repository;
    private final LiveData<List<CustomModel>>allCourse;

    public ViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        allCourse = repository.getAllCourses();
    }

    void insert(CustomModel customModel)
    {
        repository.insert(customModel);
    }

    void update(CustomModel customModel)
    {
        repository.update(customModel);
    }

    void delete(CustomModel customModel)
    {
        repository.delete(customModel);
    }

    public void deleteAll()
    {
       repository.deleteAll();
    }

    LiveData<List<CustomModel>>getAllCourse()
    {
        return allCourse;
    }
}
