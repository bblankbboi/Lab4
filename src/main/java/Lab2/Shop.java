package Lab2;

import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.List;

public class Shop extends ManagerObject{
    private final List<Party> productsInThisShop = new ArrayList<>();

    public Shop(String _name, List<Integer> codes ){ name = _name; code = generateCode( codes );}

    public void addParty( Party p ){
        for( Party party : productsInThisShop ){
            if( party.equalsTo( p )){ party.count += p.count; return; }
        }

        productsInThisShop.add( p );
    }
    public int getProductPrice( Product product ){
        for( Party p : productsInThisShop ){
            if( p.getProductCode() == product.getCode() ){ return p.getPrice(); }
        }

        throw new NullPointerException();
    }
    public Party getParty( int productID ) throws MyException{
        for( Party p : productsInThisShop ){
            if( p.getProductCode() == productID ){ return p; }
        }
        throw new MyException("Невозможно купить данную партию товаров.\n");
    }
    public List<Pair< Product, Integer >> ProductsThatCouldBuyForTheAmount(int amount){
        if( productsInThisShop == null ){ throw new NullPointerException(); }

        List<Pair< Product, Integer >> products = null;

        for( Party p : productsInThisShop ){
            Pair< Product, Integer > pair;
            try { pair =  p.buyForTheAmount( amount ); }
            catch(MyException e){ continue; }
            if( products == null ){ products = new ArrayList<>();}
            products.add( pair );
        }

        if( products == null ){ throw new NullPointerException(); }

        return products;
    }
    public int costOfConsignmentOfFoods( List<Pair< Product, Integer >> products) throws MyException{
        int sum = 0;
        for( Pair< Product, Integer > p : products ){
            int ID = p.getValue0().getCode();
            int _count = p.getValue1();
            Party party = this.getParty( ID );
            if( _count > party.count){ throw new MyException("Невозможно купить данную партию товаров.\n"); }
            sum += _count * party.getPrice();
            party.count -= _count;
        }
        return sum;
    }
}
