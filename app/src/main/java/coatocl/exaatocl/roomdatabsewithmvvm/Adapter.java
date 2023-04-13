package coatocl.exaatocl.roomdatabsewithmvvm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
public class Adapter extends ListAdapter<CustomModel, Adapter.ViewHolder>
{
    private OnItemClickListener listener;
    Adapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<CustomModel> DIFF_CALLBACK = new DiffUtil.ItemCallback<CustomModel>() {
        @Override
        public boolean areItemsTheSame(CustomModel oldItem, CustomModel newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(CustomModel oldItem, CustomModel newItem) {
            return oldItem.getModelName().equals(newItem.getModelName()) &&
                    oldItem.getModelDuration().equals(newItem.getModelDuration()) &&
                    oldItem.getModelCourse().equals(newItem.getModelCourse()) &&
                    oldItem.getModelField().equals(newItem.getModelField());
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.show, parent, false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CustomModel model = getCourseAt(position);
        holder.nameShow.setText(model.getModelName());
        holder.durationShow.setText(model.getModelDuration());
        holder.courseShow.setText(model.getModelCourse());
        holder.fieldShow.setText(model.getModelField());
    }

    CustomModel getCourseAt(int position) {
        return getItem(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameShow, durationShow, courseShow, fieldShow;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameShow = itemView.findViewById(R.id.nameShow);
            durationShow = itemView.findViewById(R.id.durationShow);
            courseShow = itemView.findViewById(R.id.courseShow);
            fieldShow = itemView.findViewById(R.id.fieldShow);

            itemView.setOnClickListener(v ->
            {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(getItem(position));
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(CustomModel model);
    }

    void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}



//    private Context ctx;
//    private List<CustomModel> listItem;
//    private static AdapterView.OnItemClickListener listener;
//    String extra_id,extra_name,extra_course,extra_duration,extra_field;
////    ViewModel viewModel;
////    private ViewHolder.ClickListener clickListener;
////    ViewModel viewModel;
//
////    Adapter(ViewHolder.ClickListener clickListener) {
////        this.clickListener = clickListener;
////    }
//
////    Adapter(Context ctx, List<CustomModel> listItem) {
////        this.ctx = ctx;
////        this.listItem = listItem;
////    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
//    {
//        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
//        View view = layoutInflater.inflate(R.layout.show,parent,false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//
////        List<CustomModel>listItem = new ArrayList<>();
//
//        CustomModel customModel = listItem.get(position);
//
//        holder.nameShow.setText(customModel.getModelName());
//        holder.durationShow.setText(customModel.getModelDuration());
//        holder.courseShow.setText(customModel.getModelCourse());
//        holder.fieldShow.setText(customModel.getModelField());
//
////        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
////            @Override
////            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
////                return false;
////            }
////
////            @Override
////            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
////                viewModel.delete(listItem.get(viewHolder.getPosition()));
////                Toast.makeText(ctx,"deleted",Toast.LENGTH_SHORT).show();
////
////            }
////        });
//
////        holder.itemView.setOnClickListener(v ->
////        {
////
////            Intent intent = new Intent(ctx, Second_Activity.class);
////            intent.putExtra(extra_id, customModel.getId());
////            intent.putExtra(extra_name, customModel.getModelName());
////            intent.putExtra(extra_course, customModel.getModelCourse());
////            intent.putExtra(extra_duration, customModel.getModelDuration());
////            intent.putExtra(extra_field, customModel.getModelField());
////
////            ((Activity) ctx).startActivityForResult(intent, 2);
////
////        });
//
////        Intent data = new Intent();
////
////        String courseName = data.getStringExtra(extra_name);
////            String courseCourse = data.getStringExtra(extra_course);
////            String courseDuration = data.getStringExtra(extra_duration);
////            String courseFiled = data.getStringExtra(extra_field);
////
////            holder.nameShow.setText(courseName);
////        holder.durationShow.setText(courseDuration);
////        holder.courseShow.setText(courseCourse);
////        holder.fieldShow.setText(courseFiled);
//
//    }
//
////    @Override
////    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
////        super.onAttachedToRecyclerView(recyclerView);
////
////        if(viewModel == null) {
////            viewModel = new ViewModelProviders(recyclerView.getContext().get(ViewModel.class));
////        }
////    }
////
////    CustomModel getCourseAt(int position) {
////        return getCourseAt(position);
////    }
//
//
//    @Override
//    public int getItemCount() {
//        return listItem.size();
//    }
//
//    static class ViewHolder extends RecyclerView.ViewHolder {
//        TextView nameShow, durationShow, courseShow, fieldShow;
//
//        ViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            nameShow = itemView.findViewById(R.id.nameShow);
//            durationShow = itemView.findViewById(R.id.durationShow);
//            courseShow = itemView.findViewById(R.id.courseShow);
//            fieldShow = itemView.findViewById(R.id.fieldShow);
//
//        }
//
////        @Override
////        public void onClick(View v) {
////            int position = getBindingAdapterPosition();
////            if (position >= 0) {
////                ViewHolder.ClickListener.onItemClick(position, v);
////            }
////        }
////
////        interface ClickListener {
////            static void onItemClick(int position, View v) {
////
////            }
////        }
//
//    }
//}
