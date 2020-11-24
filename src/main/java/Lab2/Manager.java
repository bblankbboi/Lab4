package Lab2;

import org.javatuples.Pair;

    import java.util.ArrayList;
    import java.util.List;

    import static java.lang.String.format;

public class Manager {
    private static Manager instance;
    private static List<Shop> shops;
    private final static List<Integer> codes = new ArrayList<>();
    private static List<Product> products;


    private Manager(){ System.out.println("\nManager was created\n"); }
    public static Manager getInstance(){
        if( instance == null ){ instance = new Manager(); }
        return instance;
    }
    public List<Product> getProducts(){ return new ArrayList<>( products );}
    public List<Shop> getShops(){ return new ArrayList<>( shops ); }

    public void createShop( String shopName){
        Shop e = new Shop( shopName, codes );
        if( shops == null ){ shops = new ArrayList<>(); }
        shops.add( e );
    }// +
    public void createProduct( String productName ){
        Product e = new Product( productName, codes );
        if( products == null ){ products = new ArrayList<>(); }
        products.add( e );
    }// +
    public void createConsignmentOfFood( Shop shop, List<Party> partyList){
        for( Party p : partyList ){ shop.addParty( p ); }
    }// +
    public String nameOfShopInWhichThisProductIsTheCheapest( Product p ){
        String shopName = null;
        int min = Integer.MAX_VALUE;

        for( Shop e : shops ){
            int price;
            try{ price = e.getProductPrice( p ); }
            catch( NullPointerException exception ){ continue; }
            if( min > price ){ min = price; shopName = e.getName(); }
        }
        if( shopName == null ){ return "Ни в одном из магазинов нет данного товара.\n"; }
        return shopName;
    }// +
    public String nameOfProductsThatCouldBuyForTheAmount( Shop shop, int amount){
        List<Pair< Product, Integer>> products;

        try{ products = shop.ProductsThatCouldBuyForTheAmount( amount );}
        catch(NullPointerException e){ return "На данную сумму нельзя ничего купить.\n"; }

        String line = "";
        for( Pair< Product, Integer> p : products ){
            line += format("%s: %d\n", p.getValue0().getName(), p.getValue1());
        }

        return line;
    }// +
    public int costOfConsignmentOfFoods( Shop shop, List<Pair< Product, Integer >> products) throws MyException{
        return shop.costOfConsignmentOfFoods( products );
    }// +
    public String nameOfShopWhichThisConsignmentOfFoodsTheCheapest( List<Pair< Product, Integer >> products ) throws MyException{
        int min = Integer.MAX_VALUE;
        String shopName = null;
        for( Shop p : shops ){
            int cost;
            try {
                cost = p.costOfConsignmentOfFoods( products );
            }catch(MyException e){ continue; }
            if( min > cost ){ min = cost; shopName = p.getName(); }
        }
        if( shopName == null ){ throw new MyException("Ни в каком из магазинов нельзя купить данную партию товаров"); }
        return shopName;
    }// +
}
