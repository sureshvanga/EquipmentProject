package com.sample.equipmentproject.Adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.widget.CompoundButtonCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.sample.equipmentproject.Models.Names;
import com.sample.equipmentproject.R;


public class ExpandableAdapter extends RecyclerView.Adapter<ExpandableAdapter.ParentViewModel> {
    Names[] roots;
    Context context;
    public ExpandableAdapter(Names[] roots, Context context) {
        this.roots = roots;
        this.context = context;
    }
    @NonNull
    @Override
    public ParentViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        return new ParentViewModel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParentViewModel holder, int position) {
        try {
            holder.textviewMake.setText(roots[position].getMake());
            holder.textviewID.setText(String.valueOf(roots[position].getId()));
            holder.textviewVin.setText(roots[position].getVin());
            holder.textviewYear.setText(String.valueOf(roots[position].getYear()));
            holder.textviewExpandMake.setText(roots[position].getMake());
            holder.textviewValue.setText(context.getString(R.string.doller) + roots[position].getValue());
            holder.textviewLength.setText(roots[position].getLength() + context.getString(R.string.one_space) + context.getString(R.string.ft));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return roots.length;
    }


    public class ParentViewModel extends RecyclerView.ViewHolder {
        public ConstraintLayout constraintLayout;
        TextView textviewMake, textviewVin, textviewID, textviewYear, textviewValue, textviewLength, textviewExpandMake;

        public ParentViewModel(@NonNull View itemView) {
            super(itemView);
            constraintLayout = itemView.findViewById(R.id.constraintLayout);
            constraintLayout.setBackgroundColor(Color.WHITE);
            constraintLayout.setMinHeight(70);


            LinearLayout parentLayout = new LinearLayout(itemView.getContext());
            parentLayout.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams parentLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 10);
            parentLayoutParams.leftMargin = 50;
            parentLayout.setLayoutParams(parentLayoutParams);


            CheckBox checkBox = new CheckBox(itemView.getContext());
            LinearLayout.LayoutParams checkBoxParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            checkBoxParams.leftMargin = 50;
            checkBox.setLayoutParams(checkBoxParams);
            if (Build.VERSION.SDK_INT < 21) {
                CompoundButtonCompat.setButtonTintList(checkBox, ColorStateList.valueOf(Color.GRAY));
            } else {
                checkBox.setButtonTintList(ColorStateList.valueOf(Color.GRAY));
            }


            textviewMake = new TextView(itemView.getContext());
            LinearLayout.LayoutParams tvMakeHeadingParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 8);
            tvMakeHeadingParams.leftMargin = 17;
            textviewMake.setLayoutParams(tvMakeHeadingParams);
            textviewMake.setTextColor(Color.BLACK);
            textviewMake.setTypeface(textviewMake.getTypeface(), Typeface.BOLD);
            textviewMake.setTextSize(18);

            textviewID = new TextView(itemView.getContext());
            LinearLayout.LayoutParams tvIdHeadingParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            textviewID.setLayoutParams(tvIdHeadingParams);
            textviewID.setTextColor(Color.BLACK);
            textviewID.setTextSize(16);

            ImageView imageView = new ImageView(itemView.getContext());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
            imageView.setLayoutParams(layoutParams);
            imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_keyboard_arrow_right_24));
            imageView.setTag("up_arrow");
            parentLayout.addView(checkBox);
            parentLayout.addView(textviewID);
            parentLayout.addView(textviewMake);
            parentLayout.addView(imageView);


            constraintLayout.addView(parentLayout);

            //Expandable Layout
            LinearLayout expandLayout = new LinearLayout(itemView.getContext());
            expandLayout.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            expandLayout.setLayoutParams(linearParams);

            //Vin layout with horizontal layout
            LinearLayout vinLayout = new LinearLayout(itemView.getContext());
            vinLayout.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams vinLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 10);
            vinLayoutParams.leftMargin = 65;
            vinLayout.setLayoutParams(vinLayoutParams);

            TextView tvVinHeading = new TextView(itemView.getContext());
            LinearLayout.LayoutParams layoutParamsVinHeading = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 8);
            layoutParamsVinHeading.topMargin = 50;
            tvVinHeading.setText(context.getString(R.string.vin));
            tvVinHeading.setTextColor(Color.GRAY);
            tvVinHeading.setTextSize(16);
            tvVinHeading.setLayoutParams(layoutParamsVinHeading);

            textviewVin = new TextView(itemView.getContext());
            LinearLayout.LayoutParams layoutParamsVin = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 2);
            layoutParamsVin.topMargin = 50;
            textviewVin.setTextColor(Color.BLACK);
            textviewVin.setTextSize(20);
            textviewVin.setTypeface(textviewMake.getTypeface(), Typeface.BOLD);
            textviewVin.setLayoutParams(layoutParamsVin);

            vinLayout.addView(tvVinHeading);
            vinLayout.addView(textviewVin);


            //Year layout with horizontal layout
            LinearLayout yearLayout = new LinearLayout(itemView.getContext());
            yearLayout.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams expandLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 10);
            expandLayoutParams.leftMargin = 65;
            yearLayout.setLayoutParams(expandLayoutParams);


            TextView tvYearHeading = new TextView(itemView.getContext());
            LinearLayout.LayoutParams layoutParamsYearHeading = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 8);
            tvYearHeading.setText(context.getString(R.string.year));
            tvYearHeading.setTextColor(Color.GRAY);
            tvYearHeading.setTextSize(16);
            tvYearHeading.setLayoutParams(layoutParamsYearHeading);

            textviewYear = new TextView(itemView.getContext());
            LinearLayout.LayoutParams layoutParamsYear = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 2);
            textviewYear.setTextSize(20);
            textviewYear.setTextColor(Color.BLACK);
            textviewYear.setTypeface(textviewMake.getTypeface(), Typeface.BOLD);
            textviewYear.setLayoutParams(layoutParamsYear);

            yearLayout.addView(tvYearHeading);
            yearLayout.addView(textviewYear);

            //Make layout with horizontal layout
            LinearLayout makeLayout = new LinearLayout(itemView.getContext());
            makeLayout.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams makeLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 10);
            makeLayoutParams.leftMargin = 65;
            makeLayout.setLayoutParams(makeLayoutParams);


            TextView tvMakeHeading = new TextView(itemView.getContext());
            LinearLayout.LayoutParams layoutParamsMakeHeading = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 8);
            tvMakeHeading.setText(context.getString(R.string.make));
            tvMakeHeading.setTextColor(Color.GRAY);
            tvMakeHeading.setTextSize(16);
            tvMakeHeading.setLayoutParams(layoutParamsMakeHeading);

            textviewExpandMake = new TextView(itemView.getContext());
            LinearLayout.LayoutParams layoutParamsMake = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 2);
            textviewExpandMake.setTextSize(20);
            textviewExpandMake.setTypeface(textviewMake.getTypeface(), Typeface.BOLD);
            textviewExpandMake.setTextColor(Color.BLACK);
            textviewExpandMake.setLayoutParams(layoutParamsMake);

            makeLayout.addView(tvMakeHeading);
            makeLayout.addView(textviewExpandMake);

            //Values layout with horizontal layout
            LinearLayout valueLayout = new LinearLayout(itemView.getContext());
            valueLayout.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams valueLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 10);
            valueLayoutParams.leftMargin = 65;
            valueLayout.setLayoutParams(valueLayoutParams);


            TextView tvValueHeading = new TextView(itemView.getContext());
            LinearLayout.LayoutParams layoutParamsValueHeading = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 8);
            tvValueHeading.setText(context.getString(R.string.value));
            tvValueHeading.setTextColor(Color.GRAY);
            tvValueHeading.setTextSize(16);
            tvValueHeading.setLayoutParams(layoutParamsValueHeading);

            textviewValue = new TextView(itemView.getContext());
            LinearLayout.LayoutParams layoutParamsValue = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 2);
            textviewValue.setTextSize(20);
            textviewValue.setTypeface(textviewMake.getTypeface(), Typeface.BOLD);
            textviewValue.setTextColor(Color.BLACK);
            textviewValue.setLayoutParams(layoutParamsValue);

            valueLayout.addView(tvValueHeading);
            valueLayout.addView(textviewValue);

            //Length layout with horizontal layout
            LinearLayout lengthLayout = new LinearLayout(itemView.getContext());
            lengthLayout.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams lengthLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 10);
            lengthLayoutParams.leftMargin = 65;
            lengthLayout.setLayoutParams(lengthLayoutParams);


            TextView tvLengthHeading = new TextView(itemView.getContext());
            LinearLayout.LayoutParams layoutParamsLengthHeading = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 8);
            tvLengthHeading.setText(context.getString(R.string.length));
            tvLengthHeading.setTextSize(16);
            tvLengthHeading.setTextColor(Color.GRAY);
            tvLengthHeading.setLayoutParams(layoutParamsLengthHeading);

            textviewLength = new TextView(itemView.getContext());
            LinearLayout.LayoutParams layoutParamsLength = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 2);
            textviewLength.setTextSize(20);
            textviewLength.setTypeface(textviewMake.getTypeface(), Typeface.BOLD);
            textviewLength.setTextColor(Color.BLACK);
            textviewLength.setLayoutParams(layoutParamsLength);

            lengthLayout.addView(tvLengthHeading);
            lengthLayout.addView(textviewLength);


            //adding All Layouts
            expandLayout.addView(vinLayout);
            expandLayout.addView(yearLayout);
            expandLayout.addView(makeLayout);
            expandLayout.addView(valueLayout);
            expandLayout.addView(lengthLayout);

            expandLayout.setVisibility(View.GONE);
            constraintLayout.addView(expandLayout);


            itemView.setOnClickListener(view -> {

                if (imageView.getTag().equals(context.getString(R.string.up_arrow))) {
                    expandLayout.setVisibility(View.VISIBLE);
                    imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_keyboard_arrow_down_24));
                    imageView.setTag(context.getString(R.string.down_arrow));
                } else {
                    expandLayout.setVisibility(View.GONE);
                    imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_keyboard_arrow_right_24));
                    imageView.setTag(context.getString(R.string.up_arrow));
                }


            });
        }
    }


}
