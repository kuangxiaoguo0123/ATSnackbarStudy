package com.asiatravel.atsnackbarstudy;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private Snackbar mSnackbar;
    private CoordinatorLayout mLayout;
    private FloatingActionButton mActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLayout = (CoordinatorLayout) findViewById(R.id.activity_main);
        mActionButton = (FloatingActionButton)
                findViewById(R.id.floating_actionbar);
        setListenerForFloatingBar();
    }

    private void setListenerForFloatingBar() {
        mActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSnackbar != null && mSnackbar.isShown()) {
                    mSnackbar.dismiss();
                    return;
                }
                showSnackBar();
            }
        });
    }

    private void showSnackBar() {
        mSnackbar = Snackbar.make(mLayout, "描述内容", Snackbar.LENGTH_SHORT);
        mSnackbar.setActionTextColor(getResources().getColor(R.color.colorAccent));
        mSnackbar.setAction("点击", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "hello click", Toast.LENGTH_SHORT).show();
            }
        });
        View view = mSnackbar.getView();
        view.setBackgroundResource(R.color.snackbar_backgound);
        mSnackbar.show();
        if (mSnackbar.isShown()) {
            doAnimation(mSnackbar.getView());
        }
    }

    private void doAnimation(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "alpha", 0, 1f).setDuration(500);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.start();
    }
}
