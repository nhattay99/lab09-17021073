package lab9;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    /**
     * hàm đọc dữ liệu từ file
     * @param path đường dẫn
     * @return dữ liệu đọc được từ file
     */
    public static String readContentFromFile(String path){
        String s = "";
        try {
            File file = new File (path);
            FileReader reader = new FileReader (file);
            BufferedReader input = new BufferedReader (reader);
            String line = input.readLine ();
            while (line != null){
                s += line + "\n";
                line = input.readLine ();
            }
        }catch (Exception e){
            e.printStackTrace ();
        }
        return s;

    }

    /**
     * hàm xóa dữ liệu trong file và ghi dữ liệu mới vào file
     * @param path đường dẫn đến file
     * @param s dữ liệu cần ghi vào file
     */
    public static void writeContentToFile(String path, String s){
        try {
            File file = new File (path);
            FileWriter writer = new FileWriter (file);
            writer.write (s);
            writer.close ();
            System.out.println ("Write success!");
        }catch (Exception e){
            e.printStackTrace ();
        }
    }

    /**
     * hàm ghi thêm dữ liệu vào cuối file
     * @param path đường dẫn đến file
     * @param s dữ liệu cần ghi vào file
     */
    public static void writeContentToFile2(String path, String s){
        try {
            File file = new File (path);
            FileWriter writer = new FileWriter (file, true);
            writer.write (s);
            writer.close ();
            System.out.println ("Write success!");
        }catch (Exception e){
            e.printStackTrace ();
        }
    }


    /**
     * hàm tìm file trong thư mục
     * @param arrayList mảng các đường dẫn đến file cần tìm.
     * @param folderPath đường dẫn đến folder chứa file cần tìm.
     * @param fileName tên file cần tìm.
     */
    public static void findFile(ArrayList arrayList, String folderPath, String fileName){
        File file = new File (folderPath);
        if (file.exists ()){
            if (file.isFile ()){ // kiểm tra xem file là bình thường hay là thư mục
                if (file.getName ().equals (fileName)){
                    arrayList.add (file);
                }
            }else {
                File[] list = file.listFiles ();// mảng tên tệp hoặc thư mục con của thư mục đầu vào
                for (int i = 0; i < list.length; i++){
                    findFile (arrayList, list[i].getAbsolutePath (), fileName);
                }
            }
        }else {
            System.out.println ("Not found");
        }
    }


    /**
     * hàm tìm kiếm một tệp trong thư mục
     * @param folderPath tên thư mục
     * @param fileName tên tệp cần tìm kiếm
     * @return đường dẫn đến file cần tìm
     */
    public static ArrayList<File> findFileByName(String folderPath, String fileName){
        ArrayList<File> list = new ArrayList<> ();
        findFile (list, folderPath, fileName);
        return list;
    }

    public static void main(String[] args) {
        System.out.println (Utils.readContentFromFile ("File/hello.txt"));

        Utils.writeContentToFile ("File/hello2.txt", "Hello "+"\n");
        Utils.writeContentToFile2 ("File/hello2.txt", "Write last file");

        ArrayList arr = new ArrayList ();
        arr = findFileByName ("File", "hello.txt");

        for (int i = 0; i<arr.size (); i++){
            System.out.println (arr.get (i));
        }

    }
}
