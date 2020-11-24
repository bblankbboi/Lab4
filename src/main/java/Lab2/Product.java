package Lab2;

import java.util.List;

public class Product extends ManagerObject{
    public Product( String _name, List<Integer> codes ){ name = _name; code = generateCode( codes );}
    public Product( Product product ){
        name = product.getName();
        code = product.getCode();
    }
}
