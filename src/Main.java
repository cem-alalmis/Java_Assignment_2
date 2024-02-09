import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
      /*  CreditManager creditManager = new CreditManager();
        creditManager.calculate();
        creditManager.calculate();
        creditManager.save();

        Customer customer = new Customer();

        customer.setId(1);
        customer.setCity("Istanbul");

        CustomerManager customerManager = new CustomerManager(new Person());
        customerManager.save();
        customerManager.delete();

        Company company = new Company();
        company.setTaxNumber("100000");
        company.setCompanyName("Arçelik");
        company.setId(100);

        CustomerManager customerManager2 = new CustomerManager(new Person());

        Person person = new Person();
        person.setNationalIdentity("1234567890");

        Customer c1 = new Customer();
        Customer c2 = new Person(); // Stack'teki Customer c2, Person'ın heap'teki referans numarasını tutuyor
        Customer c3 = new Company(); // Stack'teki Customer c3, Company'nin heap'teki referans numarasını tutuyor
*/
        // Interface ----------------------------------------------------
        // Tanım: İş yapan sınıfların, operasyonlarını sadece imza seviyesinde yazarak yazılımda bağımlılığı korumak adına yapılan çalışmadır.

        // IoC Container
        CustomerManager customerManager3 = new CustomerManager(new Person(), new TeacherCreditManager());
        customerManager3.giveCredit();

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}

class CreditManager {
    public void calculate() {
        System.out.println("Hesaplandı");
    }

    public void save() {
        System.out.println("Kredi verildi");
    }
}

interface ICreditManager {
    void calculate();
    void save();
}

abstract class BaseCreditManager implements ICreditManager {
    public abstract void calculate();

    public void save() {
        System.out.println("Kaydedildi");
    }
}

class TeacherCreditManager extends BaseCreditManager {
    public void calculate() {
        System.out.println("Öğretmen kredisi hesaplandı");
    }

    public void save() {
        super.save();
    }
}

class MilitaryCreditManager extends BaseCreditManager {
    public void calculate() {
        System.out.println("Asker kredisi hesaplandı");
    }
}

// SOLID (Bir class'ın bir işi yapması tercih edilir)

class Customer {
    private int id;
    private String city;

    public Customer() {
        System.out.println("Müşteri nesnesi başlatıldı");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

class Person extends Customer {
    private String firstName;
    private String lastName;
    private String nationalIdentity;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNationalIdentity() {
        return nationalIdentity;
    }

    public void setNationalIdentity(String nationalIdentity) {
        this.nationalIdentity = nationalIdentity;
    }
}

class Company extends Customer {
    private String companyName;
    private String taxNumber;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }
}

// Katmanlı Mimariler
class CustomerManager {
    private Person person;
    private ICreditManager creditManager;

    public CustomerManager(Person person, ICreditManager creditManager) {
        this.person = person;
        this.creditManager = creditManager;
    }

    public void save() {
        System.out.println("Müşteri kaydedildi: " + person.getFirstName());
    }

    public void delete() {
        System.out.println("Müşteri silindi: " + person.getFirstName());
    }

    public void giveCredit() {
        creditManager.calculate();
        System.out.println("Kredi verildi");
    }
}
