package com.example.calmsleep_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private StackFramework stackFramework;
    private Button view1, view2, view3;
    private Animation expandAnimation, collapseAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        view1 = findViewById(R.id.view1);
        view2 = findViewById(R.id.view2);
        view3 = findViewById(R.id.view3);

        // Create the stack framework
        stackFramework = new StackFramework();
        stackFramework.addView(view1);
        stackFramework.addView(view2);
        stackFramework.addView(view3);

        // Load animations
        expandAnimation = AnimationUtils.loadAnimation(this, R.anim.expand);
        collapseAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        // Set click listeners
        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleView(view1);
            }
        });

        view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleView(view2);
            }
        });

        view3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleView(view3);
            }
        });
    }

    private void toggleView(Button view) {
        stackFramework.toggleView(view);
        if (view == stackFramework.getExpandedView()) {
            view.startAnimation(expandAnimation);
            Intent intent = new Intent(MainActivity.this, test01.class);
            startActivity(intent);
        } else {
            view.startAnimation(collapseAnimation);
        }
    }

    private class StackFramework {
        private List<Button> views;
        private Button expandedView;

        public StackFramework() {
            views = new ArrayList<>();
        }

        public void addView(Button view) {
            views.add(view);
        }

        public void toggleView(Button view) {
            if (view == expandedView) {
                // Collapse the view
                view.setSelected(false);
                expandedView = null;
            } else {
                if (expandedView != null) {
                    // Collapse the currently expanded view
                    expandedView.setSelected(false);
                }
                // Expand the clicked view
                view.setSelected(true);
                expandedView = view;
            }
        }

        public Button getExpandedView() {
            return expandedView;
        }
    }
}
