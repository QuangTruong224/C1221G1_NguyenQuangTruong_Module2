package de_thi_c11.service.Impl;

import de_thi_c11.controller.Menu;
import de_thi_c11.models.BenhAnVip;
import de_thi_c11.service.BenhAnVipService;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BenhAnVipImpl implements BenhAnVipService {
//  static   List<BenhAnVip> benhAnVipList=new ArrayList<>();
//    static {
//        benhAnVipList=readerBenhAnVip();
//    }
    public void write(List<BenhAnVip>benhAnVipList,boolean append){
        try {
            FileWriter fileWriter = new FileWriter("D:\\CodeGym\\module_2\\src\\de_on_tao_tu_lam\\data\\benhanvip.csv",append);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (BenhAnVip benhAnVip : benhAnVipList){
                bufferedWriter.write(benhAnVip.getSoThuTu()+","+benhAnVip.getMaBenhAn()+","+
                benhAnVip.getTenBenhNhan()+","+benhAnVip.getNgayRaVien()+","+benhAnVip.getNgayNhapVien()+","+
                        benhAnVip.getLyDoNhapVien()+","+benhAnVip.getLoaiVip()+","+benhAnVip.getThoiHanVip());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }
    public static  List<BenhAnVip> readerBenhAnVip() {
        List<BenhAnVip>benhAnVipList=new ArrayList<>();
           try{ FileReader fileReader = new FileReader(new File("D:\\CodeGym\\module_2\\src\\de_on_tao_tu_lam\\data\\benhanvip.csv"));
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            String[] temp;
            while ((line = bufferedReader.readLine()) != null) {
                temp = line.split(",");
                BenhAnVip benhAnVip = new BenhAnVip(Integer.parseInt(temp[0]),temp[1], temp[2], temp[3], temp[4],temp[5],temp[6],temp[7]);
                benhAnVipList.add(benhAnVip);

            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return benhAnVipList ;
}

    @Override
    public void addBenhAnVip() {
        List<BenhAnVip> benhAnVipList=readerBenhAnVip();
        Scanner scanner=new Scanner(System.in);
        int soThuTu;
        if (benhAnVipList.size()==0) {
            soThuTu=1;
        }else {
            soThuTu=benhAnVipList.get(benhAnVipList.size()-1).getSoThuTu()+1;
        }
//        System.out.println("Th??m s??? th??? t??? m???i");
//        int soThuTu= Integer.parseInt(scanner.nextLine());
        System.out.println("Th??m b?? b???nh ??n m???i");
        String maBenhAn=scanner.nextLine();
        System.out.println("Th??m t??n b???nh nh??n m???i");
        String tenBenhNhan=scanner.nextLine();
        System.out.println("Th??m m???i ng??y nh???p vi???n");
        String ngayNhapVien=scanner.nextLine();
        System.out.println("Th??m m???i ng??y ra vi???n");
        String ngayRaVien=scanner.nextLine();
        System.out.println("Th??m l?? do nh???p vi???n m???i");
        String lyDoNhapVien=scanner.nextLine();
        System.out.println("1. vip 1");
        System.out.println("2.  vip2");
        System.out.println("3. vip3 ");
        System.out.println("Th??m lo???i Vip m???i");
        String loaiVip = " ";
        int choose = 0;
        try {
            choose = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(" vui l??ng nh???p l???i nghe ");
        }
        switch (choose) {
            case 1:
                loaiVip = "vip1";
                break;
            case 2:
                loaiVip = "vip 2";
                break;
            case 3:
                loaiVip = "vip 3";
                break;
        }
        System.out.println("Th??m th???i h???n Vip m???i");
        String thoiHanVip=scanner.nextLine();
        BenhAnVip benhAnVip=new BenhAnVip(soThuTu,maBenhAn,tenBenhNhan,ngayNhapVien,ngayRaVien,lyDoNhapVien,loaiVip,thoiHanVip);
       benhAnVipList.add(benhAnVip);
write(benhAnVipList,true);
    }

    @Override
    public void deleteBenhAnVip() {
        List<BenhAnVip> benhAnVipList=readerBenhAnVip();
        boolean check = false;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nh???p m?? b???nh ??n c???n x??a");
        String xoaBenhAn = scanner.nextLine();
        for (int i = 0; i < benhAnVipList.size(); i++) {
            System.out.println(benhAnVipList.get(i).getMaBenhAn());
            if (benhAnVipList.get(i).getMaBenhAn().equals(xoaBenhAn)) {
                check = true;
                System.out.println("B???n c?? mu???n x??a m?? b???nh ??n:");
                System.out.println("1.C??");
                System.out.println("2.Kh??ng");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        benhAnVipList.remove(i);
                        System.out.println("???? x??a r???i nh??!");
                        break;
                    case 2:
                        Menu.displayMenu();
                        break;
                }
            }

        }
        write(benhAnVipList,false);
    }

    @Override
    public void displayBenhAnVip() {
        List<BenhAnVip> benhAnVipList=readerBenhAnVip();
            for (BenhAnVip benhAnVip:benhAnVipList
                 ) {
                System.out.println(benhAnVip);
            }
    }
}
