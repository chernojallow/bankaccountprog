
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;

class BankAccountProg{

	static ArrayList<personAcc> personList;
	//public static HashMap personList;

	public static void main(String[] args) {

        	personList = new ArrayList<personAcc>();
		//personList = new HasMap();
        	String names;
        	Random ran = new Random();
        	try{
            		BufferedReader read = new BufferedReader(new FileReader("names.txt"));
            	while((names = read.readLine()) != null){
                	personList.add(new personAcc((names)));
			//personList.put(new personAcc (names), ran.nextInt(200) + 1);
            		}
        	}
        	catch (Exception e){
            		System.out.println("Could not locate the file!");
        	}

        	for(int i = 0; i < personList.size(); i++){
            		personList.get(i).setAccount(i+1);	//sets an account number for each person added by 1 everytime
            		personList.get(i).setBalance(ran.nextInt(200) + 1);
            	//	personList.get(i).viewInfo();
        	}

		Scanner sc = new Scanner(System.in);
        	System.out.println("User Authentication\nEnter Name: ");
        	String name = sc.next();
        	authenticate(name); //calls out the method authenticate
    	}

	    public static void authenticate(String name){

        for(int i = 0; i < personList.size(); i++){

            if(name.equals(personList.get(i).viewName())) {
                passauthentication(personList.get(i));
                break;
            }
                //no else function
            else if(i == personList.size() - 1){
                System.out.println("No name found, Would you like to exit yes or no? ");
                Scanner sc = new Scanner(System.in);
                String name2 = sc.next();
                if(name2.equalsIgnoreCase("no") || name2.equalsIgnoreCase("n")){
                    System.out.println("Please enter name again: ");
                    String ans = sc.next();
                    authenticate(ans);
                }
                else if(name2.equalsIgnoreCase("yes") || name2.equalsIgnoreCase("y")){
                    return;
                }
                else
                {
                    System.out.println("Unknown answer, program terminated!");
                    return; //in case we don't get a yes or no answer
                }
            }
        }
    }

    public static void passauthentication(personAcc name){

        int option;
        do {
            System.out.println("What would like to do?\n1. Deposit\n2. Withdraw\n3. View Balance\n4. View Info\n5. Exit\n");
            Scanner sc = new Scanner(System.in);
            option = sc.nextInt();
	    System.out.println("****************************************");

            switch (option) {
                case 1:
                    System.out.println("How much do you want to deposit: \n");
                    int amount = sc.nextInt();
                    name.deposit(amount);
                    break;
                case 2:
                    System.out.println("How much do you want to withdraw: \n");
                    int amount2 = sc.nextInt();
                    if (name.getBalance() < amount2) {
                        System.out.println("Not enough funds!\n");
			System.out.println("****************************************");
                    } else
                        name.withdraw(amount2);
                    break;
                case 3:
                    name.viewBalance();
                    break;
                case 4:
                    name.viewInfo();
                    break;
                case 5:
                default:
                    System.out.println("You exited the program\n");
                    break;
            }
        } while (option == 1 || option == 2 || option == 3 || option == 4);
    }
}


