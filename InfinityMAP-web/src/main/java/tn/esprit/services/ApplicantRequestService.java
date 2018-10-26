package tn.esprit.services;


import java.util.Date;
import java.util.List;
import java.util.Locale.Category;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import tn.esprit.PIDEVMap.persistence.Applicant;
import tn.esprit.PIDEVMap.persistence.ApplicantAnswer;
import tn.esprit.PIDEVMap.persistence.ApplicantFile;
import tn.esprit.PIDEVMap.persistence.ApplicantRequest;
import tn.esprit.PIDEVMap.persistence.CategoryTest;
import tn.esprit.PIDEVMap.persistence.Question;
import tn.esprit.PIDEVMap.persistence.Rdv;
import tn.esprit.PIDEVMap.persistence.RdvState;
import tn.esprit.PIDEVMap.persistence.Requeststate;
import tn.esprit.PIDEVMap.persistence.Test;
import tn.esprit.PIDEVMap.services.AppliquantRequestLocal;

@Path("/ApplicantRequestService")
@RequestScoped
public class ApplicantRequestService {

	@EJB 
	AppliquantRequestLocal proxy; 
	
	@POST
	@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/sendRequest")
	public int sendRequet(ApplicantRequest request) {
		return proxy.sendRequet(request); 
		//return 0;
	}


	public void affecterRequestAapplicant(int appliquantId, int requestId) {

	}


	public ApplicantRequest suiviRequest(int requestId) {
		return null;
	}


	public void CancelRequest(int requestId, int applicantId) {
		
	}


	public Rdv TraiterDemande(int requestId, int reponse, Date date) {
		
		return null;
	}


	public int proposerTest(CategoryTest categoryTest) {
		
		return 0;
	}


	public void ajouterQuestion(Question q, int testId) {
	}


	public float corrigerTest(int testId, int applicantId) { //on peut mettre cette fonction dans une boucle pour corriger tous les tests de tous les applicant
		return 0; 
	}
		
	public void envoyerMailMinistere(String adresseMinistere, int applicantId) {
		// TODO Auto-generated method stub
		
	}


	public boolean consulterReponse(int applicantId) {
		// TODO Auto-generated method stub
		return false;
	}


	public void proposerLettre(int applicantId, String bodyFormulaire) {
		// TODO Auto-generated method stub
		
	}
}