package ru.geekbrains.lesson4.homework;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.geekbrains.lesson4.models.Course;
import ru.geekbrains.lesson4.models.Student;

public class Program {
    /**
     * Задание
     * =======
     * Создайте базу данных (например, SchoolDB).
     * В этой базе данных создайте таблицу Courses c полями id (ключ), title, duration
     * Настройте Hibernate для работы с вашей базой данных.
     * Создайте Java-класс Course, соответствующий таблице Courses, с Необходимыми аннотациями Hibernate.
     * Используя Hibernate, напишите код для вставки, чтения, обновления и удаления данных в Талице Courses
     * Убедитесь, что каждая операция выполняется в отдельной транзакции.
     */
    public static void main(String[] args) {
        try(SessionFactory sessionFactory = new Configuration()
                .configure("hibernate_schoolDB.cfg.xml")
                .addAnnotatedClass(Course.class)
                .buildSessionFactory()){

            //Создание сессии
            Session session = sessionFactory.getCurrentSession();

            // Начало транзакции
            session.beginTransaction();

            // Создание объекта
            Course course = Course.create();
            session.save(course);
            System.out.println("Object student save successfully");

            //Чтение объекта из базы данных
            Course retrievedCourse = session.get(Course.class, course.getId());
            System.out.println("Object course retrieved successfully");
            System.out.println("Retrieved course object: " + retrievedCourse);

            // Обновление объекта
            retrievedCourse.updateDuration();
            retrievedCourse.updateTitle();
            session.update(retrievedCourse);
            System.out.println("Object student update successfully");

            // Удаление объекта
            //session.delete(retrievedCourse);
            //System.out.println("Object student delete successfully");

            session.getTransaction().commit();

        }catch (Exception e){
            System.out.println("---------------------------------");
            e.printStackTrace();
        }
    }
}
