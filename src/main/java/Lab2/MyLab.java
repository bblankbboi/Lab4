package Lab2;
import org.javatuples.Pair;
import java.util.ArrayList; import java.util.List;
public class MyLab {
    public static void main(String[] args) {
        Manager manager = Manager.getInstance();
        manager.createShop( "Дикси" );// 0
        manager.createShop( "Пятёрочка" );// 1
        Manager.getInstance().createShop( "Призма" );// 2

        manager.createProduct( "Milk" );// 0
        manager.createProduct( "Bread" );// 1
        manager.createProduct( "Water" );// 2
        manager.createProduct( "Chocolate" );// 3
        manager.createProduct( "Orange" );// 4
        manager.createProduct( "Strawberry" );// 5
        manager.createProduct( "Sunflower" );// 6
        manager.createProduct( "Coconut" );// 7
        manager.createProduct( "Oil" );// 8
        manager.createProduct( "Tomato" );// 9

        List<Party> products = new ArrayList<>();
        products.add( new Party( manager.getProducts().get( 0 ), 10, 100 ) );
        products.add( new Party( manager.getProducts().get( 1 ), 12, 50 ) );
        products.add( new Party( manager.getProducts().get( 2 ), 1, 117 ) );

        manager.createConsignmentOfFood( manager.getShops().get( 0 ), products);// Дикси

        products = new ArrayList<>();
        products.add( new Party( manager.getProducts().get( 1 ), 9, 101 ) );
        products.add( new Party( manager.getProducts().get( 4 ), 13, 52 ) );
        products.add( new Party( manager.getProducts().get( 3 ), 100, 118 ) );

        manager.createConsignmentOfFood( manager.getShops().get( 1 ), products);// Пятёрочка

        products = new ArrayList<>();
        products.add( new Party( manager.getProducts().get( 5 ), 90, 1010 ) );
        products.add( new Party( manager.getProducts().get( 4 ), 130, 522 ) );
        products.add( new Party( manager.getProducts().get( 6 ), 10, 110 ) );
        products.add( new Party( manager.getProducts().get( 7 ), 101, 101 ) );
        products.add( new Party( manager.getProducts().get( 8 ), 12, 116 ) );
        products.add( new Party( manager.getProducts().get( 9 ), 30, 151 ) );
        products.add( new Party( manager.getProducts().get( 3 ), 100, 118 ) );

        manager.createConsignmentOfFood( manager.getShops().get( 2 ), products);// Призма

        try {
            System.out.format("Данный товар дешевле всего купить в магазине \"%s\".\n\n"
                    , manager.nameOfShopInWhichThisProductIsTheCheapest(manager.getProducts().get(1)));
        }catch (MyException e){
            System.out.println(e.getMessage());
        }

        System.out.println(manager.nameOfProductsThatCouldBuyForTheAmount( manager.getShops().get( 1 ), 1000) );

        List<Pair< Product, Integer >> prod = new ArrayList<>();
        prod.add( new Pair<Product, Integer>( manager.getProducts().get( 5 ), 90 ) );
        prod.add( new Pair<Product, Integer>( manager.getProducts().get( 6 ), 10 ) );
        try{
            System.out.println(manager.getShops().get( 2 ).buyConsignmentOfFoods( prod ));

            System.out.println();
        }
        catch( MyException e ){ System.out.println("Покупка невозможна\n");
        }
        prod = new ArrayList<>();
        prod.add( new Pair<Product, Integer>( manager.getProducts().get( 4 ), 10 ) );
        prod.add( new Pair<Product, Integer>( manager.getProducts().get( 3 ), 10 ) );

        try { System.out.println(manager.nameOfShopWhichThisConsignmentOfFoodsTheCheapest(prod));
        }catch(MyException e){
            System.out.println("Ни в каком из магазинов нельзя совершить данную покупку");
        } }
}