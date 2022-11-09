package mipes;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class Save extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		RequestDispatcher rd = null;
		
		try{
			
			String name = req.getParameter("name");
			String lastname = req.getParameter("lastname");
			String email = req.getParameter("email");
			String gp = req.getParameter("grupo");

			Persona p = new Persona(name, lastname, email, gp);

			PersistenceManager pm = PMF.get().getPersistenceManager();


			pm.makePersistent(p);
			
			//delete persistent
			//delete persistent all
			String operacion = "Registrado";
			req.setAttribute("operacion", operacion);
			rd = req.getRequestDispatcher("/success.jsp");

		}catch(Exception e){
			rd = req.getRequestDispatcher("/error.jsp");
		}
		
		rd.forward(req, resp);
		
	}
}
