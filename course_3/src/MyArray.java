import java.util.ArrayList;
import java.util.stream.IntStream;

class MyArray {

    public ArrayList<Integer> m_Array;

    public MyArray(){
        m_Array = new ArrayList<Integer>(10);
        for(int i = 0; i < 10; ++i){
            m_Array.add(0);
        }
    }

    public Integer Sum(){
        return m_Array.stream().mapToInt( f -> f).sum( );
    }

    public Integer Sum(Integer aEnd){

        Integer sum = 0;

        for(Integer i = 0; i < aEnd; ++i){
            sum += m_Array.get(i);
        }

        return sum;
    }


}
