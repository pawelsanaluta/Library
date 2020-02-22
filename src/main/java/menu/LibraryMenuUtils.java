package menu;

import java.util.LinkedHashMap;
import java.util.Map;

public class LibraryMenuUtils {
    static Map<Integer, String> COMMANDS = new LinkedHashMap<>();
    static Map<Integer, String> LIBRARY_MENU = new LinkedHashMap<>();
    static Map<Integer, String> LIBRARY_MENU_SEARCH_BOOK = new LinkedHashMap<>();
    static Map<Integer, String> CATALOGUE_MENU = new LinkedHashMap<>();
    static Map<Integer, String> CUSTOMER_MENU = new LinkedHashMap<>();
    static Map<Integer, String> CUSTOMER_MENU_SEARCH_CUSTOMER = new LinkedHashMap<>();

    static {
        COMMANDS.put(1, "Biblioteka");
        COMMANDS.put(2, "Katalog");
        COMMANDS.put(3, "Czytelnik");
        COMMANDS.put(4, "Wyjście");
        LIBRARY_MENU.put(1, "Katalog książek");
        LIBRARY_MENU.put(2, "Katalog czytelników");
        LIBRARY_MENU.put(3, "Aktualnie dostępne ksiązki");
        LIBRARY_MENU.put(4, "Aktualnie wypożyczone ksiązki");
        LIBRARY_MENU.put(5, "Zbliżający się termin oddania książek");
        LIBRARY_MENU.put(6, "Książki po terminie");
        LIBRARY_MENU.put(7, "Wyszukaj książkę");
        LIBRARY_MENU.put(8, "Wypożycz ksiązkę");
        LIBRARY_MENU.put(9, "Wyświetl wypożycznia czytelnika");
        LIBRARY_MENU.put(0, "Wyjście do głównego menu");
        LIBRARY_MENU_SEARCH_BOOK.put(1, "Wyszukaj książki po ID");
        LIBRARY_MENU_SEARCH_BOOK.put(2, "Wyszukaj książki po słowie kluczowym");
        LIBRARY_MENU_SEARCH_BOOK.put(3, "Wyszukaj książki po kategorii");
        LIBRARY_MENU_SEARCH_BOOK.put(4, "Powrót do głównego menu");
        CATALOGUE_MENU.put(1, "Dodaj książke");
        CATALOGUE_MENU.put(2, "Edytuj książkę");
        CATALOGUE_MENU.put(3, "Usuń książkę");
        CATALOGUE_MENU.put(4, "Wyświetl wszystkie pozycje");
        CATALOGUE_MENU.put(5, "Powrót do głównego menu");
        CUSTOMER_MENU.put(1, "Dodaj profil");
        CUSTOMER_MENU.put(2, "Edytuj profil");
        CUSTOMER_MENU.put(3, "Edytuj zgody marketingowe");
        CUSTOMER_MENU.put(4, "Usuń profil");
        CUSTOMER_MENU.put(5, "Znajdź czytelnika");
        CUSTOMER_MENU.put(6, "Wyświetl wszystkich cztelników");
        CUSTOMER_MENU.put(7, "Powrót do głównego menu");
        CUSTOMER_MENU_SEARCH_CUSTOMER.put(1, "Znajdź czytelnika po numerze PESEL");
        CUSTOMER_MENU_SEARCH_CUSTOMER.put(2, "Znajdź czytelnika po imieniu i nazwisku");

    }

    static void showMenu() {
        System.out.println("***** WITAJ W BIBLIOTECE ****");
        COMMANDS.forEach((key, val) -> System.out.println(String.format("%s:%-5s", key, val)));
    }

    static void showLibraryMenu() {
        LIBRARY_MENU.forEach((key, val) -> System.out.println(String.format("%-20s:,%-20s", key, val)));
    }

    static void showLibraryMenuSearchBook() {
        LIBRARY_MENU_SEARCH_BOOK.forEach((key, val) -> System.out.println(String.format("%-20s:,%-20s", key, val)));
    }

    static void showCatalogueMenu() {
        CATALOGUE_MENU.forEach((key, val) -> System.out.println(String.format("%-20s:,%-20s", key, val)));
    }

    static void showCustomerMenu() {
        CUSTOMER_MENU.forEach((key, val) -> System.out.println(String.format("%-20s:,%-20s", key, val)));
    }

    static void showCustomerMenuSearchCustomer() {
        CUSTOMER_MENU_SEARCH_CUSTOMER.forEach((key, val) -> System.out.println(String.format("%-20s:,%-20s", key, val)));
    }
}
