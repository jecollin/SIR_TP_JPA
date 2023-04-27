package jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JpaTest {

	@PersistenceContext
	private static EntityManager entityManager;

	public static void main(String[] args) {
		entityManager = EntityManagerHelper.getEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();

		try {
			// Création d'un nouvel utilisateur
			User user = createUser(entityManager, "John Doe", "john.doe@example.com", "password", "user");

			// Création d'un nouveau ticket
			List<String> tags = new ArrayList<>();
			tags.add("paiement");
			tags.add("bug");
			Ticket ticket = new Ticket("Bug dans le module de paiement", "Lorsque l'utilisateur essaie de payer avec une carte Amex, la transaction ne passe pas.", "open", "high", tags, user, null);
			entityManager.persist(ticket);

			// Ajout d'un commentaire au ticket
			TicketComment comment = new TicketComment("Je vais regarder ça de plus près.", LocalDateTime.now(), ticket, user);
			entityManager.persist(comment);

			// Liste des utilisateurs
			List<User> users = listUser(entityManager);
			for (User u : users) {
				System.out.println(u.toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();

		entityManager.close();
		EntityManagerHelper.closeEntityManagerFactory();
	}


	private static User createUser(EntityManager manager, String name, String email, String password, String role) {
		User user = new User(name, email, password, role);
		manager.persist(user);
		return user;
	}

	private static List<User> listUser(EntityManager manager) {
		TypedQuery<User> query = manager.createQuery("SELECT u FROM User u", User.class);
		return query.getResultList();
	}

	public void getBugTickets() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<BugTicket> query = cb.createQuery(BugTicket.class);
		Root<BugTicket> root = query.from(BugTicket.class);
		query.select(root);

		List<BugTicket> results = entityManager.createQuery(query).getResultList();

		for (BugTicket ticket : results) {
			System.out.println(ticket.toString());
		}
	}
}
