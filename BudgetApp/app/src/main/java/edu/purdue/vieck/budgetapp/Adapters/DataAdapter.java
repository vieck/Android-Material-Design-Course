package edu.purdue.vieck.budgetapp.Adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Stack;

import edu.purdue.vieck.budgetapp.DatabaseHandler;
import edu.purdue.vieck.budgetapp.R;
import edu.purdue.vieck.budgetapp.CustomObjects.BudgetElement;

/**
 * Created by vieck on 7/16/15.
 */
public class DataAdapter extends RecyclerView.Adapter<DataAdapter.mViewHolder> {

    Context context;
    DatabaseHandler databaseHandler;
    Stack<BudgetElement> mDataset = new Stack<>();

    public DataAdapter(Context context, String filter) {
        this.context = context;
        databaseHandler = new DatabaseHandler(context);
        if (filter == "") {
            mDataset = databaseHandler.getAllData();
        } else {
            mDataset = databaseHandler.searchDatabase(filter);
        }
    }


    @Override
    public mViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.data_item, viewGroup, false);
        mViewHolder viewHolder = new mViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(mViewHolder viewHolder, int i) {
        final BudgetElement budgetElement = mDataset.get(i);
        viewHolder.date.setText(budgetElement.getMonth() + "-" + budgetElement.getMonth() + "-" + budgetElement.getYear());
        viewHolder.amount.setText(budgetElement.getAmount() + "");
        viewHolder.expenses.setText(budgetElement.isType() + "");
        viewHolder.income.setText(budgetElement.getCategory());
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHandler.delete(budgetElement);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class mViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView date, amount, expenses, income;

        public mViewHolder(View v) {
            super(v);
            cardView = (CardView) v.findViewById(R.id.data_cardview);
            date = (TextView) v.findViewById(R.id.data_cardview_date);
            amount = (TextView) v.findViewById(R.id.data_cardview_amount);
            expenses = (TextView) v.findViewById(R.id.data_cardview_expenses);
            income = (TextView) v.findViewById(R.id.data_cardview_budget);
        }
    }
}
