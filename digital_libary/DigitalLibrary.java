package completed_working_code;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

class BookFrame{
    private int id;
    private String title;
    private String  author;
    public BookFrame(int a,String b,String c){
        this.id = a;
        this.title =b;
        this.author = c;
    }
    public String getAuthor() {
        return author;
    }
    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
        
}

class Book extends BookFrame{
    private boolean available;
    public Book(int a,String b,String c){
        super(a,b,c);
        this.available = true;
    }
    public Book(int a,String b,String c,boolean d){
        super(a,b,c);
        this.available = d; 
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }
    public boolean getAvailable(){
        return available;
    }
  
}



class Membre{
    private int id;
    private String name;
    private BookFrame borrowedBook;
    private boolean borrowedBookstate;//flase default mean non borrowed

    /*public Member(int a,String b,BookFrame c,boolean d){
        this.id =a;
        this.name = b;
        this.borrowedBook = c;
        this.borrowedBookstate =d;
    }*/

    public Membre(int a,String b){
        this.id = a;
        this.name = b;
        borrowedBook = null;
        borrowedBookstate = false;
    }
    
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public BookFrame getBorrowedBook() {
        return borrowedBook;
    }
    public void setBorrowedBook(BookFrame borrowedBook) {
        this.borrowedBook = borrowedBook;
    }
    public void setBorrowedBookState(boolean a){
        borrowedBookstate = a;
    }
    public boolean getBorrowedBookState(){
        return borrowedBookstate;
    }
}

class Libarary{
    ArrayList<Book> listBooks;
    ArrayList<Membre> listMembres;
    public Libarary(){
        listBooks = new ArrayList<>();
        listMembres = new ArrayList<>();
    }
    public ArrayList<Book> getListBooks() {
        return listBooks;
    }public void setListBooks(ArrayList<Book> listBooks) {
        this.listBooks = listBooks;
    }
    public ArrayList<Membre> getListMembres() {
        return listMembres;
    }
    public void setListMembres(ArrayList<Membre> listMembres) {
        this.listMembres = listMembres;
    }
    public int isBookExist(String bookName){
        int index = 0;
        boolean bouton = false;
        for(Book br:listBooks){
            if(br.getTitle().equals(bookName)){
                bouton = true;
                break;
            }
            index++;
        }
        if (bouton) {
            return index;
        }
        return -1;
    }
    public int  isMemberexist(int id){
        int index = 0;
        boolean bouton=false;
        for(Membre m:listMembres){
            if (m.getId() == id) {
                bouton = true;
                break;    
            }
            index++;
        }
        if (bouton) {
            return index;
        }
        return -1;
    }

    public void borrowBook(int id,String bookName) throws Exception{
        //if (!listBooks.contains(b)) {
        //     throw new Exception("book not founded .") ;
        //}
        //if (!listMembres.contains(m)) {
        //    throw new Exception("error ,no match member.");
        //}
        int indexOfBook = isBookExist(bookName);
        int indexOfMembre = isMemberexist(id);
        
        if (indexOfMembre == -1) {
            throw new Exception("member not found "+id);
        }
        if (indexOfBook == -1) {
            throw new Exception("book not found "+bookName);
        }

        /*i dont think this might work cause i just effect the operation in th local book and memeber */
        //Book book = listBooks.get(indexOfBook);
        //Membre membre = listMembres.get(indexOfMembre);
        
        //if (book.getAvailable()) {
        //   membre.setBorrowedBook(b);
        //    book.setAvailable(false);
        //}
        if (listBooks.get(indexOfBook).getAvailable()){
            if(listMembres.get(indexOfMembre).getBorrowedBookState()){// i just use c logic to handle it  
                System.out.println("cannot borrow book for "+listMembres.get(indexOfMembre).getName()+" member, already have borrowed book.");
                return;
            }
            listBooks.get(indexOfBook).setAvailable(false);
            int idbookBorrowed = listBooks.get(indexOfBook).getId();
            String athourtBook = listBooks.get(indexOfBook).getAuthor();
            listMembres.get(indexOfMembre).setBorrowedBook(new BookFrame(idbookBorrowed, bookName,athourtBook));
            listMembres.get(indexOfMembre).setBorrowedBookState(true);
            System.out.println("book borrowed successfully.");
        }else{
            throw new Exception("this book is not available "+bookName);
        }
    }

