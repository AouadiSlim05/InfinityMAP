package tn.esprit.services;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tn.esprit.PIDEVMap.persistence.Client;
import tn.esprit.PIDEVMap.persistence.Projet;
import tn.esprit.PIDEVMap.services.ClientServiceLocal;
import tn.esprit.PIDEVMap.services.ProjetServiceLocal;

@Path("/ProjetService")
@RequestScoped
public class ProjetService {

	@EJB
	ProjetServiceLocal remoteProjet;
	
	@EJB
	ClientServiceLocal remoteClient;

	

    //ajouter projet
  		@POST
  		@Produces(MediaType.APPLICATION_JSON)
  		@Path("addProjet")
  		public javax.ws.rs.core.Response ajouterProjet(Projet p)
  		{
  			remoteProjet.ajouterProjet(p);
  			return javax.ws.rs.core.Response.ok("ajoute avec succes").build();
  		}
  		
    
	//affecterProjetAClient
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("affecterProjetAClient/{projetId}/{clientId}")
	public javax.ws.rs.core.Response affecterProjetAClient(@PathParam("projetId") int  projetId,@PathParam("clientId") int clientId)
	{
		Client client=remoteClient.findClientById(clientId);
		Projet projet=remoteProjet.findProjetById(projetId);
		if(projet.getEtat().equals("not available"))
		{
			 return javax.ws.rs.core.Response.ok("le projet que vous voulez affecter n'existe pas ").build();
		}
		else if(client.getEtat().equals("not available"))
		{
				return javax.ws.rs.core.Response.ok("le client que vous voulez affecter n'existe pas ").build();
		}
		else
		{
			System.out.println(projetId);
			System.out.println(clientId);
		
			remoteProjet.affecterProjetAClient(projetId, clientId);
			return javax.ws.rs.core.Response.ok("affectation").build();
		}
		
		
		
	}
	
	
	//afficher liste des projets
    @Path("afficherAllProjets")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProjets()
    {
    	return Response.status(Status.ACCEPTED).entity(remoteProjet.findAllProjets()).build()  ;
    }
    
	//modifier projet
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("modifierProjet/{projetId}")
    public Response modifierProjet(@PathParam("projetId") int id, Projet projet) throws Exception {
    	projet.setId(id);
    	if(remoteProjet.findProjetById(id)== null )
    	{
    		 return javax.ws.rs.core.Response.ok("le projet que vous voulez modifier n'existe pas ").build();
    	}
    	else
    	{	
    		
    	
    		System.out.println("id projet= "+projet.getId());
    		System.out.println("nom projet= "+projet.getNom());
    		//System.out.println("logo= "+client.getLogo());
    		//System.out.println("type client= "+client.getCategorie());
    		//System.out.println("etat client= "+client.getEtat());
    		if(projet.getEtat().equals("not available"))
    		{
    			return   javax.ws.rs.core.Response.ok("le projet que vous vous voulez modifier est supprimé").build();
    		}
    		
    			remoteProjet.modifierProjet(projet,id);
    		
    		System.out.println("baed modfi");
    		System.out.println("id projet= "+projet.getId());
	    	System.out.println("nom projet= "+projet.getNom());
	    	//System.out.println("logo= "+client.getLogo());
	    	//System.out.println("type client= "+client.getCategorie());
	    	//System.out.println("etat client= "+client.getEtat());
    		 return   javax.ws.rs.core.Response.ok("projet modifié avec succes").build();
    	}
    }
    
    
    
   //supprimer projet
    @PUT
    @Path("supprimerProjet/{projetId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response supprimerProjet(@PathParam("projetId") int idprojet,Projet projet) throws Exception {
    	 projet=remoteProjet.findProjetById(idprojet);
    	 if(projet==null)
    	 {
    		 return javax.ws.rs.core.Response.ok("projet non existant").build();
    	 }
    	 System.out.println(projet.getEtat());
    	 if(projet.getEtat().equals("not available"))
    	 {
    		 return javax.ws.rs.core.Response.ok("projet deja supprime").build();
    	 }
    	 remoteProjet.supprimerProjet( idprojet);
    	 return javax.ws.rs.core.Response.ok("supprime avecc succes").build();
    }
    
  //afficher liste des projets dun client donné en para   
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("findListPByIdClient/{idclient}")
    public Response findListPByIdClient(@PathParam("idclient") int idclient)
    {
    	Client client=remoteClient.findClientById(idclient);
    	//System.out.println("PROJECTS FOUND ===>"+remoteProjet.findListPByIdClient(idclient).size());
    	if(client==null)
    	{
    		return javax.ws.rs.core.Response.ok("client non existant").build();
    	}
    	else if(client.getEtat().equals("not available"))
    	{
    		return javax.ws.rs.core.Response.ok("client supprime").build();
    	}
    	
    	return Response.status(Status.ACCEPTED).entity(remoteProjet.findListPByIdClient(idclient)).build()  ;
    }
    
    //afficher nombre de projet par clients  
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("NbrProjetParClient/{idclient}")
    public Response NbrProjetParClient(@PathParam("idclient") int idclient)
    {
    	Client client=remoteClient.findClientById(idclient);
    	if(client==null)
    	{
    		return javax.ws.rs.core.Response.ok("client non existant").build();
    	}
    	else if(client.getEtat().equals("not available"))
    	{
    		return javax.ws.rs.core.Response.ok("client supprime").build();
    	}
    	return Response.status(Status.ACCEPTED).entity(remoteProjet.NbrPRojetParClient(idclient)).build()  ;
    }
    
  //afficher nombre de projet par clients  
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("NbrProjetsTotal")
    public Response NbrProjetsTotal()
    {
    	return Response.status(Status.ACCEPTED).entity(remoteProjet.NbrProjetsTotal()).build()  ;
    }
    
    //calcul du statistique des projets
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("statProjet/{idclient}")
    public Response statProjet(@PathParam("idclient") int idclient)
    {
    	Client client=remoteClient.findClientById(idclient);
    	return Response.status(Status.ACCEPTED).entity("les projets du client "+client.getNom()+" represente "+remoteProjet.statProjet(idclient)+"% du nombre de projets total").build()  ;
    }
    
   
}
