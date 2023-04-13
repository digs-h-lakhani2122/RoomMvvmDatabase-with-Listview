package coatocl.exaatocl.roomdatabsewithmvvm;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "student_details")
public class CustomModel
{
    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name="id")
    private int id;

    @ColumnInfo(name="modelName")
    private final String modelName;
    @ColumnInfo(name="modelCourse")
    private final String modelCourse;
    @ColumnInfo(name="modelDuration")
    private final String modelDuration;
    @ColumnInfo(name="modelField")
    private final String modelField;

    public CustomModel(String modelName, String modelDuration,
                       String modelCourse, String modelField) {
        this.modelName = modelName;
        this.modelDuration = modelDuration;
        this.modelCourse = modelCourse;
        this.modelField = modelField;
    }

    int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    String getModelName() {
        return modelName;
    }

    String getModelDuration() {
        return modelDuration;
    }

    String getModelCourse() {
        return modelCourse;
    }

    String getModelField() {
        return modelField;
    }

}