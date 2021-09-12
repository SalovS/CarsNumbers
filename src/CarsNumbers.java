import java.util.*;

public class CarsNumbers {
    public void Start(){
        ArrayList<String> carsNumbers = getNumbers();
        String number = "X555СТ84";
        bruteForceSearch(carsNumbers, number);
        binarySearch(carsNumbers, number);
        hashSetSearch(carsNumbers, number);
        treeSetSearch(carsNumbers, number);
    }
    public ArrayList<String> getNumbers(){
        ArrayList<String> numbers = new ArrayList<>();
        numbers.add("");
        return addRegion(addLetter(addLetter(addNumbers(addLetter(numbers)))));
    }

    private ArrayList<String> addLetter(ArrayList<String>numbers){
        ArrayList<String> array = new ArrayList<>();
        for (String number: numbers) {
            for(int i = 1; i < 13; i++){
                array.add(number + getLetter(i));
            }
        }
        return array;
    }

    private String getLetter(int number){
        switch(number){
            case 1: return "A";
            case 2: return "В";
            case 3: return "Е";
            case 4: return "К";
            case 5: return "М";
            case 6: return "Н";
            case 7: return "О";
            case 8: return "Р";
            case 9: return "С";
            case 10: return "Т";
            case 11: return "У";
            default: return "X";
        }
    }

    private String gerAnswer(boolean answer){
        if(answer){
            return "найден";
        }
        return "не найден";
    }

    private ArrayList<String> addNumbers(ArrayList<String> numbers){
        ArrayList<String> array = new ArrayList<>();
        for(String number: numbers){
            for(int i = 111; i < 1000; i += 111){
                array.add(number + i);
            }
        }
        return array;
    }

    private ArrayList<String> addRegion(ArrayList<String> numbers){
        ArrayList<String> array = new ArrayList<>();
        for(String number: numbers){
            for(int i = 1; i < 200; i++){
                array.add(number + String.format("%02d",i));
            }
        }
        return array;
    }

    public void bruteForceSearch(ArrayList<String> carsNumbers, String carNumber){
        boolean answer = false;
        long finish = System.nanoTime();
        long start = System.nanoTime();
        for(String number: carsNumbers){
            if(number.contains(carNumber)){
                answer =  true;
                finish = System.nanoTime();
                break;
            }
        }
        if(!answer){
            finish = System.nanoTime();
        }
        System.out.printf("Поиск перебором: номер %s, поиск занял %dнс\n",gerAnswer(answer), (finish - start));
    }

    public void binarySearch(ArrayList<String> carsNumbers, String carNumber){
        boolean answer = false;
        long finish = System.nanoTime();
        Collections.sort(carsNumbers, Collections.reverseOrder());
        long start = System.nanoTime();
        int first = 0;
        int last = carsNumbers.size() - 1;
        int middle;
        while(first <= last){
            middle = first + (last - first) / 2;
            if(carNumber.compareTo(carsNumbers.get(middle)) == 1){
                answer = true;
                finish = System.nanoTime();
                break;
            }else if(carNumber.compareTo(carsNumbers.get(middle)) < 0){
                first = middle + 1;
            }else{
                last = middle - 1;
            }
        }
        if(!answer){
            finish = System.nanoTime();
        }
        System.out.printf("Бинарный поиск : номер %s, поиск занял %dнс\n",gerAnswer(answer), (finish - start));
    }

    public void hashSetSearch(ArrayList<String> carsNumbers, String carNumber){
        boolean answer = false;
        HashSet hashSet = new HashSet(carsNumbers);
        long start = System.nanoTime();
        answer = hashSet.contains(carNumber);
        long finish = System.nanoTime();
        System.out.printf("Поиск в HashSet: номер %s, поиск занял %dнс\n",gerAnswer(answer), (finish - start));
    }

    public void treeSetSearch(ArrayList<String> carsNumbers, String carNumber){
        boolean answer = false;
        Set treeSet = new TreeSet(carsNumbers);
        long start = System.nanoTime();
        answer = treeSet.contains(carNumber);
        long finish = System.nanoTime();
        System.out.printf("Поиск в TreeSet: номер %s, поиск занял %dнс\n",gerAnswer(answer), (finish - start));
    }
}
