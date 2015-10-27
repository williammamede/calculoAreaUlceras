package br.com.calculoareaulceras;

import br.com.calculoareaulceras.model.entity.Paciente;
import br.com.calculoareaulceras.web.service.impl.WebServiceImpl;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("ShowToast")
public class CadastroActivity extends Activity {
	EditText nomePaciente;
	EditText emailPaciente;
	EditText telefonePaciente;
	Paciente paciente;
	Paciente pacienteEditar;
	TextView lblCadastro;

	Button salvar;
	Button cancelar;
	Boolean isEditar = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro);

		
		String emailPassado;

		nomePaciente = (EditText) findViewById(R.id.editTextNome);
		emailPaciente = (EditText) findViewById(R.id.editTextEmail);
		telefonePaciente = (EditText) findViewById(R.id.editTextTelefone);
		lblCadastro = (TextView) findViewById(R.id.textViewCadastro);

		salvar = (Button) findViewById(R.id.buttonSalvarPaciente);
		cancelar = (Button) findViewById(R.id.btnCancelar);
		paciente = new Paciente();
		pacienteEditar = new Paciente();

		// verificar se é cadastro ou edição
		if (getIntent().hasExtra("email")) {
			Bundle extras = getIntent().getExtras();
			isEditar = true;
			emailPassado = extras.getString("email");
			WebServiceImpl webService = new WebServiceImpl();
			pacienteEditar = webService.findPacienteByEmail(emailPassado);
			lblCadastro.setText("Editar");
			nomePaciente.setText(pacienteEditar.getNome());
			emailPaciente.setText(pacienteEditar.getEmail());
			emailPaciente.setEnabled(false);
			telefonePaciente.setText(pacienteEditar.getTelefone());
		}

		salvar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				efetuarCadastroPaciente();

				Boolean dadosOk = false;

				WebServiceImpl webService = new WebServiceImpl();
				if (!isEditar) {
					try {
						dadosOk = webService.CadastroPaciente(paciente);
					} catch (Exception e) {
						// TODO: handle exception
					}

					if (dadosOk) {
						Toast.makeText(getApplicationContext(),
								"Paciente Inserido com sucesso",
								Toast.LENGTH_LONG);
						finish();
					} else
						Toast.makeText(getApplicationContext(),
								"Erro ao inserir paciente tente novamente",
								Toast.LENGTH_LONG);
				}
				else{
					try {
						dadosOk = webService.EditarPaciente(paciente);
					} catch (Exception e) {
						// TODO: handle exception
					}

					if (dadosOk) {
						Toast.makeText(getApplicationContext(),
								"Paciente Alterado com sucesso",
								Toast.LENGTH_LONG);
						finish();
					} else
						Toast.makeText(getApplicationContext(),
								"Erro ao Alterar paciente tente novamente",
								Toast.LENGTH_LONG);
				}
			}
		});

		cancelar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Cancelar deve fechar essa activity
				finish();
			}
		});
	}
	public void efetuarCadastroPaciente() {
		paciente.setEmail(emailPaciente.getText().toString());
		paciente.setNome(nomePaciente.getText().toString());
		paciente.setTelefone(telefonePaciente.getText().toString());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cadastro, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		return super.onOptionsItemSelected(item);
	}
}
