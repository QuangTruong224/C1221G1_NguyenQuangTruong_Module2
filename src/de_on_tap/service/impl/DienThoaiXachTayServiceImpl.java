package de_on_tap.service.impl;

import de_on_tap.models.DienThoaiXachTay;
import de_on_tap.service.DienThoaiXachTayService;
import de_on_tap.utils.RegexData;
import de_thi_c10.controller.Menu;
import de_thi_c10.models.NhapKhau;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DienThoaiXachTayServiceImpl implements DienThoaiXachTayService {
    private static final String REGEX_SO_DUONG = "^[1-9]\\d*$";

    public void write(List<DienThoaiXachTay> dienThoaiXachTayList, boolean append) {
        try {
            FileWriter fileWriter = new FileWriter("D:\\CodeGym\\module_2\\src\\de_on_tap\\data\\dienthoaixachtay.csv", append);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (DienThoaiXachTay dienThoaiXachTay : dienThoaiXachTayList) {
                bufferedWriter.write(dienThoaiXachTay.getId() + "," + dienThoaiXachTay.getTenDienThoai() + "," +
                        dienThoaiXachTay.getGiaBan() + "," + dienThoaiXachTay.getSoLuong() + "," +
                        dienThoaiXachTay.getQuocGiaXachTay() + "," + dienThoaiXachTay.getTrangThai());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static List<DienThoaiXachTay> readerDienThoaiXachTay() {
        List<DienThoaiXachTay> dienThoaiXachTayList = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(new File("D:\\CodeGym\\module_2\\src\\de_on_tap\\data\\dienthoaixachtay.csv"));
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            String[] temp;
            while ((line = bufferedReader.readLine()) != null) {
                temp = line.split(",");
                DienThoaiXachTay dienThoaiXachTay = new DienThoaiXachTay(Integer.parseInt(temp[0]), temp[1], Integer.parseInt(temp[2]), Integer.parseInt(temp[3]), temp[4], temp[5]);
                dienThoaiXachTayList.add(dienThoaiXachTay);
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return dienThoaiXachTayList;
    }

    @Override
    public void addNew() {
//        int id, String tenDienThoai, int giaBan,
//        int soLuong, String quocGiaXachTay, String trangThai
        List<DienThoaiXachTay> dienThoaiXachTayList = readerDienThoaiXachTay();
        Scanner scanner = new Scanner(System.in);
        int id;
        if (dienThoaiXachTayList.size() == 0) {
            id = 1;
        } else {
            id = dienThoaiXachTayList.get(dienThoaiXachTayList.size() - 1).getId() + 1;
        }
        String tenDienThoai;
        while (true) {
            System.out.println("Th??m t??n ??i???n tho???i m???i");
            tenDienThoai = scanner.nextLine();
            if (tenDienThoai.trim().equals("")) {
                System.out.println("Nh???p l???i");
            } else {
                break;
            }
        }
        int giaBan;
        do {
            try {
                System.out.println("Th??m gi?? b??n m???i");
                giaBan = Integer.parseInt(RegexData.checkStr(scanner.nextLine(), REGEX_SO_DUONG, "B???t bu???c nh???p s??? d????ng"));
                break;
            } catch (NumberFormatException e) {
                System.out.println("Nh???p sai ?????nh d???ng");
            }
        } while (true);
//        System.out.println("Th??m s??? l?????ng m???i");
//        int soLuong = Integer.parseInt(RegexData.checkStr(scanner.nextLine(), REGEX_SO_DUONG, "B???t bu???c nh???p s??? d????ng"));
        int soLuong;
        do {
            try {
                System.out.println("Th??m s??? l?????ng m???i");
                soLuong = Integer.parseInt(RegexData.checkStr(scanner.nextLine(), REGEX_SO_DUONG, "B???t bu???c nh???p s??? d????ng"));
                break;
            } catch (NumberFormatException e) {
                System.out.println("Nh???p sai ?????nh d???ng");
            }
        } while (true);
        String quocGiaXachtay;
        while (true) {
            System.out.println("Th??m qu???c gia x??ch tay m???i");
            quocGiaXachtay=scanner.nextLine();
            if (quocGiaXachtay.equals("Viet Nam")) {
                System.out.println("Qu???c gia x??ch tay: kh??ng ???????c l?? ???Viet Nam???");

            } else if (quocGiaXachtay.trim().equals("")) {
                System.out.println("nh???p l???i");

            } else {
                break;
            }
        }
        System.out.println("Vui l??ng ch???n tr???ng th??i ??i???n tho???i");
        System.out.println("1.???? s???a ch???a");
        System.out.println("2.Ch??a s???a ch???a");

        String trangThai = " ";
        int choose = 0;
        try {
            choose = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(" vui l??ng nh???p l???i ");
        }
        switch (choose) {
            case 1:
                trangThai = "???? s???a ch???a";
                break;
            case 2:
                trangThai = "Ch??a s???a ch???a";
                break;
        }
        DienThoaiXachTay dienThoaiXachTay = new DienThoaiXachTay(id,tenDienThoai,giaBan,soLuong,quocGiaXachtay,trangThai);
        List<DienThoaiXachTay> dienThoaiXachTayList2 = new ArrayList<>();
        dienThoaiXachTayList2.add(dienThoaiXachTay);
        write(dienThoaiXachTayList2, true);
    }

    @Override
    public void delete() {
        boolean check = false;
        Scanner scanner = new Scanner(System.in);
        displayDT();
        System.out.println("Nh???p ID c???n x??a");
        int xoaID = Integer.parseInt(scanner.nextLine());
        List<DienThoaiXachTay> dienThoaiXachTayList = readerDienThoaiXachTay();
        for (int i = 0; i < dienThoaiXachTayList.size(); i++) {

            if (dienThoaiXachTayList.get(i).getId() == xoaID) {
                System.out.println("B???n c?? mu???n x??a kh??ng:");
                System.out.println("1.C??");
                System.out.println("2.Kh??ng");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        dienThoaiXachTayList.remove(i);
                        System.out.println("???? x??a r???i nh??!");
                        break;
                    case 2:
                        Menu.displayMenu();
                        break;
                }
            }
        }
        write(dienThoaiXachTayList, false);
    }

    @Override
    public void displayDT() {
        List<DienThoaiXachTay> dienThoaiXachTayList = readerDienThoaiXachTay();
        for (DienThoaiXachTay dienThoaiXachTay : dienThoaiXachTayList) {
            System.out.println(dienThoaiXachTay);
        }
    }

    @Override
    public void searchDT() {
        List<DienThoaiXachTay> dienThoaiXachTayList = readerDienThoaiXachTay();
        Scanner scanner = new Scanner(System.in);
        displayDT();
        try {
            System.out.println("Nh???p id c???n t??m");
            int checkId = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < dienThoaiXachTayList.size(); i++) {

                if (dienThoaiXachTayList.get(i).getId() == checkId) {

                    System.out.println("???? t??m ki???m th??nh c??ng");
                    System.out.println(dienThoaiXachTayList.get(i));
        }

            }
        }catch (NumberFormatException e) {
            System.out.println("Vui l??ng nh???p ????ng ID c???n t??m");
        }

    }
}
