package mipes;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class Update extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		RequestDispatcher rd = null;
		
		try{
			
			/*
			 * se recibe todos los atributos de la case Persona
			 * para modificar
			 */
			String name = req.getParameter("name");
			String lastname = req.getParameter("lastname");
			String email = req.getParameter("email");
			String gp = req.getParameter("gp");

			PersistenceManager pm = PMF.get().getPersistenceManager();
			
			/*
			 * se usa el atributo "email" como clave para buscar
			 * y despues modificar ya que es unico para cada persona
			 */
			Query q = pm.newQuery(Persona.class);
			q.setFilter("email == emailParam");
			q.declareParameters("String emailParam");
			/*
			 * despues de hacer la consulta se obtiene el primer
			 * objeto de tipo Persona
			 */
			List<Persona> personas = (List<Persona>) q.execute(email);
			Persona p = personas.get(0);
			
			/*
			 * se modifican todos los atributos excepto email
			 */
			p.setName(name);
			p.setLastname(lastname);
			p.setGp(gp);
			q.closeAll();
			
			//delete persistent
			//delete persistent all
			String operacion = "Modificado";
			req.setAttribute("operacion", operacion);
			/*
			 * una vez terminado el proceso redirecciona
			 */
			rd = req.getRequestDispatcher("/success.jsp");

		}catch(Exception e){
			/*
			 * si ocurre algun error redirecciona a una pagina de error
			 */
			rd = req.getRequestDispatcher("/error.jsp");
		}
		
		rd.forward(req, resp);
		
	}
}
