package mipes;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class Remove extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		RequestDispatcher rd = null;

		try{
			
			/*
			 * se obtiene el valor de la operacion a realizarse
			 */
			char op = req.getParameter("opcion").charAt(0);

			PersistenceManager pm = PMF.get().getPersistenceManager();

			resp.setContentType("text/html");
			
			/*
			 * ademas de el contenido del input con nombre busqueda
			 */
			String bus = req.getParameter("busqueda");

			Query q = pm.newQuery(Persona.class);

			/*
			 * de acuerdo a los datos presentados
			 * se hace la busqueda primero del objeto
			 * a ser elmininado
			 */
			switch (op) {

			case 'n':
				q.setFilter("name == nombreParam");
				q.declareParameters("String nombreParam");

				break;
			case 'a':
				q.setFilter("lastname == lastnameParam");
				q.declareParameters("String lastnameParam");

				break;
			case 'e':
				q.setFilter("email == emailParam");
				q.declareParameters("String emailParam");

				break;
			case 'c': 
				q.setFilter("gp == gpParam");
				q.declareParameters("String gpParam");		
				break;
			case 'l': 
				q.setFilter("");
				q.declareParameters("");		
				break;

			}
			
			
			List<Persona> personas = (List<Persona>) q.execute(bus);
			/*
			 * una vez obtenido el objeto a ser eliminado 
			 * se elimina usando el metodo DELETEPERSISTENT()
			 */
			pm.deletePersistent(personas.get(0));
			
			q.closeAll();
			pm.close();
			
			String operacion = "Eliminado";
			req.setAttribute("operacion", operacion);
			/*
			 * si no ocurre ningun error redirecciona
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
