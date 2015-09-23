package edu.purdue.vieck.budgetapp;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Stack;

/**
 * Created by vieck on 7/16/15.
 */
public class ChartRecyclerAdapter extends RecyclerView.Adapter<ChartRecyclerAdapter.mViewHolder>{

    Context context;
    DatabaseHandler databaseHandler;
    Stack<BudgetElement> mDataset = new Stack<>();

    public ChartRecyclerAdapter(Context context) {
        this.context = context;
        databaseHandler = new DatabaseHandler(context);
        mDataset = databaseHandler.getAllData();
    }

    @Override
    public mViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.budget_item, viewGroup, false);
        mViewHolder viewHolder = new mViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(mViewHolder viewHolder, int i) {
        final BudgetElement budgetElement = mDataset.get(i);
        viewHolder.date.setText(budgetElement.getMonth() + "-"+budgetElement.getMonth() + "-" + budgetElement.getYear());
        viewHolder.amount.setText(budgetElement.getAmount()+"");
        viewHolder.expenses.setText(budgetElement.isType()+"");
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
            cardView = (CardView) v.findViewById(R.id.cardview);
            date = (TextView) v.findViewById(R.id.cardview_date);
            amount = (TextView) v.findViewById(R.id.cardview_amount);
            expenses = (TextView) v.findViewById(R.id.cardview_expenses);
            income = (TextView) v.findViewById(R.id.cardview_budget);
        }
    }
}