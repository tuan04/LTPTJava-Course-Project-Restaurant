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

@IdClass(ChiTietHoaDon.ChiTietHoaDonId.class)
public class ChiTietHoaDon implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "maHoaDon")
    private HoaDon hoaDon;

    @Id
    @ManyToOne
    @JoinColumn(name = "maMonAn")
    private MonAn monAn;

    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChiTietHoaDonId implements Serializable {
        private HoaDon hoaDon;
        private MonAn monAn;
    }

    private int soLuong;
    private double donGia;
    private double thanhTien;

}