package com.example.kdm.allalgo.visualizer;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.kdm.allalgo.R;
import com.example.kdm.allalgo.algorithm.list.Stack;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.roughike.bottombar.BottomBar;

public class StackControls extends LinearLayout {

    Stack stack;
    BottomBar bottomBar;
    FloatingActionButton fab;

    public StackControls(Context context, BottomBar bottomBar, FloatingActionButton fab) {
        super(context);
        this.bottomBar = bottomBar;
        this.fab = fab;
        initialise();
    }

    public StackControls(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialise();
    }

    public void setStack(Stack algorithm) {
        this.stack = algorithm;
    }


    private void initialise() {
        setOrientation(VERTICAL);

        View controls = LayoutInflater.from(getContext()).inflate(R.layout.stack_controls, this, false);

        final Button peek, push, pop;

        peek = (Button) controls.findViewById(R.id.sc_peek);
        push = (Button) controls.findViewById(R.id.sc_push);
        pop = (Button) controls.findViewById(R.id.sc_pop);

        peek.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomBar.selectTabAtPosition(1, true);
                stack.sendMessage(Stack.PEEK);
            }
        });
        push.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomBar.selectTabAtPosition(1, true);
                stack.sendMessage(Stack.PUSH);
            }
        });
        pop.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomBar.selectTabAtPosition(1, true);
                stack.sendMessage(Stack.POP);
            }
        });


        addView(controls);
    }
}