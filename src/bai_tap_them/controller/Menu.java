package bai_tap_them.controller;

import bai_tap_them.service.OtoService;
import bai_tap_them.service.XeMayService;
import bai_tap_them.service.XeTaiService;
import bai_tap_them.service.impl.OtoImpl;
import bai_tap_them.service.impl.XeMayImpl;
import bai_tap_them.service.impl.XeTaiImpl;

import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        displayMenu();
    }

    public static void displayMenu() {
        boolean check = true;
        int choice = 0;
        while (check) {
            System.out.println("QUẢN LÝ PHƯƠNG TIỆN GIAO THÔNG");
            System.out.println("1. Thêm mới phương tiện");
            System.out.println("2. Hiển thị phương tiện");
            System.out.println("3. Xóa phương tiện");
            System.out.println("4. Thoát");
            Scanner scanner = new Scanner(System.in);
            try {
                choice = Integer.parseInt(scanner.nextLine());



            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập lại");

            }
            switch (choice) {
                case 1:
                    addNew();
                    break;
                case 2:
                    display();
                    break;
                case 3:
                    delete();
                    break;
                case 4:
                    System.exit(4);
                    break;
            }
        }

    }

    public static void addNew() {
        OtoService otoService = new OtoImpl();
        XeMayService xeMayService = new XeMayImpl();
        XeTaiService xeTaiService = new XeTaiImpl();
        boolean check = true;
        int choice = 0;
        while (check) {
            System.out.println("1.  Thêm mới oto ");
            System.out.println("2.  Thêm mới xe máy ");
            System.out.println("3. Thêm mới xe tải");
            System.out.println("4. Thoát");
            Scanner scanner = new Scanner(System.in);
            try {
                choice = Integer.parseInt(scanner.nextLine());

            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập lại");

            }
            switch (choice) {
                case 1:
                    otoService.addOto();
                    break;
                case 2:
                    xeMayService.addXeMay();
                    break;
                case 3:
                    xeTaiService.addXeTai();
                    break;
                case 4:
                    displayMenu();
                    break;

            }

        }
    }

    public static void display() {
        boolean check = true;
        int choice = 0;
        OtoService otoService = new OtoImpl();
        XeMayService xeMayService = new XeMayImpl();
        XeTaiService xeTaiService = new XeTaiImpl();
        while (check) {
            System.out.println("1.  Hiển thị oto ");
            System.out.println("2.  Hiển thị xe máy ");
            System.out.println("3. Hiển thị xe tải");
            System.out.println("4. Thoát");
            Scanner scanner = new Scanner(System.in);
            try {
                choice = Integer.parseInt(scanner.nextLine());

            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập lại");

            }
            switch (choice) {
                case 1:
                    otoService.displayOto();
                    break;
                case 2:
                    xeMayService.displayXeMay();
                    break;
                case 3:
                    xeTaiService.displayXeTai();
                    break;
                case 4:
                    displayMenu();
                    break;
            }

        }
    }

    public static void delete() {
        boolean check = true;
        int choice = 0;
        OtoService otoService = new OtoImpl();
        XeMayService xeMayService = new XeMayImpl();
        XeTaiService xeTaiService = new XeTaiImpl();
        while (check) {
            System.out.println("1. xóa oto ");
            System.out.println("2.  xóa xe máy ");
            System.out.println("3. xóa xe tải");
            System.out.println("4. Thoát");
            Scanner scanner = new Scanner(System.in);
            try {
                choice = Integer.parseInt(scanner.nextLine());

            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập lại");
            }
            switch (choice) {
                case 1:
                    otoService.deleteOto();
                    break;
                case 2:
                    xeMayService.deleteXeMay();
                    break;
                case 3:
                    xeTaiService.deleteXeTai();
                    break;
                case 4:
                    displayMenu();
                    break;

            }

        }
    }
}
