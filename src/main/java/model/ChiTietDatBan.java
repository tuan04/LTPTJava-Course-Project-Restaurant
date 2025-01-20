package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class ChiTietDatBan {
    @Id
    @ManyToOne
    @JoinColumn(name = "maDonDatBan")
    private DonDatBan donDatBan;

    @Id
    @ManyToOne
    @JoinColumn(name = "maMonAn")
    private MonAn monAn;

    private int soLuong;
    private double donGia;
    private double thanhTien;

}
