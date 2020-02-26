package menu;

import customer.Address;
import customer.Approvals;
import customer.Customer;
import library.Library;

import java.io.IOException;
import java.util.Scanner;

import static menu.LibraryMenuUtils.*;

public class CustomerMenu {

    static void customer() throws IOException {
        Library library = Library.getInstance();
        Scanner scanner = new Scanner(System.in);
        showCustomerMenu();
        Customer customer;
        String profil = scanner.next();
        String firstName;
        String lastName;
        String pesel;
        String email;
        String phoneNumber;
        String city;
        String street;
        String addressNumber;
        String zipCode;
        switch (profil) {
            case "1":
                System.out.println("Podaj imię: ");
                firstName = scanner.next();
                System.out.println("Podaj nazwisko: ");
                lastName = scanner.next();
                System.out.println("Podaj PESEL: ");
                pesel = scanner.next();
                System.out.println("Podaj adres email: ");
                email = scanner.next();
                System.out.println("Podaj telefon: ");
                phoneNumber = scanner.next();
                System.out.println("Podaj miasto: ");
                city = scanner.next();
                System.out.println("Podaj ulicę: ");
                street = scanner.next();
                System.out.println("Podaj numer domu: ");
                addressNumber = scanner.next();
                System.out.println("Podaj kod pocztowy: ");
                zipCode = scanner.next();
                Address address = new Address(street, city, addressNumber, zipCode);
                library.createAndAddCustomer(firstName
                        , lastName, pesel, email, phoneNumber
                        , address);
                menuHandler();
                break;

            case "2":
                editCustomerData();
                editCustomerAddress();
                menuHandler();
                break;
            case "3":
                System.out.println("Podaj PESEL czytelnika: ");
                pesel = scanner.next();
                while(library.searchCustomerByPesel(pesel)==null){
                    System.out.println("Błędny PESEL, podaj ponownie: ");
                    pesel = scanner.next();
                }
                System.out.println("Czy na pewno chcesz cofnąć zgodę marketingową dla czytelnika: "
                        + library.searchCustomerByPesel(pesel).getFirstName()
                        + " " + library.searchCustomerByPesel(pesel).getLastName()
                        + " ? y/n");
                String changeApproval = scanner.next();
                if (changeApproval.equals("y")) {
                    library.searchCustomerByPesel(pesel).setApprovals(Approvals.MARKETING, false);
                    System.out.println("Zmieniono zgodę marketingową");
                }
                menuHandler();
                break;

            case "4":
                System.out.println("Podaj PESEL czytelnika do usunięcia: ");
                pesel = scanner.next();
                while(library.searchCustomerByPesel(pesel)==null){
                    System.out.println("Błędny PESEL, podaj ponownie: ");
                    pesel = scanner.next();
                }
                System.out.println("Czy na pewno chcesz usunąć czytelnika: "
                        + library.searchCustomerByPesel(pesel).getFirstName()
                        + " " + library.searchCustomerByPesel(pesel).getLastName()
                        + " ? y/n");
                String yes = scanner.next();
                if (yes.equals("y")) {
                    library.removeCustomer(pesel);
                    System.out.println("Usunięto czytelnika");
                }
                menuHandler();
                break;

            case "5":
                showCustomerMenuSearchCustomer();
                int searchCustomer = scanner.nextInt();
                if (searchCustomer == 1) {
                    System.out.println("Podaj numer PESEL czytelnika");
                    pesel = scanner.next();
                    while(library.searchCustomerByPesel(pesel)==null){
                        System.out.println("Błędny PESEL, podaj ponownie: ");
                        pesel = scanner.next();
                    }
                    library.searchCustomerByPesel(pesel);
                }
                if (searchCustomer == 2) {
                    System.out.println("Podaj imię i nazwisko czytelnika");
                    String firstAndLastName = scanner.next();
                    if (!firstAndLastName.contains(" ")) {
                        System.out.println("Błędne dane wejściowe, podaj imię i nazwisko czytelnika");
                    }
                    String[] afterDivision = firstAndLastName.split(" ");
                    firstName = afterDivision[0];
                    lastName = afterDivision[1];
                    library.searchCustomerByName(firstName, lastName);
                }
                menuHandler();
                break;
            case "6":
                Library.showCustomers(library.getCustomers());
                menuHandler();
            case "0":
                Menu.readAndExecute();
                break;
        }

    }

