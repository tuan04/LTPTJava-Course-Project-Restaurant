package model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@IdClass(ChiTietDatBan.ChiTietDatBanId.class)
public class ChiTietDatBan implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "maDonDatBan")
    private DonDatBan donDatBan;

    @Id
    @ManyToOne
    @JoinColumn(name = "maMonAn")
    private MonAn monAn;

    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChiTietDatBanId implements Serializable {
        private DonDatBan donDatBan;
        private MonAn monAn;
    }

    private int soLuong;
    private double donGia;
    private double thanhTien;

}