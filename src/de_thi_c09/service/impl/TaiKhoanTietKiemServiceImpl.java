package de_thi_c09.service.impl;

import de_on_tap.utils.RegexData;
import de_thi_c09.models.TaiKhoanThanhToan;
import de_thi_c09.models.TaiKhoanTietKiem;
import de_thi_c09.service.TaiKhoanTietKiemService;
import de_thi_c10.controller.Menu;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaiKhoanTietKiemServiceImpl implements TaiKhoanTietKiemService {
    private static final String REGEX_SO_DUONG = "^[1-9]\\d*$";

    public void write(List<TaiKhoanTietKiem> taiKhoanTietKiemList, boolean append) {
        try {
            FileWriter fileWriter = new FileWriter("D:\\CodeGym\\module_2\\src\\de_thi_c09\\data\\taikhoantietkiem.csv", append);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (TaiKhoanTietKiem taiKhoanTietKiem : taiKhoanTietKiemList) {
                bufferedWriter.write(taiKhoanTietKiem.getiD() + "," + taiKhoanTietKiem.getMaTaiKhoan() + "," +
                        taiKhoanTietKiem.getChuTaiKhoan() + "," + taiKhoanTietKiem.getNgayTaoTaiKhoan() + "," +
                        taiKhoanTietKiem.getSoTienGui() + "," + taiKhoanTietKiem.getNgayGuiTien()
                        + "," + taiKhoanTietKiem.getLaiXuat() + "," + taiKhoanTietKiem.getKiHan());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static List<TaiKhoanTietKiem> readerTaiKhoanTietKiem() {
        List<TaiKhoanTietKiem> taiKhoanTietKiemList = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(new File("D:\\CodeGym\\module_2\\src\\de_thi_c09\\data\\taikhoantietkiem.csv"));
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            String[] temp;
            while ((line = bufferedReader.readLine()) != null) {
                temp = line.split(",");
                TaiKhoanTietKiem taiKhoanTietKiem = new TaiKhoanTietKiem(Integer.parseInt(temp[0]), temp[1],
                        temp[2], temp[3], Integer.parseInt(temp[4]), temp[5],temp[6],temp[7]);
                taiKhoanTietKiemList.add(taiKhoanTietKiem);
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return taiKhoanTietKiemList;
    }

    @Override
    public void addNew() {
//        int iD, String maTaiKhoan, String chuTaiKhoan,
//        String ngayTaoTaiKhoan, String soThe, String soTien
        List<TaiKhoanTietKiem> taiKhoanTietKiemList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int iD;
        if (taiKhoanTietKiemList.size() == 0) {
            iD = 1;
        } else {
            iD = taiKhoanTietKiemList.get(taiKhoanTietKiemList.size() - 1).getiD() + 1;
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
            int soTienGui;
           do {
               try {
                   System.out.println("Nh???p s??? ti???n g???i");
                   soTienGui=Integer.parseInt(RegexData.checkStr(scanner.nextLine(),REGEX_SO_DUONG,"Ph???i nh???p s??? ti???n l???n h??n 0??"));
                   break;
               } catch (NumberFormatException e) {
                   System.out.println("Nh???p sai");
               }
           }while (true);
            String ngayGuiTien;
            while (true) {
                System.out.println("Nh???p ng??y g???i ti???n");
                ngayGuiTien=scanner.nextLine();
                if (ngayGuiTien.trim().equals("")) {
                    System.out.println("Nh???p l???i");
                } else {
                    break;
                }
            }
            String laiXuat;
            while (true) {
                System.out.println("Nh???p l??i xu???t");
                laiXuat=scanner.nextLine();
                if (laiXuat.trim().equals("")) {
                    System.out.println("Nh???p l???i");
                } else {
                    break;
                }
            }
            String kiHan;
            while (true) {
                System.out.println("Nh???p k?? h???n");
                kiHan=scanner.nextLine();
                if (kiHan.trim().equals("")) {
                    System.out.println("Nh???p l???i");
                } else {
                    break;
                }
            }
            TaiKhoanTietKiem taiKhoanTietKiem = new TaiKhoanTietKiem(iD, maTaiKhoan, chuTaiKhoan, ngayTaoTaiKhoan,soTienGui,ngayGuiTien,laiXuat,kiHan);
            List<TaiKhoanTietKiem> taiKhoanTietKiemList1 = new ArrayList<>();
            taiKhoanTietKiemList1.add(taiKhoanTietKiem);
            write(taiKhoanTietKiemList1, true);
        }

    }

    @Override
    public void delete() {
        boolean check = false;
        Scanner scanner = new Scanner(System.in);
        display();
        System.out.println("Nh???p m?? t??i kho???n c???n x??a");
        String xoaMaTaiKhoan = scanner.nextLine();
        List<TaiKhoanTietKiem> taiKhoanTietKiemList = readerTaiKhoanTietKiem();
        for (int i = 0; i < taiKhoanTietKiemList.size(); i++) {

            if (taiKhoanTietKiemList.get(i).getMaTaiKhoan().equals(xoaMaTaiKhoan)) {
                System.out.println("B???n c?? mu???n x??a kh??ng:");
                System.out.println("1.C??");
                System.out.println("2.Kh??ng");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        taiKhoanTietKiemList.remove(i);
                        System.out.println("???? x??a r???i nh??!");
                        break;
                    case 2:
                        Menu.displayMenu();
                        break;
                }
            }
        }
        write(taiKhoanTietKiemList, false);

    }


    @Override
    public void display() {
        List<TaiKhoanTietKiem> taiKhoanTietKiemList = readerTaiKhoanTietKiem();
        for (TaiKhoanTietKiem taiKhoanTietKiem : taiKhoanTietKiemList) {
            System.out.println(taiKhoanTietKiem);
        }
    }

    @Override
    public void search() {
        List<TaiKhoanTietKiem> taiKhoanTietKiemList = readerTaiKhoanTietKiem();
        Scanner scanner = new Scanner(System.in);
        display();
        try {
            System.out.println("Nh???p m?? t??i kho???n c???n t??m");
            String checkMaTaiKhoan = scanner.nextLine();
            for (int i = 0; i < taiKhoanTietKiemList.size(); i++) {
                if (taiKhoanTietKiemList.get(i).getMaTaiKhoan().equals(checkMaTaiKhoan)) {
                    System.out.println("???? t??m ki???m th??nh c??ng");
                    System.out.println(taiKhoanTietKiemList.get(i));
                }
            }
        } catch (Exception e) {
            System.out.println("Vui l??ng nh???p l???i");
        }

    }
}
