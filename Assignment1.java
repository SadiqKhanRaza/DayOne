/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author macbook
 */
public class Assignment1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       /* List<Employee> empList= new ArrayList<>();
       String fileName = "TestCsv.csv";
		try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
			List<List<String>> values = lines.skip(1).map(l -> Arrays.asList(l.split(",")))
			.collect(Collectors.toList());
			values.forEach(value -> {
                            Employee e = new Employee(value.get(0),value.get(1),value.get(2));
                            empList.add(e);
                        });
                        System.out.println(empList.get(0).getName()+" "+empList.get(1).getSalary());
		} catch (IOException e) {
			e.printStackTrace();

                }*/
                
                //Read second file
                String fileName2 = "TestSec.csv";
                HashMap<String,Double> hm = new HashMap<>();
		try (Stream<String> lines = Files.lines(Paths.get(fileName2))) {
			List<List<String>> values = lines.skip(1).map(l -> Arrays.asList(l.split(",")))
			.collect(Collectors.toList());
			values.forEach(new Consumer<List<String>>() {
                            @Override
                            public void accept(List<String> value) {
                                String id=value.get(0);
                                DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                                Date d1=new Date();
                                try {
                                    d1 = df.parse(value.get(1));
                                } catch (ParseException ex) {
                                    Logger.getLogger(Assignment1.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                //DateFormat df2 = new SimpleDateFormat();
                                Date d2=new Date();
                                try {
                                    d2 = df.parse(value.get(2));
                                } catch (ParseException ex) {
                                    Logger.getLogger(Assignment1.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                double officeTime=(d2.getTime()-d1.getTime()) / (60 * 60 * 1000) % 24;
                                hm.put(id,officeTime);
                            }
                        });
                        System.out.println("Time : "+hm);
		} catch (IOException e) {
			e.printStackTrace();

                }
    }
    
}
