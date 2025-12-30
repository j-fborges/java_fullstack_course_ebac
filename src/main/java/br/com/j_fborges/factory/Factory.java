package br.com.j_fborges.factory;

public class Factory implements IFactory{
    @Override
    public PersistentFactory createFactory(String option) {
        if ("Consumer".equals(option)) {
            return new ConsumerFactory();
        } else {
            return new ProductFactory();
        }
    }
}
