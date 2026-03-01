package com.example.projetws;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.projetws.adapter.EtudiantAdapter;
import com.example.projetws.beans.Etudiant;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ListEtudiant extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EtudiantAdapter adapter;
    private List<Etudiant> etudiants;
    private RequestQueue requestQueue;
    private static final String loadUrl = "http://10.0.2.2/projet/ws/loadEtudiant.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_etudiant);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        etudiants = new ArrayList<>();
        adapter = new EtudiantAdapter(this, etudiants);
        recyclerView.setAdapter(adapter);

        requestQueue = Volley.newRequestQueue(this);
        loadEtudiants();
    }

    private void loadEtudiants() {
        StringRequest request = new StringRequest(Request.Method.POST, loadUrl,
                response -> {
                    Log.d("RESPONSE", response);
                    Type type = new TypeToken<Collection<Etudiant>>(){}.getType();
                    Collection<Etudiant> loadedEtudiants = new Gson().fromJson(response, type);
                    etudiants.clear();
                    etudiants.addAll(loadedEtudiants);
                    adapter.notifyDataSetChanged();
                },
                error -> Log.e("VOLLEY", "Erreur chargement : " + error.getMessage()));
        requestQueue.add(request);
    }
}
