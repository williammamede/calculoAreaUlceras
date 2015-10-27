package br.com.calculoareaulceras.activitys;

import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import br.com.calculoareaulceras.CadastroUserActivity;
import br.com.calculoareaulceras.R;
import br.com.calculoareaulceras.model.entity.Medico;
import br.com.calculoareaulceras.web.service.impl.WebServiceImpl;

public class MainActivity extends Activity {

	EditText edtLogin;// edit text onde vai o login do usuario
	EditText edtSenha;// edit text onde vai a senha entrada pelo usuario
	TextView error;// label que aparece quando da um erro

	SharedPreferences prefs;
	Editor ed;

	Button btnEntrar;
	Button btnCadastrar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		prefs = getSharedPreferences("ulcerasPrefs", Context.MODE_PRIVATE);
		Map<String, ?> map;
		try {
			map = prefs.getAll();
			if (map.get("user") != null) {
				finish();
				Intent proximaTela = new Intent(getApplicationContext(),
						LoggedActivity.class);// declara a tela logado
				startActivity(proximaTela);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*
		 * PEGA OS VALORES DOS EDIT TEXT
		 */
		edtLogin = (EditText) findViewById(R.id.editTextLogin);
		edtSenha = (EditText) findViewById(R.id.editTextSenha);
		error = (TextView) findViewById(R.id.error);
		error.setVisibility(View.INVISIBLE);

		// BOTAO ENTRAR
		btnEntrar = (Button) findViewById(R.id.buttonEntrar);

		// BOTÃO CADASTRAR
		btnCadastrar = (Button) findViewById(R.id.buttonCadastroUser);

		//EVENTO DO CADASTRO
		
		btnCadastrar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// Chamar a Activity para cadastrar medico
				Intent telaCadastro = new Intent(getApplicationContext(),
						CadastroUserActivity.class);
				
				startActivity(telaCadastro);
				
			}
		});
		
		// EVENTO AO SER GERADO AO CLICAR NO BOTAO
		btnEntrar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				error.setVisibility(View.INVISIBLE);// setar erro invisivel
				// CONDIÇÃO PARA VERIFICAR SE EXISTEM CAMPOS EM BRANCO

				if (edtLogin.getText().toString() == null
						|| edtLogin.getText().toString().equals("")
						|| edtSenha.getText().toString() == null
						|| edtSenha.getText().toString().equals("")) {
					// EXISTEM CAMPOS EM BRANCO EXECUTAR
					error.setVisibility(View.VISIBLE);// SETAR ERRO VISIVEL

				} else
					efetuarLogin(edtLogin.getText().toString(), edtSenha
							.getText().toString());// efetua o login
			}

			// metodo que será executado quando for efetuar o login
			private void efetuarLogin(String login, String senha) {
				Boolean dadosOk = false;

				WebServiceImpl webService = new WebServiceImpl();

				Medico medicoRecebido = webService.findMedByLoginSenha(login,
						senha);

				if (medicoRecebido != null) {
					if (medicoRecebido.getEmail() != null) {
						Toast.makeText(getBaseContext(),
								"Login efetuado com sucesso", Toast.LENGTH_LONG)
								.show();
						dadosOk = true;
					} else {
						Toast.makeText(getBaseContext(),
								getString(R.string.erro_ws), Toast.LENGTH_LONG)
								.show();
					}
				} else {
					Toast.makeText(getBaseContext(),
							getString(R.string.erro_ws), Toast.LENGTH_LONG)
							.show();
				}

				if (dadosOk) {// caso os dados de login forem corretos, navegar
								// para a outra tela
					finish();
					Intent proximaTela = new Intent(getApplicationContext(),
							LoggedActivity.class);// declara a tela logado

					/**
					 * 
					 * COLOCAR LOGICA SE FOR PASSAR ALGUM VALOR PARA OUTRA
					 * ACTIVITY
					 * 
					 */
					// proximaTela.putExtra("login", login);

					prefs = getSharedPreferences("ulcerasPrefs",
							Context.MODE_PRIVATE);
					ed = prefs.edit();
					// salvando informações de login do usuario na memoria
					// interna para saber q ele esta logado
					ed.putString("user", login);
					// Grava efetivamente as alterações.
					ed.commit();

					startActivity(proximaTela);// vai para a proxima tela
				} else {
					error.setVisibility(View.VISIBLE);// SETAR ERRO VISIVEL
				}

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
