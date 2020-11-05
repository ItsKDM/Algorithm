package com.example.kdm.allalgo.algorithm.sorting;


import com.example.kdm.allalgo.algorithm.Algorithm;
import com.example.kdm.allalgo.algorithm.DataHandler;
import com.example.kdm.allalgo.visualizer.SortingVisualizer;

public class SortAlgorithm extends Algorithm implements DataHandler {

    public SortingVisualizer visualizer;

    public void setData(final int[] array) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                visualizer.setData(array);
            }
        });
        start();
        prepareHandler(this);
        sendData(array);
    }

    public void highlightSwap(final int one, final int two) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                visualizer.highlightSwap(one, two);
            }
        });
    }

    public void highlightTrace(final int position) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                visualizer.highlightTrace(position);
            }
        });
    }

    @Override
    public void onDataRecieved(Object data) {

    }

    @Override
    public void onMessageReceived(String message) {

    }
}
