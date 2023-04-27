package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jpa.EntityManagerHelper;
import jpa.Thing;

/**
 * Servlet implementation class ThingsServlet
 */
@WebServlet("/things")
public class ThingsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter writer = response.getWriter();
		
		writer.append("<html>\n")
		.append("<head>\n")
		.append("<title>Things Inside Database</title>\n")
		.append("</head>")
		.append("<body>\n")
		.append("<form method=\"POST\" action=\"things\"><br>\n")
		.append("Name of thing: <input type=\"text\" name=\"name\"/><br>\n")
		.append("Description: <input type=\"text\" name=\"description\" /><br>\n")
		.append("<input type=\"submit\" name=\"Add\" />")
		.append("</form>\n")
		.append("<h2>All Items</h2>\n");
		
		EntityManager manager = EntityManagerHelper.getEntityManager();
		EntityTransaction trax = manager.getTransaction();
		trax.begin();
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		
		CriteriaQuery<Thing> q = builder.createQuery(Thing.class);
		List<Thing> list = manager.createQuery(q.select(q.from(Thing.class))).getResultList();
		if(list.size() == 0)
		{
			writer.append("<h3>No Items Added yet</h3>\n");
		}
		writer.append("<ul>\n");
		for(Thing t : list)
		{
			writer.append("<li>" + t.getName() + ": " + t.getDescription() + "</li>\n");
		}
		writer.append("</ul>\n")
		.append("</body>\n</html>");
		trax.commit();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		
		Thing thing = new Thing();
		thing.setName(name);
		thing.setDescription(description);
		
		EntityManager manager = EntityManagerHelper.getEntityManager();
		EntityTransaction trax = manager.getTransaction();
		trax.begin();
		
		manager.persist(thing);
		
		trax.commit();
		
		
		doGet(request, response);
	}
	
	@Override
	public void destroy() 
	{
		super.destroy();
		EntityManagerHelper.getEntityManager().close();
	}

}
