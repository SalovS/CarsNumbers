import java.util.*;

public class CarsNumbers {
    public void Start(){
        List carsNumbers = getNumbers();
        String number = "X555СТ84";
        bruteForceSearch(carsNumbers, number);
        binarySearch(carsNumbers, number);
        hashSetSearch(carsNumbers, number);
        treeSetSearch(carsNumbers, number);
    }

    public List<String> getNumbers(){
        List numbers = new ArrayList();
        for(int i = 1; i < 13; i++){
            for(int j = 111; j < 1000; j+=111){
                for(int k = 1; k < 13; k++){
                    for(int l = 1; l < 13; l++){
                        for(int u = 1; u < 200; u++){
                            numbers.add(String.format("%s%d%s%s%02d",getLetter(i),j,getLetter(k),getLetter(l),u));
                        }
                    }
                }
            }
        }
        return numbers;
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

    public void bruteForceSearch(List<String> carsNumbers, String carNumber){
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

    public void binarySearch(List<String> carsNumbers, String carNumber){
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

    public void hashSetSearch(List<String> carsNumbers, String carNumber){
        boolean answer = false;
        HashSet hashSet = new HashSet(carsNumbers);
        long start = System.nanoTime();
        answer = hashSet.contains(carNumber);
        long finish = System.nanoTime();
        System.out.printf("Поиск в HashSet: номер %s, поиск занял %dнс\n",gerAnswer(answer), (finish - start));
    }

    public void treeSetSearch(List<String> carsNumbers, String carNumber){
        boolean answer = false;
        Set treeSet = new TreeSet(carsNumbers);
        long start = System.nanoTime();
        answer = treeSet.contains(carNumber);
        long finish = System.nanoTime();
        System.out.printf("Поиск в TreeSet: номер %s, поиск занял %dнс\n",gerAnswer(answer), (finish - start));
    }
}
