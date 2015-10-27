package br.com.calculoareaulceras.activitys;

import java.util.ArrayList;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import br.com.calculoareaulceras.CadastroActivity;
import br.com.calculoareaulceras.PacienteActivity;
import br.com.calculoareaulceras.R;
import br.com.calculoareaulceras.adapters.PacientesAdapter;
import br.com.calculoareaulceras.model.entity.Medico;
import br.com.calculoareaulceras.model.entity.Paciente;
import br.com.calculoareaulceras.web.service.impl.WebServiceImpl;

@SuppressLint("ShowToast")
public class LoggedActivity extends Activity {
	ListView listaDePacientes;// o listview onde serao mostrados os pacientes
								// logados no sistema
	Button btnCadastrar;
	ArrayList<Paciente> listaPaciente = new ArrayList<Paciente>();

	SharedPreferences prefs;
	Editor ed;
	private Menu menu;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logged);

		listaDePacientes = (ListView) findViewById(R.id.listViewPacientes);
		btnCadastrar = (Button) findViewById(R.id.buttonCadastro);
		/*
		 * TESTE DE LISTA DE HASHMAP PARA ADICIONAR OS PACIENTES TROCAR O QUE
		 * TEM AQUI PELA BUSCA NO BANCO
		 */
		prefs = getSharedPreferences("ulcerasPrefs", Context.MODE_PRIVATE);
		listaPaciente = findPacientes();
		ArrayList<HashMap<String, String>> pacientes = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> paciente = new HashMap<String, String>();

		for (Paciente p : listaPaciente) {
			paciente = new HashMap<String, String>();
			paciente.put("nome", p.getNome().toString());
			paciente.put("qtd", "0");
			pacientes.add(paciente);
		}

		PacientesAdapter adapter = new PacientesAdapter(
				getApplicationContext(), pacientes);
		listaDePacientes.setAdapter(adapter);

		// listaDePacientes.setAdapter(new
		// PacientesAdapter(getApplicationContext(), resource, pacientes));
		/*
		 * FIM TESTE
		 */

		// botão qdo vai cadastrar pacientes
		btnCadastrar.setOnClickListener(new View.OnClickListener() {// quando
					// clica
					// para
					// cadastrar

					@Override
					public void onClick(View v) {
						// abrir a actibity de cadastro

						Intent cadastroTela = new Intent(
								getApplicationContext(), CadastroActivity.class);// declara
																					// a
																					// tela
																					// logado

						/**
						 * colocar para entrar na tela de cadastro
						 */

						startActivity(cadastroTela);// vai para a proxima tela

					}
				});
		Toast.makeText(getApplicationContext(), "VC nao CLICOU",
				Toast.LENGTH_LONG);
		listaDePacientes.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent pacienteTela = new Intent(getApplicationContext(),
						PacienteActivity.class);
				pacienteTela.putExtra("email_paciente",
						listaPaciente.get(position).getEmail().toString());

				startActivity(pacienteTela);// vai para a proxima tela

			}
		});
	}

	public ArrayList<Paciente> findPacientes() {
		WebServiceImpl webService = new WebServiceImpl();

		ArrayList<Paciente> listaPacientes = webService.findAllPacientes();
		return listaPacientes;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.logged, menu);

		return true;
	}

	@Override
	public void onResume() {

		listaPaciente = findPacientes();
		ArrayList<HashMap<String, String>> pacientes = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> paciente = new HashMap<String, String>();

		for (Paciente p : listaPaciente) {
			paciente = new HashMap<String, String>();
			paciente.put("nome", p.getNome().toString());
			paciente.put("qtd", "0");
			pacientes.add(paciente);
		}

		PacientesAdapter adapter = new PacientesAdapter(
				getApplicationContext(), pacientes);
		listaDePacientes.setAdapter(adapter);
		super.onResume();

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_sair) {
			prefs = getSharedPreferences("ulcerasPrefs", Context.MODE_PRIVATE);
			ed = prefs.edit();
			// salvando informações de login do usuario na memoria interna para
			// saber q ele esta logado
			ed.clear();
			// Grava efetivamente as alterações.
			ed.commit();
			finish();
			return true;
		}

			
		return super.onOptionsItemSelected(item);
	}
}
