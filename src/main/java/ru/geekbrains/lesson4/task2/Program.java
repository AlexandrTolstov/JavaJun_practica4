package ru.geekbrains.lesson4.task2;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.geekbrains.lesson4.models.Student;
import org.hibernate.Session;
import java.net.MalformedURLException;

public class Program {
    public static void main(String[] args) throws MalformedURLException {

        try(SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory()){

            //Создание сессии
            Session session = sessionFactory.getCurrentSession();

            // Начало транзакции
            session.beginTransaction();

            // Создание объекта
            Student student = Student.create();
            session.save(student);
            System.out.println("Object student save successfully");

            //Чтение объекта из базы данных
            Student retrievedStudent = session.get(Student.class, student.getId());
            System.out.println("Object student retrieved successfully");
            System.out.println("Retrieved student object: " + retrievedStudent);

            // Обновление объекта
            retrievedStudent.updateName();
            retrievedStudent.updateAge();
            session.update(retrievedStudent);
            System.out.println("Object student update successfully");

            // Удаление объекта
            session.delete(retrievedStudent);
            System.out.println("Object student delete successfully");

            session.getTransaction().commit();

        }catch (Exception e){
            System.out.println("---------------------------------");
            e.printStackTrace();
        }

    }
}
