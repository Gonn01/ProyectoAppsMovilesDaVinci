package com.example.proyectoappsmovilesdavinci.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoappsmovilesdavinci.R;
import com.example.proyectoappsmovilesdavinci.ui.DashboardActivity;
import com.example.proyectoappsmovilesdavinci.ui.adapters.dashboard.FinancialEntityListAdapter;

public class FinancialEntitiesFragment extends Fragment {
    private DashboardActivity main;

    private FinancialEntityListAdapter listAdapter;
    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        main = (DashboardActivity) requireActivity();
        return inflater.inflate(R.layout.fragment_financial_entities, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView rv = view.findViewById(R.id.rvFinancialEntities);
        rv.setLayoutManager(new LinearLayoutManager(requireContext()));
        listAdapter = new FinancialEntityListAdapter();
        rv.setAdapter(listAdapter);
        refreshList();
    }
    private void refreshList() {
        listAdapter.setEntities(main.getEntidades());
    }

}
