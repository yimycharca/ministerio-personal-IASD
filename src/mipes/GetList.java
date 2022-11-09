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
public class GetList extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		RequestDispatcher rd = null;

		try{

			char op = req.getParameter("opcion").charAt(0);

			PersistenceManager pm = PMF.get().getPersistenceManager();

			resp.setContentType("text/html");

			String bus = req.getParameter("busqueda");

			Query q = pm.newQuery(Persona.class);

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
				q.setFilter("grupo == gpParam");
				q.declareParameters("String gpParam");		
				break;
			case 'l': 
				q.setFilter("");
				q.declareParameters("");		
				break;


			}

			List<Persona> personas = (List<Persona>) q.execute(bus);

			q.closeAll();

			req.setAttribute("personas",personas);

			rd = req.getRequestDispatcher("/result.jsp");

		}catch(Exception e){
			e.printStackTrace();
			rd = req.getRequestDispatcher("/error.jsp");

		}

		rd.forward(req, resp);
	}
}
