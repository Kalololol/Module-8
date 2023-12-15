package org.example;

import org.example.datamodel.CustomUser;
import org.example.services.CustomUserDAO;

import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    static final Scanner scanner = new Scanner(System.in);
    String choose;

    public static void main(String[] args) {
        boolean finish = false;


        do{
            System.out.println("Wybierz");
            System.out.println("1. dodaj");
            System.out.println("2. edytuj");
            System.out.println("3. usuń");
            System.out.println("4. wyświetl");
            System.out.println("0. koniec");
            String choose = scanner.next();
            switch (choose){
                case "1":
                    createUser();
                    finish = false;
                    break;
                case "2":
                    editUser();
                    finish = false;
                    break;
                case "3":
                    deleteUser();
                    finish = false;
                    break;
                case "4":
                    findUser();
                    finish = false;
                    break;
                case "0":
                    finish = true;
                    break;
                default:
                    System.out.println("Wprowadzono błędne dane");
                    finish = false;
            }

        }while(finish == false);

        System.out.println("koniec");

    }
    private static void editUser(){
        CustomUserDAO dao = new CustomUserDAO();
        CustomUser user = new CustomUser();

        System.out.println("Podaj id usera");
        long idUser = scanner.nextInt();
        user = dao.findUserById(idUser);
        System.out.println("Twój user to: " + user.toString());
        System.out.println("Edytuj jego dane");

        System.out.println("Podaj email: ");
        String email = scanner.next();
        user.setEmail(email);

        System.out.println("Podaj nazwisko: ");
        String name = scanner.next();
        user.setName(name);

        System.out.println("Podaj nazwisko: ");
        String surname = scanner.next();
        user.setLastName(surname);

        System.out.println("Podaj wiek: ");
        int age = scanner.nextInt();
        user.setAge(age);

        dao.editUser(user);
    }
    private static void createUser(){
        CustomUserDAO dao = new CustomUserDAO();
        CustomUser user = new CustomUser();
        System.out.println("Podaj email: ");
        String email = scanner.next();
        user.setEmail(email);

        System.out.println("Podaj imie: ");
        String name = scanner.next();
        user.setName(name);

        System.out.println("Podaj nazwisko: ");
        String surname = scanner.next();
        user.setLastName(surname);

        System.out.println("Podaj wiek: ");
        int age = scanner.nextInt();
        user.setAge(age);

        dao.createUser(user);

        System.out.println("Zapisano");
    }
    private static void deleteUser() {
        CustomUserDAO dao = new CustomUserDAO();
        System.out.println("Podaj email: ");
        String email = scanner.next();
        CustomUser user = dao.findUserByEmail(email);
        dao.deleteUser(user);
        System.out.println("Użytkownik został usunięty");
    }
    private static void findUser(){
        CustomUserDAO dao = new CustomUserDAO();
        System.out.println("Podaj adres email użytkownika");
        String email = scanner.next();
        CustomUser user = dao.findUserByEmail(email);
        System.out.println(user.toString());
    }
    }