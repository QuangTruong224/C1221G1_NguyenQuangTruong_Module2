package de_on_tap.service.impl;

import de_on_tap.models.DienThoaiChinhHang;
import de_on_tap.models.DienThoaiXachTay;
import de_on_tap.service.DienThoaiChinhHangService;
import de_on_tap.utils.RegexData;
import de_thi_c10.controller.Menu;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DienThoaiChinhHangServiceImpl implements DienThoaiChinhHangService {
    private static final String REGEX_SO_DUONG = "^[1-9]\\d*$";

    public void write(List<DienThoaiChinhHang> dienThoaiChinhHangList, boolean append) {
        try {
            FileWriter fileWriter = new FileWriter("D:\\CodeGym\\module_2\\src\\de_on_tap\\data\\dienthoaichinhhang.csv", append);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (DienThoaiChinhHang dienThoaiChinhHang : dienThoaiChinhHangList) {
                bufferedWriter.write(dienThoaiChinhHang.getId() + "," + dienThoaiChinhHang.getTenDienThoai() + "," +
                        dienThoaiChinhHang.getGiaBan() + "," + dienThoaiChinhHang.getSoLuong() + "," +
                        dienThoaiChinhHang.getThoiGianBaoHanh() + "," + dienThoaiChinhHang.getPhamViBaoHanh());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static List<DienThoaiChinhHang> readerDienThoaiChinhHang() {
        List<DienThoaiChinhHang> dienThoaiChinhHangList = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(new File("D:\\CodeGym\\module_2\\src\\de_on_tap\\data\\dienthoaichinhhang.csv"));
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            String[] temp;
            while ((line = bufferedReader.readLine()) != null) {
                temp = line.split(",");
                DienThoaiChinhHang dienThoaiChinhHang = new DienThoaiChinhHang(Integer.parseInt(temp[0]), temp[1], Integer.parseInt(temp[2]), Integer.parseInt(temp[3]), temp[4], temp[5]);
                dienThoaiChinhHangList.add(dienThoaiChinhHang);
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return dienThoaiChinhHangList;
    }

    @Override
    public void addNew() {
//        int id, String tenDienThoai, int giaBan, int soLuong,
//        String thoiGianBaoHanh, String phamViBaoHanh
        List<DienThoaiChinhHang> dienThoaiChinhHangList = readerDienThoaiChinhHang();
        Scanner scanner = new Scanner(System.in);
        int id;
        if (dienThoaiChinhHangList.size() == 0) {
            id = 1;
        } else {
            id = dienThoaiChinhHangList.get(dienThoaiChinhHangList.size() - 1).getId() + 1;
        }
//        System.out.println("Th??m t??n ??i???n tho???i m???i");
//        String tenDienThoai = scanner.nextLine();
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
//        System.out.println("Th??m gi?? b??n m???i");
//        int giaBan = Integer.parseInt(RegexData.checkStr(scanner.nextLine(), REGEX_SO_DUONG, "B???t bu???c ph???i nh???p s??? d????ng"));
        int giaBan;
        do {
            try {
                System.out.println("Th??m gi?? b??n m???i");
                giaBan = Integer.parseInt(de_on_tap.utils.RegexData.checkStr(scanner.nextLine(), REGEX_SO_DUONG, "B???t bu???c nh???p s??? d????ng"));
                break;
            } catch (NumberFormatException e) {
                System.out.println("Nh???p sai ?????nh d???ng");
            }
        } while (true);
//        System.out.println("Th??m s??? l?????ng m???i");
//        int soLuong = Integer.parseInt(RegexData.checkStr(scanner.nextLine(), REGEX_SO_DUONG, "B???t bu???c ph???i nh???p s??? d????ng"));
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
//        System.out.println("Th??m th???i gian b???o h??nh");
//        String thoiGianBaoHanh = scanner.nextLine();
        String thoiGianBaoHanh;
        while (true) {
            System.out.println("th??m th???i gian b???o h??nh");
            thoiGianBaoHanh = RegexData.checkStr(scanner.nextLine(), REGEX_SO_DUONG, "B???t bu???c nh???p s??? d????ng");
            if (tenDienThoai.trim().equals("")) {
                System.out.println("Nh???p l???i");
            } else {
                break;
            }
        }
        System.out.println("Ch???n ph???m vi b???o h??nh");
        System.out.println("1.Qu???c t???");
        System.out.println("2.To??n qu???c");
        ;
        String baoHanh = " ";
        int choose = 0;
        try {
            choose = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(" vui l??ng nh???p l???i ");
        }
        switch (choose) {
            case 1:
                baoHanh = "To??n qu???c";
                break;
            case 2:
                baoHanh = "Qu???c t???";
                break;
        }
        DienThoaiChinhHang dienThoaiChinhHang = new DienThoaiChinhHang(id, tenDienThoai, giaBan, soLuong, thoiGianBaoHanh, baoHanh);
        List<DienThoaiChinhHang> dienThoaiChinhHangList2 = new ArrayList<>();
        dienThoaiChinhHangList2.add(dienThoaiChinhHang);
        write(dienThoaiChinhHangList2, true);
    }

    @Override
    public void delete() {
        boolean check = false;
        Scanner scanner = new Scanner(System.in);
        displayDT();
            System.out.println("Nh???p ID c???n x??a");
            int xoaID = Integer.parseInt(scanner.nextLine());
            List<DienThoaiChinhHang> dienThoaiChinhHangList = readerDienThoaiChinhHang();
            for (int i = 0; i < dienThoaiChinhHangList.size(); i++) {

                if (dienThoaiChinhHangList.get(i).getId() == xoaID) {
                    System.out.println("B???n c?? mu???n x??a kh??ng:");
                    System.out.println("1.C??");
                    System.out.println("2.Kh??ng");
                    int choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1:
                            dienThoaiChinhHangList.remove(i);
                            System.out.println("???? x??a r???i nh??!");
                            break;
                        case 2:
                            Menu.displayMenu();
                            break;
                    }
                }
            }
        write(dienThoaiChinhHangList,false);

    }



    @Override
    public void displayDT() {
        List<DienThoaiChinhHang> dienThoaiChinhHangList = readerDienThoaiChinhHang();
        for (DienThoaiChinhHang dienThoaiChinhHang : dienThoaiChinhHangList) {
            System.out.println(dienThoaiChinhHang);
        }
    }

    @Override
    public void searchDT() {
        List<DienThoaiChinhHang> dienThoaiChinhHangList = readerDienThoaiChinhHang();
        Scanner scanner = new Scanner(System.in);
        displayDT();
        try {
            System.out.println("Nh???p id c???n t??m");
            int checkId = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < dienThoaiChinhHangList.size(); i++) {

                if (dienThoaiChinhHangList.get(i).getId() == checkId) {

                    System.out.println("???? t??m ki???m th??nh c??ng");
                    System.out.println(dienThoaiChinhHangList.get(i));
        }

            }
        } catch (NumberFormatException e) {
            System.out.println("Vui l??ng nh???p ????ng ID mu???n t??m");
        }

    }
}
