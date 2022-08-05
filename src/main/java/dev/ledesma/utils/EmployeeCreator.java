package dev.ledesma.utils;

import dev.ledesma.entities.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EmployeeCreator {

    public List<Employee> employees = new ArrayList<>();
    public String[] firstNames = {"James", "Robert", "John", "Michael", "David", "William", "Richard",
            "Joseph", "Thomas", "Charles", "Christopher", "Daniel", "Matthew", "Anthony",
            "Mark", "Donald", "Steven", "Paul", "Andrew", "Joshua", "Kenneth", "Kevin",
            "Brian", "George", "Timothy", "Ronald", "Edward", "Jason", "Jeffrey", "Ryan",
            "Jacob", "Gary", "Nicholas", "Eric", "Jonathan", "Stephen", "Larry", "Justin",
            "Scott", "Brandon", "Benjamin", "Samuel", "Gregory", "Alexander", "Frank",
            "Patrick", "Raymond", "Jack", "Dennis", "Jerry", "Tyler", "Aaron", "Jose",
            "Adam", "Nathan", "Henry", "Douglas", "Zachary", "Peter", "Kyle", "Ethan",
            "Walter", "Noah", "Jeremy", "Christian", "Keith", "Roger", "Terry", "Gerald",
            "Harold", "Sean", "Austin", "Carl", "Arthur", "Lawrence", "Dylan", "Jesse",
            "Jordan", "Bryan", "Billy", "Joe", "Bruce", "Gabriel", "Logan", "Albert",
            "Willie", "Alan", "Juan", "Wayne", "Elijah", "Randy", "Roy", "Vincent", "Ralph",
            "Eugene", "Russell", "Bobby", "Mason", "Philip", "Louis"};
    public String[] lastNames = {"Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller",
            "Davis", "Rodriguez", "Martinez", "Hernandez", "Lopez", "Gonzales", "Wilson", "Anderson", "Thomas",
            "Taylor", "Moore", "Jackson", "Martin", "Lee", "Perez", "Thompson", "White", "Harris", "Sanchez",
            "Clark", "Ramirez", "Lewis", "Robinson", "Walker", "Young", "Allen", "King", "Wright",
            "Scott", "Torres", "Nguyen", "Hill", "Flores", "Green", "Adams", "Nelson", "Baker",
            "Hall", "Rivera", "Campbell", "Mitchell", "Carter", "Roberts", "Gomez", "Phillips",
            "Evans", "Turner", "Diaz", "Parker", "Cruz", "Edwards", "Collins", "Reyes", "Stewart", "Morris",
            "Morales", "Murphy", "Cook", "Rogers", "Gutierrez", "Ortiz", "Morgan", "Cooper",
            "Peterson", "Bailey", "Reed", "Kelly", "Howard", "Ramos",
            "Kim", "Cox", "Ward", "Richardson", "Watson", "Brooks", "Chavez",
            "Wood", "James", "Bennet", "Gray", "Mendoza", "Ruiz", "Hughes", "Price",
            "Alvarez", "Castillo", "Sanders", "Patel", "Myers", "Long", "Ross", "Foster", "Jimenez"};
    public String[] title = {"Executive", "Manager", "Operations",
            "Production", "Chief Executive Officer", "Chief Operating Officer",
            "Chief Financial Officer", "Chief Marketing Officer", "Chief Technology Officer",
            "President", "Vice President", "Executive Assistants",
            "Marketing Manager", "Product Manager", "Project Manager",
            "Finance Manager", "Human Resources Manager", "Marketing Specialist",
            "Business Analyst", "Human Resource Personnel", "Accountant",
            "Sales Representative", "Customer Service Representative", "Administrative Assistant"};

    public EmployeeCreator(){
        Random r = new Random();
        int amount = r.nextInt(100);

        while(amount == 1){amount = r.nextInt(100);}

        for(int i = 0; i < amount; i++) {
            Employee employee = new Employee();
            employee.setId(0);
            employee.setFirstName(firstNames[r.nextInt(firstNames.length)]);
            employee.setLastName(lastNames[r.nextInt(lastNames.length)]);
            employee.setTitle(title[r.nextInt(title.length)]);
            employees.add(employee);
        }
    }
    public List<Employee> getEmployees() {
        return employees;
    }


}
