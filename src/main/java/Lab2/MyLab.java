package Lab2;

import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.List;

public class MyLab {

    public static void main(String[] args) {

        Manager.getInstance().createShop( "Дикси" );// 0
        Manager.getInstance().createShop( "Пятёрочка" );// 1
        Manager.getInstance().createShop( "Призма" );// 2

        Manager.getInstance().createProduct( "Milk" );// 0
        Manager.getInstance().createProduct( "Bread" );// 1
        Manager.getInstance().createProduct( "Water" );// 2
        Manager.getInstance().createProduct( "Chocolate" );// 3
        Manager.getInstance().createProduct( "Orange" );// 4
        Manager.getInstance().createProduct( "Strawberry" );// 5
        Manager.getInstance().createProduct( "Sunflower" );// 6
        Manager.getInstance().createProduct( "Coconut" );// 7
        Manager.getInstance().createProduct( "Oil" );// 8
        Manager.getInstance().createProduct( "Tomato" );// 9
        /////////////
        List<Party> products = new ArrayList<>();
        products.add( new Party( Manager.getInstance().getProducts().get( 0 ), 10, 100 ) );
        products.add( new Party( Manager.getInstance().getProducts().get( 1 ), 12, 50 ) );
        products.add( new Party( Manager.getInstance().getProducts().get( 2 ), 1, 117 ) );
        Manager.getInstance().createConsignmentOfFood( Manager.getInstance().getShops().get( 0 ), products);// Дикси

        products = new ArrayList<>();
        products.add( new Party( Manager.getInstance().getProducts().get( 1 ), 9, 101 ) );
        products.add( new Party( Manager.getInstance().getProducts().get( 4 ), 13, 52 ) );
        products.add( new Party( Manager.getInstance().getProducts().get( 3 ), 100, 118 ) );
        Manager.getInstance().createConsignmentOfFood( Manager.getInstance().getShops().get( 1 ), products);// Пятёрочка

        products = new ArrayList<>();
        products.add( new Party( Manager.getInstance().getProducts().get( 5 ), 90, 1010 ) );
        products.add( new Party( Manager.getInstance().getProducts().get( 4 ), 130, 522 ) );
        products.add( new Party( Manager.getInstance().getProducts().get( 6 ), 10, 110 ) );
        products.add( new Party( Manager.getInstance().getProducts().get( 7 ), 101, 101 ) );
        products.add( new Party( Manager.getInstance().getProducts().get( 8 ), 12, 116 ) );
        products.add( new Party( Manager.getInstance().getProducts().get( 9 ), 30, 151 ) );
        products.add( new Party( Manager.getInstance().getProducts().get( 3 ), 100, 118 ) );
        Manager.getInstance().createConsignmentOfFood( Manager.getInstance().getShops().get( 2 ), products);// Призма

        System.out.format("Данный товар дешевле всего купить в магазине \"%s\".\n\n"
                ,Manager.getInstance().nameOfShopInWhichThisProductIsTheCheapest( Manager.getInstance().getProducts().get( 1 ) ));
        ///////////////////
        System.out.println(
                Manager.getInstance().nameOfProductsThatCouldBuyForTheAmount( Manager.getInstance().getShops().get( 1 ), 1000) );
        //////////////////
        List<Pair< Product, Integer >> prod = new ArrayList<>();

        prod.add( new Pair<Product, Integer>( Manager.getInstance().getProducts().get( 5 ), 90 ) );
        prod.add( new Pair<Product, Integer>( Manager.getInstance().getProducts().get( 6 ), 10 ) );
        try{
        System.out.println(Manager.getInstance().costOfConsignmentOfFoods( Manager.getInstance().getShops().get( 2 ), prod ));
        System.out.println();}
        catch( MyException e ){
            System.out.println("Покупка невозможна\n");
        }
        //////////////////
        prod = new ArrayList<>();

        prod.add( new Pair<Product, Integer>( Manager.getInstance().getProducts().get( 4 ), 10 ) );
        prod.add( new Pair<Product, Integer>( Manager.getInstance().getProducts().get( 3 ), 10 ) );

        try {
            System.out.println(Manager.getInstance().nameOfShopWhichThisConsignmentOfFoodsTheCheapest(prod));
        }catch(MyException e){
            System.out.println("Ни в каком из магазинов нельзя совершить данную покупку");
        }
    }

}
