package br.com.calculoareaulceras;

import br.com.calculoareaulceras.model.entity.Medico;
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
public class CadastroUserActivity extends Activity {
	Button btnCancelar;
	Button btnSalvar;

	EditText nome;
	EditText email;
	EditText login;
	EditText senha;
	EditText telefone;
	Medico medicoInserir;

	TextView errorMsg;// label que aparece quando da um erro

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_cadastro_user);
		btnCancelar = (Button) findViewById(R.id.btnCancelarCad);// cancelar o
																	// cadastro
																	// de medico
		btnSalvar = (Button) findViewById(R.id.btnSalvarMedico);

		nome = (EditText) findViewById(R.id.editTextNomeMedico);
		email = (EditText) findViewById(R.id.editTextEmailMedico);
		login = (EditText) findViewById(R.id.editTextLoginMedico);
		senha = (EditText) findViewById(R.id.editTextSenhaMedico);
		telefone = (EditText) findViewById(R.id.editTextTelefoneMedico);

		errorMsg = (TextView) findViewById(R.id.errorMsg);
		errorMsg.setVisibility(View.INVISIBLE);

		medicoInserir = new Medico();

		btnCancelar.setOnClickListener(new View.OnClickListener() {// enveto de
																	// qdo
																	// cancela

					@Override
					public void onClick(View v) {
						finish();
					}
				});

		btnSalvar.setOnClickListener(new View.OnClickListener() {// evendo de
					// salvar os
					// medicos

					@Override
					public void onClick(View v) {
						if (!(nome.getText().toString().equals("")
								&& email.getText().toString().equals("")
								&& login.getText().toString().equals("")
								&& senha.getText().toString().equals("") 
								&& telefone.getText().toString().equals(""))) {
							if (senha.getText().length() > 6) {// senha deve ter
																// mais de 6
																// caracteres
								// dados ok.. fazer cadastro
								
								medicoInserir.setEmail(email.getText().toString());
								medicoInserir.setNome(nome.getText().toString());
								medicoInserir.setLogin(login.getText().toString());
								medicoInserir.setSenha(senha.getText().toString());
								medicoInserir.setTelefone(telefone.getText().toString());
								
								Boolean dadosOk = false;
								
								WebServiceImpl webService = new WebServiceImpl();

								dadosOk = webService.CadastroMedico(medicoInserir);
								
								if (dadosOk) {
									Toast.makeText(getApplicationContext(),
											"Medico Inserido com sucesso",
											Toast.LENGTH_LONG);
									finish();
								} else
									Toast.makeText(getApplicationContext(),
											"Erro ao inserir Medico tente novamente",
											Toast.LENGTH_LONG);
							} else {
								// erro senha
								errorMsg.setText("A senha deve conter mais de 6 caracteres");
								errorMsg.setVisibility(View.VISIBLE);
							}

						} else {
							// erro nos dados
							errorMsg.setText(R.string.labelErrorCad);
							errorMsg.setVisibility(View.VISIBLE);
						}
					}
				});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cadastro_user, menu);
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