    public void returnBook(int id ,String bookName) throws Exception{// i think there is bug here that prevents from channging the  state of book
        int indexOfMembre = isMemberexist(id);
        int indexOfBook = isBookExist(bookName);

        if (indexOfMembre == -1) {
            throw new Exception("member not found "+id);
        }
        if (indexOfBook == -1) {
            throw new Exception("book not found "+bookName);
        }
        Book book = listBooks.get(indexOfBook);
        Membre member = listMembres.get(indexOfMembre);
        if(member.getBorrowedBookState()){
            book.setAvailable(true);// i dont know if i should use book or listbook.get to modified cause i think that i have to modefid and book mitgh be local.
            member.setBorrowedBook(null);
            member.setBorrowedBookState(false);
            System.out.println("book "+book.getTitle()+" returned successfully from "+member.getName());
        }
        // i think that retunr is fine cause it look fine and also i test it for member it wokr but for 
        //book no there is case when i borrow a book and quit and run the programme and retunr so member did change but
        //book not say flase  so if there is bad logic it migh effect of member here  so i think the bug is in update book
    }

    public void showAvailableBooks(){
        for(Book b:listBooks){
            if (b.getAvailable()) {
                System.out.println("id: "+b.getId()+" title: "+b.getTitle()+" author: "+b.getAuthor());
            }
        }
    }

    public void showMemebers(){
        for(Membre m:listMembres){
            System.out.print("id: "+m.getId()+" name: "+m.getName());
            if (m.getBorrowedBookState()) {
                System.out.println(" borrowed book: "+m.getBorrowedBook().getTitle());
            }else{
                System.err.println(".");
            }
        }
    }
}

