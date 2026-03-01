package com.example.projetws.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.projetws.R;
import com.example.projetws.beans.Etudiant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EtudiantAdapter extends RecyclerView.Adapter<EtudiantAdapter.EtudiantViewHolder> {

    private List<Etudiant> etudiants;
    private Context context;
    private RequestQueue requestQueue;
    private static final String deleteUrl = "http://10.0.2.2/projet/ws/deleteEtudiant.php";

    public EtudiantAdapter(Context context, List<Etudiant> etudiants) {
        this.context = context;
        this.etudiants = etudiants;
        this.requestQueue = Volley.newRequestQueue(context);
    }

    @NonNull
    @Override
    public EtudiantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.etudiant_item, parent, false);
        return new EtudiantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EtudiantViewHolder holder, int position) {
        Etudiant etudiant = etudiants.get(position);
        holder.id.setText(String.valueOf(etudiant.getId()));
        holder.nom.setText(etudiant.getNom() + " " + etudiant.getPrenom());
        holder.ville.setText(etudiant.getVille() + " (" + etudiant.getSexe() + ")");

        holder.delete.setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Confirmation")
                    .setMessage("Voulez-vous vraiment supprimer cet étudiant ?")
                    .setPositiveButton("Oui", (dialog, which) -> deleteEtudiant(etudiant.getId(), position))
                    .setNegativeButton("Non", null)
                    .show();
        });
    }

    private void deleteEtudiant(int id, int position) {
        StringRequest request = new StringRequest(Request.Method.POST, deleteUrl,
                response -> {
                    etudiants.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, etudiants.size());
                    Toast.makeText(context, "Étudiant supprimé", Toast.LENGTH_SHORT).show();
                },
                error -> Log.e("VOLLEY", "Erreur suppression : " + error.getMessage())) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", String.valueOf(id));
                return params;
            }
        };
        requestQueue.add(request);
    }

    @Override
    public int getItemCount() {
        return etudiants.size();
    }

    public static class EtudiantViewHolder extends RecyclerView.ViewHolder {
        TextView id, nom, ville;
        ImageButton delete;

        public EtudiantViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.idEtudiant);
            nom = itemView.findViewById(R.id.nomEtudiant);
            ville = itemView.findViewById(R.id.villeEtudiant);
            delete = itemView.findViewById(R.id.deleteButton);
        }
    }
}
