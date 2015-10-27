package br.com.calculoareaulceras.model;

public class Constants {

	/**
	 * Namespace definido no servidor
	 */
	public static final String NAMESPACE = "http://pessoabd_webservice/";

	/**
	 * Endere�o do WSDL gerado pelo servidor
	 */
	public static String WSDL_URL = "http://192.168.0.115:8080/pessoa?wsdl";

	/**
	 * Nome dos m�todos anotados com @WebMethod na interface de comunica��o com
	 * o servidor (PessoaBD)
	 */
	
	public static final String FIND_MED_METHOD = "findMedicoByLoginSenha";
	public static final String FIND_ALL_PACIENTES_METHOD= "findAllPacientes";
	public static final String FIND_PACIENTE_EMAIL_METHOD= "findPacienteByEmail";
	public static final String INSERIR_PACIENTE_METHOD = "inserirPaciente";
	public static final String INSERIR_MEDICO_METHOD = "inserirMedico";
	public static final String EDITAR_PACIENTE_METHOD = "editarPaciente";
	public static final String INSERIR_PESSOA_METHOD = "inserirPessoa";
	public static final String BUSCAR_PESSOA_METHOD = "buscarPessoa";
	public static final String LISTAR_PESSOA_METHOD = "listarPessoa";
	public static final String EXCLUIR_PESSOA_METHOD = "excluirPessoa";
	public static final String EXCLUIR_PACIENTE_METHOD = "excluirPaciente";

	/**
	 * Endere�os para se fazer a requisi��o via protocolo SOAP
	 */
	public static final String FIND_MED_ACTION = "\"http://pessoabd_webservice/findMedicoByLoginSenha\"";
	public static final String FIND_ALL_PACIENTES_ACTION = "\"http://pessoabd_webservice/findAllPacientes\"";
	public static final String FIND_PACIENTE_EMAIL_ACTION = "\"http://pessoabd_webservice/findPacienteByEmail\"";
	public static final String INSERIR_PACIENTE_ACTION = "\"http://pessoabd_webservice/inserirPaciente\"";
	public static final String INSERIR_MEDICO_ACTION = "\"http://pessoabd_webservice/inserirMedico\"";
	public static final String EDITAR_PACIENTE_ACTION = "\"http://pessoabd_webservice/editarPaciente\"";
	public static final String INSERIR_PESSOA_ACTION = "\"http://pessoabd_webservice/inserirPessoa\"";
	public static final String BUSCAR_PESSOA_ACTION = "\"http://pessoabd_webservice/findMedicoByLoginSenha\"";
	public static final String LISTAR_PESSOA_ACTION = "\"http://pessoabd_webservice/listarPessoa\"";
	public static final String EXCLUIR_PESSOA_ACTION = "\"http://pessoabd_webservice/excluirPessoa\"";
	public static final String EXCLUIR_PACIENTE_ACTION = "\"http://pessoabd_webservice/excluirPaciente\"";

}