class LibararyManagement{
    private Libarary libarary;
    private static final String FILEMEMBER = "member.txt";
    private static final String FILEBOOK = "book.txt";
    /*i create does to files in my current reperoire so i just use ther name to acces */
    // here where we mangage the libary like add books or remove mangaet list of 
    //member lets say this is the employement of this libarary.
    LibararyManagement(){
        libarary = new Libarary();
    }
    // from there to 
    public void addMember(Membre m,boolean managemenentPermission){
        if (!managemenentPermission) {
            System.out.println("cannot proforme such operation.");
            return;
        }
        if(m != null){
            if (libarary.isMemberexist(m.getId()) == -1) {
                libarary.getListMembres().add(m);
                saveNewMebersInFile(m);
                System.out.println("member are subscrip successfully");
            }else{
                System.out.println("member is exit");
            }
        }else{
            System.out.println("arrgument is null pointer.");
        }
    }
    public void removeMember(int id,boolean permissionManagement){
        if (!permissionManagement) {
            System.out.println("cannot proform such operation.");
            return;
        }
        /*if (id == null) {// i use it for m as object member but i modified it to id cause it easy to handle in interface 
            System.out.println("arrgument is null pointer.");
            return;
        }*/
        int indexOfMembre = libarary.isMemberexist(id);
        if (indexOfMembre != -1) {
            libarary.getListMembres().remove(indexOfMembre);
            this.updateMemberFile();
            System.out.println("member remved from successfully");
        }else{
            System.out.println("member already does not exist.");
        }
    }
    public void addBook(Book b,boolean managemenentPermission){
        if (!managemenentPermission) {
            System.out.println("cannot proforme such operation.");
            return;
        }
        if (b == null) {
            System.out.println("argument is null pointer");
            return;
        }
        if (libarary.isBookExist(b.getTitle()) == -1) {
            libarary.getListBooks().add(b);
            saveNewBookInFile(b);
            System.out.println("book add  successfully.");
        }else{
            System.out.println("book already exist in labarary.");
        }
    }
    public void removeBook(String  titleOfBook,boolean managemenentPermission){
        if (!managemenentPermission) {
            System.out.println("cannot proforme such operation.");
            return;
        }
        if (titleOfBook == null) {
            System.out.println("argument is null pointer");
        }
        int indexOfBook = libarary.isBookExist(titleOfBook);
        if (indexOfBook != -1) {
            libarary.getListBooks().remove(indexOfBook);
            this.updateBookrFile();
        }else{
            System.out.println("book already not exist.");
        }
    }
    // to there all does fucntion just to manage books and member in system 
    // and i might just redonnace the code here but i like to separte fucntionalite and make it simple to modified and maintaine
    // rather then less code and complex method that i can read later or use flixibel in other part of progarame.
    //***and now form there i gonna write the method responsable on file handaling  */
    private void saveNewMebersInFile(Membre m){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILEMEMBER,true))) {
            if(m.getBorrowedBook() != null){
                String line = m.getId()+"|"+m.getName()+"|"+m.getBorrowedBookState()+"|"+m.getBorrowedBook().getId();
                bw.write(line);
                bw.newLine();
            }else{
                String line = m.getId()+"|"+m.getName()+"|"+m.getBorrowedBookState()+"|"+"-1";
                bw.write(line);
                bw.newLine();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void saveNewBookInFile(Book b){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILEBOOK,true))) {
            String line = b.getId()+"|"+b.getTitle()+"|"+b.getAuthor()+"|"+b.getAvailable();
            bw.write(line);
            bw.newLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void updateMemberFile(){
        try (BufferedWriter bw  = new BufferedWriter(new FileWriter(FILEMEMBER))) {
            ArrayList<Membre> listtemp = libarary.getListMembres();
            for(Membre m:listtemp){
                if(m.getBorrowedBook() != null){
                    String line = m.getId()+"|"+m.getName()+"|"+m.getBorrowedBookState()+"|"+m.getBorrowedBook().getId();
                    bw.write(line);
                    bw.newLine();
                }else{
                    String line = m.getId()+"|"+m.getName()+"|"+m.getBorrowedBookState()+"|"+"-1";
                    bw.write(line);
                    bw.newLine();
                }
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void updateBookrFile(){
        try (BufferedWriter bw  = new BufferedWriter(new FileWriter(FILEBOOK))) {
            ArrayList<Book> listtemp = libarary.getListBooks();
            for(Book b:listtemp){
                    String line = b.getId()+"|"+b.getTitle()+"|"+b.getAuthor()+"|"+b.getAvailable();// so here should set the state fo the book borrowed or not but i stay flase when return so i but member book state are updated in file .
                    bw.write(line);
                    bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void loedFromFilebook(boolean managemenentPermission){
        if (!managemenentPermission) {
            System.out.println("you re not allowed to proforem such operation.");
            return;
        }
        try (BufferedReader br =new BufferedReader(new FileReader(FILEBOOK))) {
            String line;
            String[] args;
            ArrayList<Book> listtemp = libarary.getListBooks();
            Book tempBook;
            listtemp.clear();
            while((line = br.readLine()) != null){
                args = line.split("\\|");
                int id = Integer.parseInt(args[0]);
                boolean available = Boolean.parseBoolean(args[3]);
                tempBook = new Book(id,args[1],args[2],available);
                listtemp.add(tempBook);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void loedFromFileMember(boolean managemenentPermission){
        if (!managemenentPermission) {
            System.out.println("you re not allowed to proforem such operation.");
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(FILEMEMBER))) {
            String line;
            //there is error here.
            //alwayes loed book list adn then meber later.
            ArrayList<Book> listBookstemp =libarary.getListBooks();
            String[] args;
            ArrayList<Membre> listempMembers = libarary.getListMembres();
            Membre tempMembre;
            BookFrame tempBookFrameB;
            listempMembers.clear();
            while ((line = br.readLine())!= null) {
                args = line.split("\\|");
                int id = Integer.parseInt(args[0]);
                int borroewedBookId =Integer.parseInt(args[3]);
                //Book b = listBookstemp.get(indexOfBook);
                //tempBookFrameB = new BookFrame(b.getId(),b.getTitle(),b.getAuthor());
                //boolean state = Boolean.parseBoolean(args[3]);
                tempMembre = new Membre(id,args[1]);
                if (borroewedBookId != -1) {
                    for(Book b:listBookstemp){
                        if (b.getId() == borroewedBookId) {
                            tempBookFrameB = new BookFrame(b.getId(), b.getTitle(), b.getAuthor());
                            tempMembre.setBorrowedBook(tempBookFrameB);
                            tempMembre.setBorrowedBookState(true);
                            break;
                        }
                    }
                }
                listempMembers.add(tempMembre);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    public Libarary getLibarary() {
        return libarary;
    }
    public static String getFilebook() {
        return FILEBOOK;
    }
    public static String getFilemember() {
        return FILEMEMBER;
    }
    public void saveAndQuit(){
        this.updateBookrFile();
        this.updateMemberFile();
    }
    // permission i gonna use in interface like not any one can do does operaion like loed  or save.
}

class LibararyInterface{
    //here i the loby of the labarary interface.
    private LibararyManagement libararyManagement;
    private boolean permissionManagement;
    private int choice;
    LibararyInterface(boolean permission){
        libararyManagement = new LibararyManagement();
        permissionManagement = permission;
        choice = 0;
    }
    public void showInterfaceOption(){
        System.out.println("====welcome to the forbend libarary====");
        System.out.println("1-borrow a book.");
        System.out.println("2-return a book");
        System.out.println("3-show available books");
        System.out.println("4-show all member");
        System.out.println("5-quit");
        System.out.println("===give your choice as seing up 1 to 5===");
    }
    public void choiceProcessInterfacePart(Scanner sc) throws Exception{
        
        while (!sc.hasNextInt()) {
            System.out.println("XXXXX>give your choice as interger beetwen 1 to 5<XXXXX");
            sc.next();
        }
        choice = sc.nextInt();
        // i dont know if i should add sc.nextline to cunsoeme the line but i think this migh cause problem for 1 and 2 case switch.but at all it migh work.
        sc.nextLine();
        int id;
        String title;
        switch (choice) {
            case 1:
                
                System.out.println("|||give the title fo the book|||");
                title = sc.nextLine();
                System.out.println("|||give your id|||");
                while (!sc.hasNextInt()) {
                    System.out.println("XXXXX>invalid id ,give you id as integer<XXXXX");
                    sc.next();
                }
                id = sc.nextInt();
                libararyManagement.getLibarary().borrowBook(id, title);
                break;
            case 2:
                System.out.println("|||give the title fo the book|||");
                title = sc.nextLine();
                System.out.println("|||give your id|||");
                while (!sc.hasNextInt()) {
                    System.out.println("XXXXX>invalid id ,give you id as integer.<XXXXX");
                    sc.next();
                }
                id = sc.nextInt();
                libararyManagement.getLibarary().returnBook(id,title);
                break;
            case 3:
                libararyManagement.getLibarary().showAvailableBooks();
                break;
            case 4:
                if (permissionManagement) {
                    libararyManagement.getLibarary().showMemebers();
                    break;
                }
                System.out.println("XXXXX>permission denied, cannot proforem such operation.<XXXXX");
                break;
            case 5:
                System.out.println("###your welcome again any soon later.###");// i tink this message has langague error aslo this comment but i just now focus more on code .
                break;
            default:
                System.out.println("XXXXX>provide the correcte choice<XXXXX");
                break;
        }
    }
    public int idGenerater() throws Exception{
        int id;
        BufferedReader br = new BufferedReader(new FileReader("id_counter.txt"));
        String line = br.readLine();
        id = Integer.parseInt(line);
        br.close();
        BufferedWriter bw = new BufferedWriter(new FileWriter("id_counter.txt"));// i know this is might be dengeour read and try to write in file at the same time but i dont have time to find the best way lets just try this now since we  just learn.
        int copyId = id;
        copyId++;
        bw.write(Integer.toString(copyId));
        bw.close();
        return id;
    }
    public void showManagementOption(){
        System.out.println("===welcome to interface management===");
        System.out.println("1-add new book.");
        System.out.println("2-remove book.");
        System.out.println("3-reload book list from file.");
        System.out.println("4-reload member list from file");
        System.out.println("5-add member");
        System.out.println("6-remove member");
        System.out.println("7-to quit");
        System.out.println("===give your choice===");
    }
    public void choiceProcessManagement(Scanner sc) throws Exception{
        while (!sc.hasNextInt()) {
            System.out.println("XXXXX>give the choice as integer.<XXXXX");
            sc.next();
        }
        choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1:
                int id = idGenerater();//so for the idea of id i want it to be unique and autou generated.like is not nessere to follow the order but just like alwaser keep incressing for the whole time not just for the life time of the programe
                String title;
                System.out.println("|||give the name of book.|||");
                title = sc.nextLine();
                System.out.println("|||give the author of book|||");
                String author;
                author = sc.nextLine();
                Book b = new Book(id, title, author);
                libararyManagement.addBook(b, permissionManagement);
                // i know that i use try catch in nasty way i should make the function throw the error and use one try with multiple catch but i cant now modifid  the code cause its to long .
                /*i modified that later cause there is redonnonce of code  */
                break;
            case 2:
                System.out.println("|||give the name of book to remove.|||");
                String tl =sc.nextLine();
                libararyManagement.removeBook(tl, permissionManagement);
                break;
            case 3:
                libararyManagement.loedFromFilebook(permissionManagement);
                System.out.println("VVVVV>operation completed<VVVVV");
                break;
            case 4:
                libararyManagement.loedFromFileMember(permissionManagement);
                System.out.println("VVVVV>operation completed<VVVVV");
                break;
            case 5:
                int idMember =idGenerater();
                String name;
                System.out.println("|||give the name of member|||");
                name = sc.nextLine();
                Membre m = new Membre(idMember, name);
                libararyManagement.addMember(m, permissionManagement);
                break;
            case 6:
                System.out.println("|||give the id of member to remove.|||");
                while (!sc.hasNextInt()) {
                    System.out.println("XXXXX>invaldi id ,provide as integer<XXXXX");
                    sc.next();
                }
                int idMember1 = sc.nextInt();
                libararyManagement.removeMember(idMember1, permissionManagement);
                break;
            case 7:
                System.out.println("###management interface are closed###");
                break;
            default:
                System.out.println("XXXXX>provide the corrcet option.<XXXXX");
                break;
        }

    }
    public int getChoice() {
        return choice;
    }
    public LibararyManagement getLibararyManagement() {
        return libararyManagement;
    }
}
public class DigitalLibrary {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean permissionManagement = true;
        LibararyInterface mylibarary = new LibararyInterface(permissionManagement);//lets say that a loby when you just take and give back books
        String option;
        int options = 0;
        try{/* while (true) {
            System.out.println("wanna exit the programme print yes to do or any thing to stay..........");
            option = sc.nextLine();
            if(option.trim().equals("yes")){
                sc.close();
                break;
            }
            if (permissionManagement) {
                while (options != 3) {
                    System.out.println("!!!>print 1 to go to intrface mangament 2 to go to interfacelibary or 3 to quit<!!!");
                    while (!sc.hasNextInt()){
                        System.out.println("XXXXX>give the choice as inerger 1 or 2 or 3 to quit.<XXXXX");
                        sc.next();
                    }
               
                    while (options < 1 || options > 3) {
                        options = sc.nextInt();
                        sc.nextLine();
                    }
                
                
                    while (true) {
                        if (options == 1) {
                            mylibarary.showInterfaceOption();
                            mylibarary.choiceProcessInterfacePart(sc);
                            if (mylibarary.getChoice() == 5) {
                                break;
                            }
                        }else if ( options == 2) {
                            mylibarary.showManagementOption();
                            mylibarary.choiceProcessManagement(sc);
                            if (mylibarary.getChoice() == 7) {
                                break;
                            }
                        }
                    }
                    String n = sc.nextLine();
                    System.out.println(n);
                }
                
            }else{
                while (true) {
                    mylibarary.showInterfaceOption();
                    mylibarary.choiceProcessInterfacePart(sc);
                    if (mylibarary.getChoice() == 5) {
                        break;
                    }
                }
                    
            } */
        mylibarary.getLibararyManagement().loedFromFilebook(permissionManagement);
        mylibarary.getLibararyManagement().loedFromFileMember(permissionManagement);
        //hh here is where is the problem i just miss of the oreder the i set i should be book frist and mebeber secen not the oppossite.
        while (true) {
            System.out.println("wanna exit the programme? print 'yes' or anything to continue...");
            option = sc.nextLine();
            if(option.trim().equalsIgnoreCase("yes")){
                mylibarary.getLibararyManagement().saveAndQuit();
                sc.close();
                break;
            }   
    
            // Ask which interface
            System.out.println("1-Management Interface  2-Library Interface  3-Quit");
            while (!sc.hasNextInt()){
                System.out.println("Enter 1, 2, or 3");
                sc.next();
            }
            options = sc.nextInt();
            sc.nextLine();
    
            if (options == 3) {
                mylibarary.getLibararyManagement().saveAndQuit();
                sc.close();
                System.out.println("Goodbye!");
                break;
            }
    
            // Management interface
            if (options == 1 && permissionManagement) {
                mylibarary.showManagementOption();
                mylibarary.choiceProcessManagement(sc);
            } 
            // Library interface
            else if (options == 2) {
                mylibarary.showInterfaceOption();
                mylibarary.choiceProcessInterfacePart(sc);
            }
            else {
                System.out.println("Invalid choice or no permission");
            }
            
           
            }
            }catch(Exception e){
                e.printStackTrace();
            }
       
        }
}
/*so in main i just try to write the intface phase but i thikn that this code might have a lot of error cause i dont test it while i coding it cause it p
 * painfull why first i should inisialisate the two file with data mean i have to write 
 * and i need every time compile and cheek  etc  i feel like it is complitly diffrent skill
 * of wrinting code the next thing is to learn how to test corrcelty and evern automate them
  */