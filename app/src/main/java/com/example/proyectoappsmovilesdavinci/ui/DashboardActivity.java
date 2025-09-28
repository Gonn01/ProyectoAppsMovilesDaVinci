package com.example.proyectoappsmovilesdavinci.ui;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.proyectoappsmovilesdavinci.R;
import com.example.proyectoappsmovilesdavinci.dtos.FinancialEntityHomeDto;
import com.example.proyectoappsmovilesdavinci.dtos.PurchaseHomeDto;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {
    private final List<FinancialEntityHomeDto> entidades = new ArrayList<>();
    private final List<PurchaseHomeDto> compras = new ArrayList<>();
    private int nextEntityId = 1;
    private int nextPurchaseId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }
    public List<FinancialEntityHomeDto> getEntidades() {
        return entidades;
    }
    public void addEntidad(FinancialEntityHomeDto entidad) {
        entidades.add(entidad);
    }
    public List<PurchaseHomeDto> getCompras() {
        return compras;
    }

    public void addCompra(PurchaseHomeDto compra) {
        compras.add(compra);
    }

    public int nextEntityId() {
        return nextEntityId++;
    }

    public int nextPurchaseId() {
        return nextPurchaseId++;
    }
}