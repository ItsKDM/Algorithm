package com.example.kdm.allalgo;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

//import com.naman14.algovisualizer.algorithm.Algorithm;
//import com.naman14.algovisualizer.algorithm.graph.BellmanFordAlgorithm;
//import com.naman14.algovisualizer.algorithm.graph.DijkstraAgorithm;
//import com.naman14.algovisualizer.algorithm.graph.GraphTraversalAlgorithm;
//import com.naman14.algovisualizer.algorithm.list.LinkedList;
//import com.naman14.algovisualizer.algorithm.list.Stack;
//import com.naman14.algovisualizer.algorithm.search.BinarySearch;
//import com.naman14.algovisualizer.algorithm.search.LinearSearch;
//import com.naman14.algovisualizer.algorithm.sorting.BubbleSort;
//import com.naman14.algovisualizer.algorithm.sorting.InsertionSort;
//import com.naman14.algovisualizer.algorithm.sorting.QuickSort;
//import com.naman14.algovisualizer.algorithm.sorting.SelectionSort;
//import com.naman14.algovisualizer.algorithm.tree.bst.BSTAlgorithm;
//import com.naman14.algovisualizer.visualizer.AlgorithmVisualizer;
//import com.naman14.algovisualizer.visualizer.ArrayVisualizer;
//import com.naman14.algovisualizer.visualizer.BSTVisualizer;
//import com.naman14.algovisualizer.visualizer.BinarySearchVisualizer;
//import com.naman14.algovisualizer.visualizer.LinkedListControls;
//import com.naman14.algovisualizer.visualizer.LinkedListVisualizer;
//import com.naman14.algovisualizer.visualizer.SortingVisualizer;
//import com.naman14.algovisualizer.visualizer.StackControls;
//import com.naman14.algovisualizer.visualizer.StackVisualizer;
//import com.naman14.algovisualizer.visualizer.graph.DirectedGraphVisualizer;
//import com.naman14.algovisualizer.visualizer.graph.WeightedGraphVisualizer;
//import com.naman14.algovisualizer.visualizer.graph.WeightedGraphVisualizer2;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.kdm.allalgo.algorithm.Algorithm;
import com.example.kdm.allalgo.algorithm.graph.BellmanFordAlgorithm;
import com.example.kdm.allalgo.algorithm.graph.DijkstraAgorithm;
import com.example.kdm.allalgo.algorithm.graph.GraphTraversalAlgorithm;
import com.example.kdm.allalgo.algorithm.list.LinkedList;
import com.example.kdm.allalgo.algorithm.list.Stack;
import com.example.kdm.allalgo.algorithm.search.BinarySearch;
import com.example.kdm.allalgo.algorithm.search.LinearSearch;
import com.example.kdm.allalgo.algorithm.sorting.BubbleSort;
import com.example.kdm.allalgo.algorithm.sorting.InsertionSort;
import com.example.kdm.allalgo.algorithm.sorting.QuickSort;
import com.example.kdm.allalgo.algorithm.sorting.SelectionSort;
import com.example.kdm.allalgo.algorithm.tree.bst.BSTAlgorithm;
import com.example.kdm.allalgo.visualizer.AlgorithmVisualizer;
import com.example.kdm.allalgo.visualizer.ArrayVisualizer;
import com.example.kdm.allalgo.visualizer.BSTVisualizer;
import com.example.kdm.allalgo.visualizer.BinarySearchVisualizer;
import com.example.kdm.allalgo.visualizer.LinkedListControls;
import com.example.kdm.allalgo.visualizer.LinkedListVisualizer;
import com.example.kdm.allalgo.visualizer.SortingVisualizer;
import com.example.kdm.allalgo.visualizer.StackControls;
import com.example.kdm.allalgo.visualizer.StackVisualizer;
import com.example.kdm.allalgo.visualizer.graph.DirectedGraphVisualizer;
import com.example.kdm.allalgo.visualizer.graph.WeightedGraphVisualizer;
import com.example.kdm.allalgo.visualizer.graph.WeightedGraphVisualizer2;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;
import com.roughike.bottombar.OnTabClickListener;

import java.util.ArrayList;
import java.util.List;

public class VisualAlgoFragment extends Fragment {

    FloatingActionButton fab;
    BottomBar bottomBar;
    AppBarLayout appBarLayout;

    LogFragment logFragment;
    CodeFragment codeFragment;
    AlgoDescriptionFragment algoFragment;
    ViewPager viewPager;

    Algorithm algorithm;

    String startCommand = Algorithm.COMMAND_START_ALGORITHM;

