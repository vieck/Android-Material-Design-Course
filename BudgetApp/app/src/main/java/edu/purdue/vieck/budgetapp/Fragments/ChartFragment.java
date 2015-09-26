package edu.purdue.vieck.budgetapp.Fragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendPosition;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Highlight;
import com.github.mikephil.charting.utils.PercentFormatter;

import java.util.ArrayList;

import edu.purdue.vieck.budgetapp.DatabaseHandler;
import edu.purdue.vieck.budgetapp.R;
import edu.purdue.vieck.budgetapp.Adapters.ChartAdapter;
import edu.purdue.vieck.budgetapp.CustomObjects.BudgetElement;


public class ChartFragment extends Fragment implements OnChartValueSelectedListener {

    int month, year;

    private PieChart mPieChart;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private ChartAdapter mChartAdapter;
    private Button chartButton;
    DatabaseHandler mDatabaseHandler;
    private Context mContext;

    @Override
    public void onAttach(final Activity activity) {
        mContext = activity.getApplicationContext();
        super.onAttach(activity);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chart, container, false);
        month = getArguments().getInt("month", -1);
        year = getArguments().getInt("year", -1);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.budget_recycler_view);
        mChartAdapter = new ChartAdapter(mContext, month, year);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(mChartAdapter);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mChartAdapter = new ChartAdapter(mContext, month, year);
                mRecyclerView.setAdapter(mChartAdapter);
                setData(3, 100);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        mDatabaseHandler = new DatabaseHandler(mContext);

        mPieChart = (PieChart) view.findViewById(R.id.pie_chart);
        //mPieChart.setDescription("Budget Wheel");
        mPieChart.setUsePercentValues(true);
        mPieChart.setDragDecelerationFrictionCoef(0.95f);
        //mTypeface = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        mPieChart.setDrawHoleEnabled(true);
        //mPieChart.setHoleColor(Color.WHITE);
        mPieChart.setCenterTextColor(Color.BLACK);
        //mPieChart.setTransparentCircleColor(Color.WHITE);
        mPieChart.setHoleRadius(45f);
        mPieChart.setTransparentCircleRadius(45f);
        mPieChart.setDrawCenterText(true);

        mPieChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        mPieChart.setRotationEnabled(true);

        // mChart.setUnit(" €");
        // mChart.setDrawUnitsInChart(true);

        // add a selection listener
        mPieChart.setOnChartValueSelectedListener(this);

        //mPieChart.setCenterText("MPAndroidChart\nby Philipp Jahoda");
        mPieChart.setCenterTextSize(9.5f);

        setData(3, 100);

        mPieChart.animateY(1500, Easing.EasingOption.EaseInOutQuad);
        // mChart.spin(2000, 0, 360);

        Legend l = mPieChart.getLegend();
        l.setPosition(LegendPosition.PIECHART_CENTER);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(7f);
        l.setYOffset(0f);
        l.setXOffset(5f);
        return view;
    }

    private void setData(int count, float range) {

        float mult = range;

        ArrayList<Entry> yVals = new ArrayList<Entry>();

        // IMPORTANT: In a PieChart, no values (Entry) should have the same
        // xIndex (even if from different DataSets), since no values can be
        // drawn above each other.
        /*for (int i = 0; i < count + 1; i++) {
            yVals.add(new Entry((float) (Math.random() * mult) + mult / 5, i));
        }*/
        ArrayList<BudgetElement> groceries = mDatabaseHandler.getFilteredData("Groceries");
        float groceryCount, medicalCount, entertainmentCount, utilitiesCount, incomeCount;
        groceryCount = 0;
        for (BudgetElement element : groceries)
            groceryCount += element.getAmount();


        ArrayList<BudgetElement> medical = mDatabaseHandler.getFilteredData("Medical");
        medicalCount = 0;
        for (BudgetElement element : medical)
            medicalCount += element.getAmount();


        ArrayList<BudgetElement> entertainment = mDatabaseHandler.getFilteredData("Entertainment");
        entertainmentCount = 0;
        for (BudgetElement element : entertainment)
            entertainmentCount += element.getAmount();

        ArrayList<BudgetElement> utilities = mDatabaseHandler.getFilteredData("Utilities");
        utilitiesCount = 0;
        for (BudgetElement element : utilities)
            utilitiesCount += element.getAmount();

        ArrayList<BudgetElement> income = mDatabaseHandler.getFilteredData("Income");
        incomeCount = 0;
        for (BudgetElement element : income)
            incomeCount += element.getAmount();

        float totalAmount = groceryCount + utilitiesCount + entertainmentCount + medicalCount + incomeCount;
        int totalCount = groceries.size() + utilities.size() + medical.size() + entertainment.size() + income.size();
        ArrayList<String> xVals = new ArrayList<String>();
        if (incomeCount != 0) {
            yVals.add(new Entry(incomeCount / totalAmount, 4));
            xVals.add("Income");
        }

        if (utilitiesCount != 0) {
            yVals.add(new Entry(utilitiesCount / totalAmount, 1));
            xVals.add("Utilities");
        }

        if (entertainmentCount != 0) {
            yVals.add(new Entry(entertainmentCount / totalAmount, 2));
            xVals.add("Entertainment");
        }

        if (medicalCount != 0) {
            yVals.add(new Entry(medicalCount / totalAmount, 3));
            xVals.add("Medical");
        }

        if (groceryCount != 0) {
            yVals.add(new Entry(groceryCount / totalAmount, 0));
            xVals.add("Groceries");
        }

        PieDataSet dataSet = new PieDataSet(yVals, "Budget");
        dataSet.setSliceSpace(5f);
        dataSet.setSelectionShift(9f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);

        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.BLACK);
        mPieChart.setData(data);

        // undo all highlights
        mPieChart.highlightValues(null);

        mPieChart.invalidate();
    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

        if (e == null)
            return;
        Log.i("VAL SELECTED",
                "Value: " + e.getVal() + ", xIndex: " + e.getXIndex()
                        + ", DataSet index: " + dataSetIndex);
    }

    @Override
    public void onNothingSelected() {
        Log.i("PieChart", "nothing selected");
    }
}
