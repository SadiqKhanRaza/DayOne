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
                                String id=value.get(0);/*
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
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
    
        static HashMap<String,Double> hm = new HashMap<>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        HashMap<String ,Double> map=readInOutFiles("Refer");
         List<Employee1> empList= new ArrayList<>();
            String fileName = "Ref.csv";
            try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
            List<List<String>> values = lines.skip(1).map(l -> Arrays.asList(l.split(",")))
            .collect(Collectors.toList());
            values.stream().filter(element -> element != null && !element.isEmpty()).
            forEach(value -> {
                String empId=value.get(0);
                String name =value.get(1);
                String salPerDay=value.get(2);
                double salary=Double.parseDouble(salPerDay)*map.get(empId)/8;
                
            Employee1 e = new Employee1(empId,name,salPerDay,salary);
            empList.add(e);
            });
            System.out.println(empList.get(0).getName()+" "+empList.get(0).getSalPerDay()+" "+empList.get(0).getTotalSalary());
            } catch (IOException e) {
            e.printStackTrace();
            
            }
                
    }
    //Method to convert Date format (12hr to 24 hr)
    static String changeFormat(String dt)
    {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aa");
      //Desired format: 24 hour format: Change the pattern as per the need
      DateFormat outputformat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
      try{
    	 Date date= df.parse(dt);
         String output = outputformat.format(date);
    	 return output;
      }catch(ParseException pe){
       }
      return null;
    }
    
    //Method to read all In/Out csv files and return a hashmap containing EmpId and his/her total working days 
    static HashMap<String,Double> readInOutFiles(String folderPath)
    {
        try {
            Files.newDirectoryStream(Paths.get("Refer"), path -> path.toString().endsWith(".csv"))//for Directory
                    .forEach(e->{System.out.println(e);
                        //Read second file
                        //String fileName2 = "TestSec.csv";
                        
                        try (Stream<String> lines = Files.lines(e)) {//e is the path of each file in the directory
                            List<List<String>> values = lines.skip(1).map(l -> Arrays.asList(l.split(",")))
                                    .collect(Collectors.toList());
                            values.stream().filter(element -> element != null && element.size()>=3)//To check all values are there
                                    .forEach(new Consumer<List<String>>() {
                                        @Override
                                        public void accept(List<String> value) {
                                            
                                            String id=value.get(0);
                                            DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                                            Date d1=new Date();
                                            try {
                                                d1 = df.parse(changeFormat(value.get(1)));
                                                
                                            }
                                            catch (ParseException ex) {
                                                Logger.getLogger(Assignment1.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                            //DateFormat df2 = new SimpleDateFormat();
                                            Date d2=new Date();
                                            try {
                                                d2 = df.parse(changeFormat(value.get(2)));
                                            } catch (ParseException ex) {
                                                Logger.getLogger(Assignment1.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                            double officeTime=(d2.getTime()-d1.getTime()) / (60 * 60 * 1000) % 24;
                                            System.out.print(officeTime+" ");
                                            if(hm.containsKey(id))
                                            {
                                                if(officeTime>=4 && officeTime<8)
                                                    hm.put(id, hm.get(id)+4.0);
                                                else if(officeTime>=8)
                                                    hm.put(id, hm.get(id)+8.0);
                                                
                                            }
                                            else
                                            {
                                                if(officeTime>=4 && officeTime<8)
                                                    hm.put(id,4.0);
                                                else if(officeTime>=8)
                                                    hm.put(id,8.0);
                                            }
                                        }
                                    });
                            System.out.println("Time : "+hm);
                        } catch (IOException ex) {
                        }
                    });
        } catch (IOException ex) {
            Logger.getLogger(Assignment1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hm;
    }
}

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