    private static void editCustomerData() throws IOException {
        Scanner scanner = new Scanner(System.in);
        Library library = Library.getInstance();
        boolean end = true;
        while (end) {
            showCustomerMenuEditData();
            String input = scanner.next();
            Customer customer;
            String pesel;
            switch (input) {
                case "1":
                    pesel = getCorrectPesel(scanner, library);
                    System.out.println("Podaj nowe imie: ");
                    String firstName = scanner.next();
                    library.editCustomerFirstName(pesel, firstName);
                    menuHandler();
                    break;
                case "2":
                    pesel = getCorrectPesel(scanner, library);
                    System.out.println("Podaj nowe nazwisko: ");
                    String lastName = scanner.next();
                    library.editCustomerLastName(pesel, lastName);
                    menuHandler();
                    break;
                case "3":
                    pesel = getCorrectPesel(scanner, library);
                    System.out.println("Podaj email: ");
                    String email = scanner.next();
                    library.editCustomerEmail(pesel, email);
                    menuHandler();
                    break;
                case "4":
                    pesel = getCorrectPesel(scanner, library);
                    System.out.println("Podaj  nowy numer telefonu: ");
                    String phoneNumber = scanner.next();
                    library.editCustomerPhoneNumber(pesel, phoneNumber);
                    menuHandler();
                    break;
                case "5":
                    editCustomerAddress();
                    menuHandler();
                case "0":
                    showCustomerMenu();
                    scanner.nextInt();
                    end = false;
            }
        }

    }

    private static void editCustomerAddress() throws IOException {
        Scanner scanner = new Scanner(System.in);
        Library library = Library.getInstance();
        boolean end = true;
        while (end) {
            showCustomerMenuEditAddress();
//            String pesel;
            Customer customer;
            String input = scanner.next();
            switch (input) {
                case "1":
                    customer = isEnteredPeselCorrect(scanner, library);
                    System.out.println("Podaj nową nazwę ulicy: ");
                    String street = scanner.next();
                    customer.getAddress().setStreet(street);
                    menuHandler();
                    break;
                case "2":
                    customer = isEnteredPeselCorrect(scanner, library);
                    System.out.println("Podaj nową nazwę miejscowości: ");
                    String city = scanner.next();
                    customer.getAddress().setCity(city);
                    menuHandler();
                    break;
                case "3":
                    customer = isEnteredPeselCorrect(scanner, library);
                    System.out.println("Podaj nowy numer domu: ");
                    String addressNumebr = scanner.next();
                    customer.getAddress().setAddressNumber(addressNumebr);
                    menuHandler();
                    break;
                case "4":
                    customer = isEnteredPeselCorrect(scanner, library);
                    System.out.println("Podaj nowy kod pocztowy: ");
                    String zipCode = scanner.next();
                    customer.getAddress().setStreet(zipCode);
                    menuHandler();
                    break;
                case "0":
                    Menu.readAndExecute();
                    end = false;
            }
        }
    }

    private static Customer isEnteredPeselCorrect(Scanner scanner, Library library) {
        Customer customer;
        String pesel = getCorrectPesel(scanner, library);
        customer = library.searchCustomerByPesel(pesel);
        return customer;
    }

    public static void menuHandler() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wciśnij [q] aby powrócić do menu Czytelnik");
        String finish = scanner.nextLine();
        if (finish.equals("q")) {
            CustomerMenu.customer();
        }
    }

    private static String getCorrectPesel(Scanner scanner, Library library) {
        String pesel;
        System.out.println("Podaj PESEL czytelnika do zmiany danych: ");
        pesel = scanner.next();
        while(library.searchCustomerByPesel(pesel)==null){
            System.out.println("Błędny PESEL, podaj ponownie: ");
            pesel = scanner.next();
        }
        return pesel;
    }


}
