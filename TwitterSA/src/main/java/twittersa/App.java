package twittersa;


public class App 
{

    public String sayHello()
    {
        return "Hello World!";
    }

    public static void main( String[] args )
    {
        App a = new App();
        System.out.println(a.sayHello());
    }
}
