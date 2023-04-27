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
import jpa.Ticket;

/**
 * Servlet implementation class TicketsServlet
 */
@WebServlet("/tickets")
public class TicketsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter writer = response.getWriter();
		
		writer.append("<html>\n")
		.append("<head>\n")
		.append("<title>Tickets Inside Database</title>\n")
		.append("</head>")
		.append("<body>\n")
		.append("<form method=\"POST\" action=\"tickets\"><br>\n")
		.append("Title of ticket: <input type=\"text\" name=\"name\"/><br>\n")
		.append("Body: <input type=\"text\" name=\"description\" /><br>\n")
		.append("<input type=\"submit\" name=\"Add\" />")
		.append("</form>\n")
		.append("<h2>All Items</h2>\n");
		
		EntityManager manager = EntityManagerHelper.getEntityManager();
		EntityTransaction trax = manager.getTransaction();
		trax.begin();
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		
		CriteriaQuery<Ticket> q = builder.createQuery(Ticket.class);
		List<Ticket> list = manager.createQuery(q.select(q.from(Ticket.class))).getResultList();
		if(list.size() == 0)
		{
			writer.append("<h3>No Items Added yet</h3>\n");
		}
		writer.append("<ul>\n");
		for(Ticket t : list)
		{
			writer.append("<li>" + t.getTitle() + ": " + t.getBody() + "</li>\n");
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
		String title = request.getParameter("ticket1");
		String body = request.getParameter("description");
		
		Ticket ticket = new Ticket();
		ticket.setTitle(title);
		ticket.setBody(body);
		
		EntityManager manager = EntityManagerHelper.getEntityManager();
		EntityTransaction trax = manager.getTransaction();
		trax.begin();
		
		manager.persist(ticket);
		
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
