package br.com.calculoareaulceras;

import java.util.ArrayList;

import br.com.calculoareaulceras.model.entity.Paciente;
import br.com.calculoareaulceras.web.service.impl.WebServiceImpl;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class PacienteActivity extends Activity {
	Paciente paciente;
	TextView titulo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_paciente);
		Bundle extras;

		titulo = (TextView) findViewById(R.id.titulo);
		titulo.setText("");
		paciente = new Paciente();
		if (getIntent().hasExtra("email_paciente")) {
			extras = getIntent().getExtras();
			paciente = findPacienteByEmail(extras.getString("email_paciente"));
			titulo.setText(paciente.getNome());
		}
	}

	public Paciente findPacienteByEmail(String email) {
		WebServiceImpl webService = new WebServiceImpl();

		Paciente pCiente = webService.findPacienteByEmail(email);
		return pCiente;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.paciente, menu);
		return true;
	}

	@Override
	protected void onResume() {
		if (paciente != null) {
			paciente = findPacienteByEmail(paciente.getEmail());
			titulo.setText(paciente.getNome());
		}

		super.onResume();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_remover) {
			WebServiceImpl webService = new WebServiceImpl();
			boolean removeu = webService.removerPaciente(paciente.getEmail());
			if (removeu) {
				finish();
				Toast.makeText(getBaseContext(),
						"Paciente Removido com Sucesso", Toast.LENGTH_LONG)
						.show();

			}
		}
		if (id == R.id.action_editar) {
			Intent cadastroTela = new Intent(getApplicationContext(),
					CadastroActivity.class);
			cadastroTela.putExtra("email", paciente.getEmail());
			startActivity(cadastroTela);// vai para a proxima tela
		}
		return super.onOptionsItemSelected(item);
	}
}
