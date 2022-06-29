package web.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import web.config.HibernateConfig;
import web.dao.HibernateDAO;
import web.model.User;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);
        HibernateDAO hibernateDAO = context.getBean(HibernateDAO.class);
        hibernateDAO.saveUser(new User("Ruslan",14,"email12"));
        context.close();
    }
}
