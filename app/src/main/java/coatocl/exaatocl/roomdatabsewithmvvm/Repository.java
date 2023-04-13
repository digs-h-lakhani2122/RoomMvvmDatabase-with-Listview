package coatocl.exaatocl.roomdatabsewithmvvm;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import java.util.List;

class Repository
{
    private final Dao dao;
    private final LiveData<List<CustomModel>> allCourses;

    Repository(Application application)
    {
        Database database = Database.getInstance(application);
        dao = database.Dao();
        allCourses = dao.getAllCourse();
    }

    void insert(CustomModel customModel)
    {
        new InsertAsyncTask(dao).execute(customModel);
    }

    void update(CustomModel customModel)
    {
        new UpdateAsyncTask(dao).execute(customModel);
    }

    void delete(CustomModel customModel)
    {
        new DeleteAsyncTask(dao).execute(customModel);
    }

    void deleteAll()
    {
        new DeleteAllAsyncTask(dao).execute();
    }

    LiveData<List<CustomModel>> getAllCourses()
    {
        return allCourses;
    }

    private static class InsertAsyncTask extends AsyncTask<CustomModel,Void,Void>
    {
        private final Dao dao;
        InsertAsyncTask(Dao dao)
        {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(CustomModel... customModels) {
            dao.insert(customModels[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<CustomModel,Void,Void>
    {
        private final Dao dao;
        UpdateAsyncTask(Dao dao)
        {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(CustomModel... customModels) {
            dao.update(customModels[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<CustomModel,Void,Void>
    {
        private final Dao dao;
        DeleteAsyncTask(Dao dao)
        {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(CustomModel... customModels) {
            dao.delete(customModels[0]);
            return null;
        }
    }

    private static class DeleteAllAsyncTask extends AsyncTask<CustomModel,Void,Void>
    {
        private final Dao dao;
        DeleteAllAsyncTask(Dao dao)
        {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(CustomModel... customModels) {
            dao.deleteAllCourse();
            return null;
        }
    }
}
