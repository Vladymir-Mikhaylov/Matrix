package lesson11.hw;

public class Controller {

    private API[] apis;

    public Controller(API[] apis) {
        this.apis = apis;
    }



    public Room[] requestRooms(int price, int persons, String city, String hotel){

        int lengthOfTotalResultArray = 0;

        Room[][] tempResult = new Room[apis.length][];
        for (int i = 0; i < apis.length; i++){
            tempResult[i] = apis[i].findRooms(price, persons, city, hotel);
            if(tempResult[i] != null){
                lengthOfTotalResultArray += tempResult[i].length;
            }
        }
        //new empty Total Result Array
        Room [] totalResult = new Room[lengthOfTotalResultArray];
        int position = 0;
        //join all arrays to one (copy >>>> past)
        for(int i = 0; i < tempResult.length; i++){
            if(tempResult[i] != null) {
                System.arraycopy(tempResult[i], 0, totalResult, position, tempResult[i].length);
                position += tempResult[i].length;
            }
        }

        return totalResult;
    }

    public Room[] check(API api1, API api2){

        Room[] rooms1 = api1.getAll();
        Room[] rooms2 = api2.getAll();
        //cont
        int counter = 0;
        for(Room a : rooms1){

            for(Room b : rooms2){
                if(a != null && b != null && a.getPrice() ==  b.getPrice() && a.getPersons() ==  b.getPersons() && a.getHotelName() ==  b.getHotelName() && a.getCityName() ==  b.getCityName()){
                    counter++;
                }
            }
        }
        //REMOVE
        Room [] result = new Room[counter];
        for(Room a : rooms1){
            int position = 0;
            for(Room b : rooms2){
                if(a != null && b != null && a.getPrice() ==  b.getPrice() && a.getPersons() ==  b.getPersons() && a.getHotelName() ==  b.getHotelName() && a.getCityName() ==  b.getCityName()){
                    result[position] = a;
                    position++;
                }
            }
        }
        return result;
    }
}
