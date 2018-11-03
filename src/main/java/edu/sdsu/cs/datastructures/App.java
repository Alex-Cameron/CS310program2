package edu.sdsu.cs.datastructures;

public class App 
{
    public static void main( String[] args )
    {
        BalancedMap sut = new BalancedMap();
        sut.add(1, "one");
        sut.add(2, "two");
        sut.add(3, "two");
        Iterable cam = sut.getKeys("two");
        System.out.println(sut.values());
        System.out.println(sut.keyset());
    }
}
