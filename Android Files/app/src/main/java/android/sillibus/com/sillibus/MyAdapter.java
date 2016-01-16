package android.sillibus.com.sillibus;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by urvik on 1/16/2016.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private ArrayList<Assignment> mDataset;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View mView;
        public TextView mTextView;
        public ViewHolder(View v) {
            super(v);
            mView = v;
        }
    }
    public MyAdapter(ArrayList<Assignment> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
     //   return null;
        //create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.assignment_card, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        View holder1, holder2, holder3, holder4;
        holder1 = holder.mView.findViewById(R.id.subText);
        holder2 = holder.mView.findViewById(R.id.dueDateText);
        // holder3 = holder.mView.findViewById(R.id.gradeText);
//        holder4 = holder.mView.findViewById(R.id.descText);
        if (mDataset.get(position).getCourse() != null) {
            ((TextView) holder1).setText(mDataset.get(position).getCourse().getSub());
        }
        if (mDataset.get(position).getDueDate() != null) {
            ((TextView) holder2).setText((CharSequence) mDataset.get(position).getDueDate());
        }
       // ((TextView) holder3).setText(mDataset.get(position).get);
//        ((TextView) holder4).setText(mDataset[position]);
        //.setText(mDataset[position]));
        //setText(mDataset[position]);
    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        //list.length to return
        return mDataset.size();
    }
}
