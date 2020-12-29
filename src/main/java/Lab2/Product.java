package Lab2;
import java.util.List;
public class Product extends ManagerObject {
    public Product(String _name, List<String> codes) {
        name = _name;
        code = generateCode();
    }
}