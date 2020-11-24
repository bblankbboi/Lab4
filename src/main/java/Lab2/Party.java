package Lab2;

import org.javatuples.Pair;

public class Party{
    private final Product product;
    public Integer count;
    private final Integer price;

    public boolean equalsTo( Party o ){
        if( product.getCode() != o.getProductCode() ){ return false; }
        if( price != o.getPrice() ){ return false; }

        return true;
    }

    public Party( Product _product, int _count, int _price ){
        product = new Product( _product );
        count = _count;
        price = _price;
    }

    public String getName(){ return product.getName(); }
    public int getProductCode(){ return product.getCode(); }
    public int getPrice(){ return price; }

    public Pair< Product, Integer > buyForTheAmount(int amount ) throws MyException{
        Pair< Product, Integer > pair = new Pair< Product, Integer>( product, (amount / price) );

        if( pair.getValue1() == 0 ){ throw new MyException(); }
        return pair;
    }
}
