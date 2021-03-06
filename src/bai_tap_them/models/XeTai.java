package bai_tap_them.models;

public class XeTai extends PhuongTien{
    private String  trongTai;

    public XeTai() {
    }

    public XeTai(String bienKiemSoat, String hangSanXuat, String namSanXuat, String chuSoHuu, String trongTai) {
        super(bienKiemSoat, hangSanXuat, namSanXuat, chuSoHuu);
        this.trongTai = trongTai;
    }

    public String getTrongTai() {
        return trongTai;
    }

    public void setTrongTai(String trongTai) {
        this.trongTai = trongTai;
    }

    @Override
    public String toString() {
        return "XeTai{" +"bienKiemSoat='" + getBienKiemSoat() + '\'' +
                ", hangSanXuat='" + getHangSanXuat() + '\'' +
                ", namSanXuat=" + getNamSanXuat() +
                ", chuSoHuu='" + getChuSoHuu() + '\'' +
                "trongTai='" + trongTai + '\'' +
                '}';
    }
}
