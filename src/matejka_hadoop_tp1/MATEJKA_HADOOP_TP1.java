package matejka_hadoop_tp1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author qmatejka
 */
public class MATEJKA_HADOOP_TP1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
        HashMap<Integer,String> inputReader;
        try {
            inputReader = reader("MOCK_DATA.csv");
            String[][] cols = map(inputReader);
            String output = reduce(cols, "output.txt");
        } catch (IOException ex) {
            Logger.getLogger(MATEJKA_HADOOP_TP1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static HashMap<Integer,String> reader(String data) throws FileNotFoundException, IOException{
        System.out.println("Working on the file: "+data);
        HashMap<Integer,String> inputReader;
        try (BufferedReader reader = new BufferedReader(new FileReader(data))) {
            String line ;
            inputReader = new HashMap<>();
            int index = 0;
            System.out.println("Constructing first map line by line...");
            while ((line = reader.readLine()) != null) {
                index++;
                inputReader.put(index, line);
            }
        }
        return inputReader;
    }
    
    public static String[][] map(HashMap<Integer,String> inputReader){
        String[][] cols = new String[inputReader.size()][];
        for(int i=1; i<=inputReader.size(); i++){
            cols[i-1] = inputReader.get(i).split(",");
        }
        return cols;
    }
    
    public static String reduce(String[][] cols, String output) throws IOException{
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(output))) {
            for(int i=0; i<cols.length; i++){
                for(int j=0;j<cols.length;j++){
                    output += cols[j][i];
                    if(j+1 != cols.length)
                        output += ",";
                }
                output += "\n";
            }
            writer.write(output);
        }
        System.out.println("Writing result in file: "+output);
        return output;
    }
    
}