    public static VisualAlgoFragment newInstance(String algorithm) {
        VisualAlgoFragment fragment = new VisualAlgoFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Algorithm.KEY_ALGORITHM, algorithm);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_visual_algo, container, false);

        appBarLayout = (AppBarLayout) rootView.findViewById(R.id.app_bar);
        bottomBar = BottomBar.attachShy((CoordinatorLayout) rootView.findViewById(R.id.coordinator), savedInstanceState);
        bottomBar.noNavBarGoodness();
        bottomBar.noTabletGoodness();

        fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        viewPager = (ViewPager) rootView.findViewById(R.id.viewPager);


        bottomBar.setItems(
                new BottomBarTab(R.drawable.ic_wb_incandescent_white_24dp, "Details"),
                new BottomBarTab(R.drawable.ic_short_text_white_24dp, "Execution"),
                new BottomBarTab(R.drawable.ic_code_white_24dp, "Code")
        );

        bottomBar.setOnTabClickListener(new OnTabClickListener() {
            @Override
            public void onTabSelected(int position) {
                viewPager.setCurrentItem(position);
                if (position == 2) {
                    bottomBar.hide();
                }
            }

            @Override
            public void onTabReSelected(int position) {

            }
        });

        logFragment = LogFragment.newInstance();
        codeFragment = CodeFragment.newInstance(getArguments().getString(Algorithm.KEY_ALGORITHM));
        algoFragment = AlgoDescriptionFragment.newInstance(getArguments().getString(Algorithm.KEY_ALGORITHM));

        setupFragment(getArguments().getString(Algorithm.KEY_ALGORITHM));

        return rootView;
    }

    public void setStartCommand(String startCommand) {
        this.startCommand = startCommand;
    }

    public void setupFragment(String algorithmKey) {

        viewPager.setOffscreenPageLimit(3);
        bottomBar.selectTabAtPosition(0, false);
        setupViewPager(viewPager);

        codeFragment.setCode(algorithmKey);
        algoFragment.setCodeDesc(algorithmKey);

        assert algorithmKey != null;

        final AlgorithmVisualizer visualizer;

        appBarLayout.removeAllViewsInLayout();

        View toolbar = LayoutInflater.from(getActivity()).inflate(R.layout.toolbar, appBarLayout, false);
        appBarLayout.addView(toolbar);

        ((AppCompatActivity) getActivity()).setSupportActionBar((Toolbar) toolbar);
        ActionBar ab = ((AppCompatActivity) getActivity()).getSupportActionBar();
        assert ab != null;
        ab.setTitle("");
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        fab.setVisibility(View.VISIBLE);


        switch (algorithmKey) {
            case Algorithm.BINARY_SEARCH:
                visualizer = new BinarySearchVisualizer(getActivity());
                appBarLayout.addView(visualizer);
                algorithm = new BinarySearch((BinarySearchVisualizer) visualizer, getActivity(), logFragment);
                ((BinarySearch) algorithm).setData(DataUtils.createArray(15, true));
                break;
            case Algorithm.LINEAR_SEARCH:
                visualizer = new BinarySearchVisualizer(getActivity());
                appBarLayout.addView(visualizer);
                algorithm = new LinearSearch((BinarySearchVisualizer) visualizer, getActivity(), logFragment);
                ((LinearSearch) algorithm).setData(DataUtils.createArray(15, false));
                break;
            case Algorithm.BUBBLE_SORT:
                visualizer = new SortingVisualizer(getActivity());
                appBarLayout.addView(visualizer);
                algorithm = new BubbleSort((SortingVisualizer) visualizer, getActivity(), logFragment);
                ((BubbleSort) algorithm).setData(DataUtils.createRandomArray(15));
                break;
            case Algorithm.INSERTION_SORT:
                visualizer = new SortingVisualizer(getActivity());
                appBarLayout.addView(visualizer);
                algorithm = new InsertionSort((SortingVisualizer) visualizer, getActivity(), logFragment);
                ((InsertionSort) algorithm).setData(DataUtils.createRandomArray(15));
                break;
            case Algorithm.SELECTION_SORT:
                visualizer = new SortingVisualizer(getActivity());
                appBarLayout.addView(visualizer);
                algorithm = new SelectionSort((SortingVisualizer) visualizer, getActivity(), logFragment);
                ((SelectionSort) algorithm).setData(DataUtils.createRandomArray(15));
                break;
            case Algorithm.QUICKSORT:
                visualizer = new SortingVisualizer(getActivity());
                appBarLayout.addView(visualizer);
                algorithm = new QuickSort((SortingVisualizer) visualizer, getActivity(), logFragment);
                ((QuickSort) algorithm).setData(DataUtils.createRandomArray(15));
                break;
            case Algorithm.BST_SEARCH:
                visualizer = new BSTVisualizer(getActivity());
                appBarLayout.addView(visualizer);
                algorithm = new BSTAlgorithm((BSTVisualizer) visualizer, getActivity(), logFragment);
                ((BSTAlgorithm) algorithm).setData(DataUtils.createBinaryTree());
                break;
            case Algorithm.BST_INSERT:
                visualizer = new BSTVisualizer(getActivity(), 280);
                ArrayVisualizer arrayVisualizer = new ArrayVisualizer(getActivity());
                appBarLayout.addView(visualizer);
                appBarLayout.addView(arrayVisualizer);
                algorithm = new BSTAlgorithm((BSTVisualizer) visualizer, getActivity(), logFragment);
                ((BSTAlgorithm) algorithm).setArrayVisualizer(arrayVisualizer);
                ((BSTAlgorithm) algorithm).setData(DataUtils.createBinaryTree());
                break;
            case Algorithm.LINKED_LIST:
                visualizer = new LinkedListVisualizer(getActivity());
                LinkedListControls controls = new LinkedListControls(getActivity(), bottomBar, fab);
                appBarLayout.addView(visualizer);
                appBarLayout.addView(controls);
                algorithm = new LinkedList((LinkedListVisualizer) visualizer, getActivity(), logFragment);
                ((LinkedList) algorithm).setData(DataUtils.createLinkedList());
                controls.setLinkedList((LinkedList) algorithm);
                break;
            case Algorithm.STACK:
                visualizer = new StackVisualizer(getActivity());
                StackControls stackcontrols = new StackControls(getActivity(), bottomBar, fab);
                appBarLayout.addView(visualizer);
                appBarLayout.addView(stackcontrols);
                algorithm = new Stack(5, (StackVisualizer) visualizer, getActivity(), logFragment);
                ((Stack) algorithm).setData(DataUtils.createStack());
                stackcontrols.setStack((Stack) algorithm);
                fab.setVisibility(View.GONE);
                break;
            case Algorithm.BFS:
            case Algorithm.DFS:
                visualizer = new DirectedGraphVisualizer(getActivity());
                appBarLayout.addView(visualizer);
                algorithm = new GraphTraversalAlgorithm((DirectedGraphVisualizer) visualizer, getActivity(), logFragment);
                ((GraphTraversalAlgorithm) algorithm).setData(DataUtils.createDirectedGraph());
                break;
            case Algorithm.DIJKSTRA:
                visualizer = new WeightedGraphVisualizer2(getActivity());
                appBarLayout.addView(visualizer);
                algorithm = new DijkstraAgorithm((WeightedGraphVisualizer2) visualizer, getActivity(), logFragment);
                ((DijkstraAgorithm) algorithm).setData(DataUtils.createWeightedGraph2(5));
                break;
            case Algorithm.BELLMAN_FORD:
                visualizer = new WeightedGraphVisualizer(getActivity());
                appBarLayout.addView(visualizer);
                algorithm = new BellmanFordAlgorithm((WeightedGraphVisualizer) visualizer, getActivity(), logFragment);
                ((BellmanFordAlgorithm) algorithm).setData(DataUtils.createWeightedGraph(5));
                break;
            case Algorithm.N_QUEENS:
                visualizer = null;
                break;
            default:
                visualizer = null;
        }

        Algorithm.setInterval(Integer.parseInt(PreferenceManager.getDefaultSharedPreferences(getActivity())
                .getString(SettingsFragment.KEY_INTERVAL, "500")));
        algorithm.setStarted(false);
        fab.setImageResource(R.drawable.ic_play_arrow_white_24dp);
        logFragment.clearLog();

        algorithm.setCompletionListener(new AlgoCompletionListener() {
            @Override
            public void onAlgoCompleted() {
                fab.setImageResource(R.drawable.ic_settings_backup_restore_white_24dp);
                if (visualizer != null)
                    visualizer.onCompleted();

            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!algorithm.isStarted()) {
                    algorithm.sendMessage(startCommand);
                    fab.setImageResource(R.drawable.ic_pause_white_24dp);
                    logFragment.clearLog();
                    bottomBar.selectTabAtPosition(1, true);//move to log fragment
                } else {
                    if (algorithm.isPaused()) {
                        algorithm.setPaused(false);
                        fab.setImageResource(R.drawable.ic_pause_white_24dp);
                    } else {
                        algorithm.setPaused(true);
                        fab.setImageResource(R.drawable.ic_play_arrow_white_24dp);
                    }
                }
            }
        });

        View shadow = LayoutInflater.from(getActivity()).inflate(R.layout.shadow, appBarLayout, false);
        appBarLayout.addView(shadow);

    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getChildFragmentManager());
        adapter.addFragment(algoFragment, "Algo");
        adapter.addFragment(logFragment, "Log");
        adapter.addFragment(codeFragment, "Code");
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                bottomBar.selectTabAtPosition(position, false);
                bottomBar.hide();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        private Adapter(FragmentManager fm) {
            super(fm);
        }

        private void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        bottomBar.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}
