package br.com.calculoareaulceras.web.service.impl;

import java.util.ArrayList;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.util.Log;
import br.com.calculoareaulceras.model.Constants;
import br.com.calculoareaulceras.model.entity.Medico;
import br.com.calculoareaulceras.model.entity.Paciente;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class WebServiceImpl {

	public WebServiceImpl() {
		super();
	}

	public Medico findMedByLoginSenha(String login, String senha) {
		Gson gson = new Gson();

		/** Web Service **/
		SoapObject request = new SoapObject(Constants.NAMESPACE,
				Constants.FIND_MED_METHOD);

		/** Criando objeto String da classe Pessoa */
		Medico pessoaEnviada = new Medico(0, "", "", login, senha, "");
		/**
		 * Cada property deve ser adicionada com identifica��o seguindo o padr�o
		 * de nome "arg0", "arg1", "arg2"...
		 */
		request.addProperty("arg0", gson.toJson(pessoaEnviada));

		/**
		 * Instru��es respons�veis por fazer a requisi��o ao Web Service via
		 * SOAP
		 */
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.setOutputSoapObject(request);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(
				Constants.WSDL_URL);

		Medico medicoRecebido = new Medico();

		try {
			androidHttpTransport.call(Constants.FIND_MED_ACTION, envelope);

			SoapPrimitive resultsRequestSOAP = (SoapPrimitive) envelope
					.getResponse();

			medicoRecebido = gson.fromJson(resultsRequestSOAP.toString(),
					Medico.class);

		} catch (Exception e) {
			for (StackTraceElement ste : e.getStackTrace()) {
				Log.e("ERRO", ste.toString());
			}
		}

		return medicoRecebido;
	}

	public Boolean CadastroPaciente(Paciente pessoaEnviada) {
		Gson gson = new Gson();
		boolean sucesso = false;

		/** Web Service **/
		SoapObject request = new SoapObject(Constants.NAMESPACE,
				Constants.INSERIR_PACIENTE_METHOD);

		/**
		 * Cada property deve ser adicionada com identifica��o seguindo o padr�o
		 * de nome "arg0", "arg1", "arg2"...
		 */
		request.addProperty("arg0", gson.toJson(pessoaEnviada));

		/**
		 * Instru��es respons�veis por fazer a requisi��o ao Web Service via
		 * SOAP
		 */
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.setOutputSoapObject(request);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(
				Constants.WSDL_URL);

		Paciente pacienteRecebido = new Paciente();

		try {
			androidHttpTransport.call(Constants.INSERIR_PACIENTE_ACTION,
					envelope);

			SoapPrimitive resultsRequestSOAP = (SoapPrimitive) envelope
					.getResponse();

			sucesso = Boolean.parseBoolean(resultsRequestSOAP.toString());

		} catch (Exception e) {
			for (StackTraceElement ste : e.getStackTrace()) {
				Log.e("ERRO", ste.toString());
			}
		}

		return sucesso;
	}

	public ArrayList<Paciente> findAllPacientes() {
		Gson gson = new Gson();

		/** Web Service **/
		SoapObject request = new SoapObject(Constants.NAMESPACE,
				Constants.FIND_ALL_PACIENTES_METHOD);
		/**
		 * Instru��es respons�veis por fazer a requisi��o ao Web Service via
		 * SOAP
		 */
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.setOutputSoapObject(request);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(
				Constants.WSDL_URL);

		ArrayList<Paciente> pacientes = new ArrayList<Paciente>();

		try {
			androidHttpTransport.call(Constants.FIND_ALL_PACIENTES_ACTION,
					envelope);

			SoapPrimitive resultsRequestSOAP = (SoapPrimitive) envelope
					.getResponse();

			pacientes = gson.fromJson(resultsRequestSOAP.toString(),
					new TypeToken<ArrayList<Paciente>>() {
					}.getType());
			;

		} catch (Exception e) {
			for (StackTraceElement ste : e.getStackTrace()) {
				Log.e("ERRO", ste.toString());
			}
		}

		return pacientes;
	}

	public Paciente findPacienteByEmail(String email) {
		Gson gson = new Gson();

		/** Web Service **/
		SoapObject request = new SoapObject(Constants.NAMESPACE,
				Constants.FIND_PACIENTE_EMAIL_METHOD);
		/**
		 * Instru��es respons�veis por fazer a requisi��o ao Web Service via
		 * SOAP
		 */
		Paciente pc  =new Paciente();
		pc.setEmail(email);
		request.addProperty("arg0", gson.toJson(pc));
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.setOutputSoapObject(request);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(
				Constants.WSDL_URL);

		Paciente paciente = new Paciente();

		try {
			androidHttpTransport.call(Constants.FIND_PACIENTE_EMAIL_ACTION,
					envelope);

			SoapPrimitive resultsRequestSOAP = (SoapPrimitive) envelope
					.getResponse();

			paciente = gson.fromJson(resultsRequestSOAP.toString(),
					Paciente.class);

		} catch (Exception e) {
			for (StackTraceElement ste : e.getStackTrace()) {
				Log.e("ERRO", ste.toString());
			}
		}

		return paciente;
	}

	public boolean removerPaciente(String email) {
		Gson gson = new Gson();

		/** Web Service **/
		SoapObject request = new SoapObject(Constants.NAMESPACE,
				Constants.EXCLUIR_PACIENTE_METHOD);
		/**
		 * Instru��es respons�veis por fazer a requisi��o ao Web Service via
		 * SOAP
		 */
		Paciente pc  =new Paciente();
		pc.setEmail(email);
		request.addProperty("arg0", gson.toJson(pc));
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.setOutputSoapObject(request);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(
				Constants.WSDL_URL);

		try {
			androidHttpTransport.call(Constants.FIND_PACIENTE_EMAIL_ACTION,
					envelope);

			SoapPrimitive resultsRequestSOAP = (SoapPrimitive) envelope
					.getResponse();
			Integer nRegistros = gson.fromJson(resultsRequestSOAP.toString(), Integer.class);
			if(nRegistros>0)
				return true;

		} catch (Exception e) {
			for (StackTraceElement ste : e.getStackTrace()) {
				Log.e("ERRO", ste.toString());
			}
		}

		return false;
	}
	
	public Boolean EditarPaciente(Paciente pessoaEnviada) {
		Gson gson = new Gson();
		boolean sucesso = false;

		/** Web Service **/
		SoapObject request = new SoapObject(Constants.NAMESPACE,
				Constants.EDITAR_PACIENTE_METHOD);

		/**
		 * Cada property deve ser adicionada com identifica��o seguindo o padr�o
		 * de nome "arg0", "arg1", "arg2"...
		 */
		request.addProperty("arg0", gson.toJson(pessoaEnviada));

		/**
		 * Instru��es respons�veis por fazer a requisi��o ao Web Service via
		 * SOAP
		 */
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.setOutputSoapObject(request);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(
				Constants.WSDL_URL);

		Paciente pacienteRecebido = new Paciente();

		try {
			androidHttpTransport.call(Constants.EDITAR_PACIENTE_ACTION,
					envelope);

			SoapPrimitive resultsRequestSOAP = (SoapPrimitive) envelope
					.getResponse();

			sucesso = Boolean.parseBoolean(resultsRequestSOAP.toString());

		} catch (Exception e) {
			for (StackTraceElement ste : e.getStackTrace()) {
				Log.e("ERRO", ste.toString());
			}
		}

		return sucesso;
	}
	
	public Boolean CadastroMedico(Medico medicoEnviado) {
		Gson gson = new Gson();
		boolean sucesso = false;

		/** Web Service **/
		SoapObject request = new SoapObject(Constants.NAMESPACE,
				Constants.INSERIR_MEDICO_METHOD);

		/**
		 * Cada property deve ser adicionada com identifica��o seguindo o padr�o
		 * de nome "arg0", "arg1", "arg2"...
		 */
		request.addProperty("arg0", gson.toJson(medicoEnviado));

		/**
		 * Instru��es respons�veis por fazer a requisi��o ao Web Service via
		 * SOAP
		 */
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.setOutputSoapObject(request);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(
				Constants.WSDL_URL);

		Paciente pacienteRecebido = new Paciente();

		try {
			androidHttpTransport.call(Constants.INSERIR_MEDICO_ACTION,
					envelope);

			SoapPrimitive resultsRequestSOAP = (SoapPrimitive) envelope
					.getResponse();

			sucesso = Boolean.parseBoolean(resultsRequestSOAP.toString());

		} catch (Exception e) {
			for (StackTraceElement ste : e.getStackTrace()) {
				Log.e("ERRO", ste.toString());
			}
		}

		return sucesso;
	}


}
