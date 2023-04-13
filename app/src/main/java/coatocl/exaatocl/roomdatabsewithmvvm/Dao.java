package coatocl.exaatocl.roomdatabsewithmvvm;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@androidx.room.Dao
public interface Dao
{
    @Insert
    void insert(CustomModel customModel);

    @Update
    void update(CustomModel customModel);

    @Delete
    void delete(CustomModel customModel);

    @Query("DELETE FROM student_details")
    void deleteAllCourse();

    @Query("SELECT * FROM student_details ORDER BY modelName ASC")
    LiveData<List<CustomModel>>getAllCourse();

}
