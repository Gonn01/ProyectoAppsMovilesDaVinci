// HomeFragment.java
package com.example.proyectoappsmovilesdavinci.ui.fragments;

import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoappsmovilesdavinci.R;
import com.example.proyectoappsmovilesdavinci.dtos.FinancialEntityHomeDto;
import com.example.proyectoappsmovilesdavinci.dtos.PurchaseHomeDto;
import com.example.proyectoappsmovilesdavinci.ui.DashboardActivity;
import com.example.proyectoappsmovilesdavinci.ui.adapters.dashboard.HomeListAdapter;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeFragment extends Fragment {

    private HomeListAdapter listAdapter;
    private DashboardActivity main;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        main = (DashboardActivity) requireActivity();
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.rvHome);
        MaterialButton botonCrearEntidad = view.findViewById(R.id.crear_entidad_financiera);
        MaterialButton botonCrearCompra = view.findViewById(R.id.crear_compra);

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        listAdapter = new HomeListAdapter();
        recyclerView.setAdapter(listAdapter);

        botonCrearEntidad.setOnClickListener(v -> dialogCrearEntidad());
        botonCrearCompra.setOnClickListener(v -> dialogCrearCompra());

        refreshList();
    }

    private void dialogCrearEntidad() {
        final EditText input = new EditText(requireContext());
        input.setHint("Nombre de la entidad");
        input.setInputType(InputType.TYPE_CLASS_TEXT);

        new MaterialAlertDialogBuilder(requireContext())
                .setTitle("Nueva Entidad Financiera")
                .setView(input)
                .setPositiveButton("Crear", (d, w) -> {
                    String name = input.getText().toString().trim();
                    if (name.isEmpty()) {
                        Toast.makeText(requireContext(),
                                "El nombre no puede estar vacío", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    FinancialEntityHomeDto fe = new FinancialEntityHomeDto(main.nextEntityId(), name);
                    main.addEntidad(fe);
                    Toast.makeText(requireContext(),
                            "Entidad creada: " + fe.getName(), Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }

    private void dialogCrearCompra() {
        if (main.getEntidades().isEmpty()) {
            Toast.makeText(requireContext(),
                    "Primero creá una entidad financiera", Toast.LENGTH_SHORT).show();
            return;
        }

        LinearLayout layout = new LinearLayout(requireContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        int pad = (int) (16 * getResources().getDisplayMetrics().density);
        layout.setPadding(pad, pad, pad, pad);

        TextView lblEntidad = new TextView(requireContext());
        lblEntidad.setText("Entidad");
        Spinner spEntidad = new Spinner(requireContext());
        ArrayAdapter<FinancialEntityHomeDto> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                main.getEntidades()
        );
        spEntidad.setAdapter(adapter);

        TextView lblNombre = new TextView(requireContext());
        lblNombre.setText("Nombre de la compra");
        EditText txtNombre = new EditText(requireContext());
        txtNombre.setHint("Ej: Spotify");

        TextView lblMonto = new TextView(requireContext());
        lblMonto.setText("Monto");
        EditText txtMonto = new EditText(requireContext());
        txtMonto.setHint("1999.99");
        txtMonto.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

        layout.addView(lblEntidad);
        layout.addView(spEntidad);
        layout.addView(lblNombre);
        layout.addView(txtNombre);
        layout.addView(lblMonto);
        layout.addView(txtMonto);

        new MaterialAlertDialogBuilder(requireContext())
                .setTitle("Nueva Compra")
                .setView(layout)
                .setPositiveButton("Guardar", (d, w) -> {
                    FinancialEntityHomeDto fe = (FinancialEntityHomeDto) spEntidad.getSelectedItem();
                    String nombre = txtNombre.getText().toString().trim();
                    String montoStr = txtMonto.getText().toString().trim();

                    if (nombre.isEmpty() || montoStr.isEmpty()) {
                        Toast.makeText(requireContext(),
                                "Completá nombre y monto", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    double monto;
                    try {
                        monto = Double.parseDouble(montoStr);
                    } catch (NumberFormatException e) {
                        Toast.makeText(requireContext(),
                                "Monto inválido", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    PurchaseHomeDto p = new PurchaseHomeDto(main.nextPurchaseId(),monto,nombre, fe.getId()  );
                    main.addCompra(p);

                    Toast.makeText(requireContext(),
                            "Compra \"" + p.getName() + "\" en " + fe.getName() + " ($" + p.getAmount() + ")",
                            Toast.LENGTH_SHORT).show();

                    refreshList();
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }
    private void refreshList() {
        Map<Integer, List<PurchaseHomeDto>> byEntity = new HashMap<>();
        for (PurchaseHomeDto p : main.getCompras()) {
            List<PurchaseHomeDto> list = byEntity.computeIfAbsent(p.getFinancialEntityId(), k -> new ArrayList<>());
            list.add(p);
        }

        List<HomeListAdapter.Row> rows = new ArrayList<>();
        for (FinancialEntityHomeDto fe : main.getEntidades()) {
            List<PurchaseHomeDto> list = byEntity.get(fe.getId());
            if (list == null || list.isEmpty()) continue;
            rows.add(new HomeListAdapter.EntityHeader(fe.getId(), fe.getName()));
            for (PurchaseHomeDto p : list) {
                rows.add(new HomeListAdapter.PurchaseRow(p.getId(), p.getName(), p.getAmount()));
            }
        }

        listAdapter.setRows(rows);
    }
}
