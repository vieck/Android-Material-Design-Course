package edu.purdue.vieck.budgetapp;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by vieck on 7/16/15.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.mViewHolder>{

    Context context;
    DatabaseHandler databaseHandler;
    ArrayList<BudgetElement> mDataset = new ArrayList<>();

    public RecyclerAdapter(Context context) {
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
        viewHolder.amount.setText("mAmount $");
        viewHolder.expenses.setText("mExpense");
        viewHolder.income.setText("mIncome");
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class mViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView amount;
        TextView expenses;
        TextView income;
        public mViewHolder(View v) {
            super(v);
            cardView = (CardView) v.findViewById(R.id.cardview);
            amount = (TextView) v.findViewById(R.id.cardview_amount);
            expenses = (TextView) v.findViewById(R.id.cardview_expenses);
            income = (TextView) v.findViewById(R.id.cardview_budget);
        }
    }
}