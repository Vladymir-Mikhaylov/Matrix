package lesson25;

public class GeneralDAO <T> {

    private T[] DB = (T[])new Object[10];

    public void print(Order order){
        System.out.println("order is: " + order.toString());
    }

    public <V extends IdEntity> void validate(V v)throws Exception{
        if(v.getId() <= 0){
            throw new Exception("Id can't be negative");
        }
    }

    public <K> void validate(K k){
        if (k.equals(100)) {
            System.out.println(true);
        }else {
            System.out.println(false);
        }
    }

    public T save(T t)throws Exception{
        if(find(t) == null){
            for(int i = 0; i < DB.length; i++){
                if(DB[i] == null){
                    DB[i] = t;
                    return (T) DB[i];
                }
            }
        }
        throw new Exception("can't save an object");
    }

    public T[] getAll(){

        int amount = 0;
        for(int i = 0; i < DB.length; i++){
            if(DB[i] != null){
                amount++;
            }
        }

        T[] result = (T[])new Object[amount];
        int index = 0;

        for(int i = 0; i < DB.length; i++){
            if(DB[i] != null){
                result[index] = DB[i];
                index++;
            }
        }
        return result;
    }

    private T find (T t){
        for(int i = 0; i < DB.length; i++){
            if(DB[i] != null && (DB[i]).equals(t)){
                return DB[i];
            }
        }
        return null;
    }
}
