package lesson25;

public class Demo {
    public static void main(String[] args) throws Exception{
        GeneralDAO generalDAO = new GeneralDAO();

        Order o1 = new Order(1);
        generalDAO.validate(o1);

        TestClass<String, Order, Long> testClass = new TestClass<>();

        System.out.println(testClass.doSomething1("rrr"));


        int test = 100;
        long var  = 111;
        Long var2 = 222L;
        Long var3 = new Long(111);

        generalDAO.validate(test);

    }
}
