package de_thi_c10.service.impl;

import de_thi_c10.controller.Menu;
import de_thi_c10.models.NhapKhau;
import de_thi_c10.models.XuatKhau;
import de_thi_c10.service.NhapKhauService;
import de_thi_c10.utils.RegexData;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NhapKhauImpl implements NhapKhauService {
//    static List<NhapKhau> nhapKhauList = new ArrayList<>();
    private static final String REGEX_SO_DUONG = "^[1-9]\\d*$";
//    static {
//       nhapKhauList = readerNhapKhau();
//    }

    public void write(List<NhapKhau> nhapKhauList,boolean append) {
        try {
            FileWriter fileWriter = new FileWriter("D:\\CodeGym\\module_2\\src\\de_thi_c10\\data\\nhapkhau.csv",append);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (NhapKhau nhapKhau:nhapKhauList) {
                bufferedWriter.write(nhapKhau.getId() + "," + nhapKhau.getMaSanPham() + "," +
                        nhapKhau.getTenSanPham() + "," + nhapKhau.getGiaBan() + "," +
                        nhapKhau.getSoLuong() + "," + nhapKhau.getNhaSanXuat() + "," +
                        nhapKhau.getGiaNhapKhau() + "," + nhapKhau.getTinhThanhNhap()+ "," + nhapKhau.getThueNhapKhau());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
    public static List<NhapKhau> readerNhapKhau() {
        List<NhapKhau> nhapKhauList=new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(new File("D:\\CodeGym\\module_2\\src\\de_thi_c10\\data\\nhapkhau.csv"));
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            String[] temp;
            while ((line = bufferedReader.readLine()) != null) {
                temp = line.split(",");
                NhapKhau nhapKhau = new NhapKhau(Integer.parseInt(temp[0]),temp[1],temp[2],Double.parseDouble(temp[3]),temp[4],temp[5],Double.parseDouble(temp[6]),temp[7],Integer.parseInt(temp[8]));
                nhapKhauList.add(nhapKhau);
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return nhapKhauList;

    }
    @Override
    public void addNew() {
//        int id, String maSanPham, String tenSanPham, double giaBan,
//        String soLuong, String nhaSanXuat, double giaNhapKhau,
//        String tinhThanhNhap, int thueNhapKhau
        List<NhapKhau> nhapKhauList1=readerNhapKhau();
        Scanner scanner = new Scanner(System.in);
//        System.out.println("Th??m id m???i");
        int id;
        if (nhapKhauList1.size()==0) {
            id=1;
        }else {
             id =nhapKhauList1.get(nhapKhauList1.size()-1).getId()+1;
        }

        System.out.println("Th??m m?? s???n ph???m m???i");
        String maSanPham = scanner.nextLine();
        System.out.println("Th??m t??n s???n ph???m m???i");
        String tenSanPham = scanner.nextLine();
        System.out.println("Th??m gi?? b??n m???i");
        double giaBan = Double.parseDouble(RegexData.checkStr(scanner.nextLine(),REGEX_SO_DUONG,"Nh???p sai ?????nh d???ng m???i nh???p l???i"));
        System.out.println("Th??m s??? l?????ng m???i");
        String soLuong = RegexData.checkStr(scanner.nextLine(),REGEX_SO_DUONG,"Nh???p sai ?????nh d???ng m???i nh???p l???i");
        System.out.println("Th??m nh?? s???n xu???t m???i");
        String nhaSanXuat = scanner.nextLine();
        System.out.println("Th??m gi?? nh???p kh???u m???i");
        double giaNhapKhau = Double.parseDouble(RegexData.checkStr(scanner.nextLine(),REGEX_SO_DUONG,"Nh???p sai ?????nh d???ng m???i nh???p l???i"));
        System.out.println("Th??m t???nh th??nh m???i");
        String tinhThanhNhap = scanner.nextLine();
        System.out.println("Th??m thu??? nh???p kh???u m???i");
        int thueNhapKhau =Integer.parseInt(RegexData.checkStr(scanner.nextLine(),REGEX_SO_DUONG,"Nh???p sai ?????nh d???ng m???i nh???p l???i"));
        NhapKhau nhapKhau = new NhapKhau(id, maSanPham, tenSanPham, giaBan, soLuong, nhaSanXuat, giaNhapKhau, tinhThanhNhap, thueNhapKhau);
        List<NhapKhau> nhapKhauList = new ArrayList<>();
        nhapKhauList.add(nhapKhau);
        write(nhapKhauList,true);
    }

    @Override
    public void delete() {
        boolean check = false;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nh???p m?? s???n ph???m c???n x??a");
        String xoaMaSanPham = scanner.nextLine();
        List<NhapKhau> nhapKhauList=readerNhapKhau();
        for (int i = 0; i < nhapKhauList.size(); i++) {
           display();
            if (nhapKhauList.get(i).getMaSanPham().equals(xoaMaSanPham)) {
                check = true;
                System.out.println("B???n c?? mu???n x??a kh??ng:");
                System.out.println("1.C??");
                System.out.println("2.Kh??ng");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        nhapKhauList.remove(i);
                        System.out.println("???? x??a r???i nh??!");
                        break;
                    case 2:
                        Menu.displayMenu();
                        break;
                }
            }
        }
        write(nhapKhauList,false);
    }

    @Override
    public void display() {
        List<NhapKhau> nhapKhauList=readerNhapKhau();
        for (NhapKhau nhapKhau : nhapKhauList) {
            System.out.println(nhapKhau);
        }
    }

    @Override
    public void search() {
        List<NhapKhau> nhapKhauList=readerNhapKhau();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nh???p m?? s???n ph???m c???n t??m");
        String checkMaSanPham = scanner.nextLine();
        for (int i = 0; i < nhapKhauList.size(); i++) {
            if (nhapKhauList.get(i).getMaSanPham().contains((checkMaSanPham))) {

                System.out.println("???? t??m ki???m th??nh c??ng");
                System.out.println(nhapKhauList.get(i));
            }
        }

    }
}
