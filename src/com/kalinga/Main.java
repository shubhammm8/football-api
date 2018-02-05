package com.kalinga;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.hibernate.Query;
import org.hibernate.Session;

import com.kalinga.hibernate.HibernateUtil;
import com.kalinga.hibernate.Nation;

@Path("/api")
public class Main {
	
	@GET
	@Path("/Nation")
	@Produces(MediaType.APPLICATION_XML)
	public List<Nation> response(){
		Session s=HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		
		Query q=s.createQuery("from Nation");
		List<Nation> li=q.list();
		s.getTransaction().commit();
		s.close();
		
		return li;
		
	
	}
	
	@GET
	@Path("/Nation/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Nation responseToGetById(@PathParam("id") int nid){
		Session s=HibernateUtil.buildSessionFactory().openSession();
		s.beginTransaction();
		Query q=s.createQuery("from Nation where nid=:n");
		q.setParameter("n", nid);
		Nation nation=(Nation) q.list().get(0);
		return nation;
	}
	
	@POST
	@Path("/Nation/add")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_JSON)
	public String ResposeToPost(@QueryParam("nation") String name){
		
		Session s=HibernateUtil.buildSessionFactory().openSession();
		s.beginTransaction();
		Nation n=new Nation();
		n.setName(name);
//		Query q=s.createQuery("insert into Nation value(:n)");
//		q.setParameter("n", name);
		
		s.save(n);
		s.getTransaction().commit();
		s.close();
		return "done";
		
		
	}
	
	
	@PUT
	@Path("/Nation/update")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_JSON)
	public String responseToPut(@QueryParam("nid") int nationid,@QueryParam("nation") String nationname){
		
		Session s=HibernateUtil.buildSessionFactory().openSession();
		s.beginTransaction();
		
		Query q=s.createQuery("update Nation set Name=:n where nid=:nid");
		q.setParameter("n",nationname);
		q.setParameter("nid", nationid);
		q.executeUpdate();
		s.getTransaction().commit();
		s.close();
		return "Done and Dusted";
		
	}
	@DELETE
	@Path("/Nation/delete")
	public String responseToDelete(@QueryParam("nid")int nid){
		Session s=HibernateUtil.buildSessionFactory().openSession();
		s.beginTransaction();
		
		Query q=s.createQuery("Delete from Nation where nid=:n");
		q.setParameter("n",nid);
		q.executeUpdate();
		s.getTransaction().commit();
		s.close();
		return "Deleted";
	}
	
}
