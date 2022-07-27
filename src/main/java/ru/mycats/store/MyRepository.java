package ru.mycats.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.mycats.model.Cat;

import java.util.List;
import java.util.function.Function;

@Repository
public class MyRepository {
    private final SessionFactory sf;

    public MyRepository(SessionFactory sf) {
        this.sf = sf;
    }

    private <T> T tx(final Function<Session, T> command) {
        Session session = sf.openSession();
        try {
            T rsl = command.apply(session);
            session.beginTransaction().commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public List<Cat> getAll() {
        return tx(session -> session
                .createQuery("from Cat")
                .list());
    }

    public Cat save(Cat cat) {
        tx(session -> {
            session.save(cat);
            return cat;
        });
        return cat;
    }
}
