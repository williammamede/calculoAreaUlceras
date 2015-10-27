package br.com.calculoareaulceras.adapters;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import br.com.calculoareaulceras.R;

/**
 * 
 * 
 * @author william
 *
 */

// ADAPTER PARA COLOCAR OS PACIENTES NA LISTVIEW NA TELA PRINCIPAL
public class PacientesAdapter extends ArrayAdapter<HashMap<String, String>> {
	private final Context context;
	private final ArrayList<HashMap<String, String>> pacientes;

	public PacientesAdapter(Context context,
			ArrayList<HashMap<String, String>> objects) {
		super(context, R.layout.paciente_lista, objects);
		this.context = context;
		this.pacientes = new ArrayList<HashMap<String, String>>();

		for (HashMap<String, String> paciente : objects) {// PERCORRER CADA
															// PACIENTE QUE
															// EXITE E
															// ADICIONALO A
															// LISTA DE
															// PACIENTES
			this.pacientes.add(paciente);
		}

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View rowView = inflater.inflate(R.layout.paciente_lista, parent, false);

		TextView txtNome = (TextView) rowView
				.findViewById(R.id.textViewNomePaciente);

		TextView txtQtd = (TextView) rowView
				.findViewById(R.id.textViewNumeroFotos);

		String nome = pacientes.get(position).get("nome");

		String qts = pacientes.get(position).get("qtd");

		txtNome.setText(nome);
		txtQtd.setText(qts);

		return rowView;
	}

}
