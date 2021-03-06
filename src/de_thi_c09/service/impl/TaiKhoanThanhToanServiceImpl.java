package de_thi_c09.service.impl;

import de_on_tap.models.DienThoaiChinhHang;
import de_on_tap.utils.RegexData;
import de_thi_c09.models.TaiKhoanThanhToan;
import de_thi_c09.service.TaiKhoanThanhToanService;
import de_thi_c10.controller.Menu;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaiKhoanThanhToanServiceImpl implements TaiKhoanThanhToanService {
    private static final String REGEX_SO_DUONG = "^[1-9]\\d*$";

    public void write(List<TaiKhoanThanhToan> taiKhoanThanhToanList, boolean append) {
        try {
            FileWriter fileWriter = new FileWriter("D:\\CodeGym\\module_2\\src\\de_thi_c09\\data\\taikhoanthanhtoan.csv", append);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (TaiKhoanThanhToan taiKhoanThanhToan : taiKhoanThanhToanList) {
                bufferedWriter.write(taiKhoanThanhToan.getiD() + "," + taiKhoanThanhToan.getMaTaiKhoan() + "," +
                        taiKhoanThanhToan.getChuTaiKhoan() + "," + taiKhoanThanhToan.getNgayTaoTaiKhoan() + "," +
                        taiKhoanThanhToan.getSoThe() + "," + taiKhoanThanhToan.getSoTien());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static List<TaiKhoanThanhToan> readerTaiKhoanThanhToan() {
        List<TaiKhoanThanhToan> taiKhoanThanhToanList = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(new File("D:\\CodeGym\\module_2\\src\\de_thi_c09\\data\\taikhoanthanhtoan.csv"));
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            String[] temp;
            while ((line = bufferedReader.readLine()) != null) {
                temp = line.split(",");
                TaiKhoanThanhToan taiKhoanThanhToan = new TaiKhoanThanhToan(Integer.parseInt(temp[0]), temp[1],
                       temp[2], temp[3], temp[4], temp[5]);
                taiKhoanThanhToanList.add(taiKhoanThanhToan);
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return taiKhoanThanhToanList;
    }

    @Override
    public void addNew() {
//        int iD, String maTaiKhoan, String chuTaiKhoan,
//        String ngayTaoTaiKhoan, String soThe, String soTien
        List<TaiKhoanThanhToan> taiKhoanThanhToanList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int iD;
        if (taiKhoanThanhToanList.size() == 0) {
            iD = 1;
        } else {
            iD = taiKhoanThanhToanList.get(taiKhoanThanhToanList.size() - 1).getiD() + 1;
            String maTaiKhoan;
            while (true) {
                System.out.println("Th??m m?? t??i kho???n");
                maTaiKhoan = scanner.nextLine();
                ;
                if (maTaiKhoan.trim().equals("")) {
                    System.out.println("Nh???p l???i");
                } else {
                    break;
                }
            }
            String chuTaiKhoan;
            while (true) {
                System.out.println("Th??m ch??? t??i kho???n");
                chuTaiKhoan = scanner.nextLine();
                if (chuTaiKhoan.trim().equals("")) {
                    System.out.println("Nh???p l???i");
                } else {
                    break;
                }
            }
            String ngayTaoTaiKhoan;
            while (true) {
                System.out.println("Nh???p ng??y t???o t??i kho???n");
                ngayTaoTaiKhoan = scanner.nextLine();
                if (ngayTaoTaiKhoan.trim().equals("")) {
                    System.out.println("Nh???p l???i");
                } else {
                    break;
                }
            }
            String soThe;
            while (true) {
                System.out.println("Nh???p s??? th??? ");
                soThe = RegexData.checkStr(scanner.nextLine(), REGEX_SO_DUONG, "Ph???i nh???p s??? d????ng");
                if (soThe.trim().equals("")) {
                    System.out.println("Nh???p l???i");
                } else {
                    break;
                }
            }
            String soTien;
            while (true) {
                System.out.println("Nh???p s??? ti???n");
                soTien = RegexData.checkStr(scanner.nextLine(), REGEX_SO_DUONG, "Ph???i nh???p s??? ti???n l???n h??n 0");
                if (soTien.trim().equals("")) {
                    System.out.println("Nh???p l???i");
                } else {
                    break;
                }
            }
            TaiKhoanThanhToan taiKhoanThanhToan = new TaiKhoanThanhToan(iD, maTaiKhoan, chuTaiKhoan, ngayTaoTaiKhoan, soThe, soTien);
            List<TaiKhoanThanhToan> taiKhoanThanhToanList1 = new ArrayList<>();
            taiKhoanThanhToanList1.add(taiKhoanThanhToan);
            write(taiKhoanThanhToanList1, true);
        }

    }

    @Override
    public void delete() {
        boolean check = false;
        Scanner scanner = new Scanner(System.in);
        display();
        System.out.println("Nh???p m?? t??i kho???n c???n x??a");
        String xoaMaTaiKhoan = scanner.nextLine();
        List<TaiKhoanThanhToan> taiKhoanThanhToanList = readerTaiKhoanThanhToan();
        for (int i = 0; i < taiKhoanThanhToanList.size(); i++) {

            if (taiKhoanThanhToanList.get(i).getMaTaiKhoan().equals(xoaMaTaiKhoan)) {
                System.out.println("B???n c?? mu???n x??a kh??ng:");
                System.out.println("1.C??");
                System.out.println("2.Kh??ng");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        taiKhoanThanhToanList.remove(i);
                        System.out.println("???? x??a r???i nh??!");
                        break;
                    case 2:
                        Menu.displayMenu();
                        break;
                }
            }
        }
        write(taiKhoanThanhToanList, false);

    }


    @Override
    public void display() {
        List<TaiKhoanThanhToan> taiKhoanThanhToanList = readerTaiKhoanThanhToan();
        for (TaiKhoanThanhToan taiKhoanThanhToan : taiKhoanThanhToanList) {
            System.out.println(taiKhoanThanhToan);
        }
    }

    @Override
    public void search() {
        List<TaiKhoanThanhToan> taiKhoanThanhToanList=readerTaiKhoanThanhToan();
        Scanner scanner=new Scanner(System.in);
        display();
        try {
            System.out.println("Nh???p m?? t??i kho???n c???n t??m");
            String checkMaTaiKhoan=scanner.nextLine();
            for (int i = 0; i < taiKhoanThanhToanList.size(); i++) {
                if (taiKhoanThanhToanList.get(i).getMaTaiKhoan().equals(checkMaTaiKhoan)) {
                    System.out.println("???? t??m ki???m th??nh c??ng");
                    System.out.println(taiKhoanThanhToanList.get(i));
                }
            }
        }catch(Exception e) {
            System.out.println("Vui l??ng nh???p l???i");
        }

    }
}
