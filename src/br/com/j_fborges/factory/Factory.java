package br.com.j_fborges.factory;

public class Factory implements IFactory{
    @Override
    public PersistentFactory createFactory(String option) {
//        if ("1".equals(opcaoMenuGeral)) {
//            return new ClienteFabrica();
//        } else {
//            return new ProdutoFabrica();
//        }
        return new ConsumerFactory();
    }
}
