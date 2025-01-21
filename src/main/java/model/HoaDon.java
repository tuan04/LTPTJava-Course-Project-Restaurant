package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
public class HoaDon {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "nvarchar(11)")
    private String maHD;

    @Column(nullable = false)
    private LocalDate ngayTao;

    @Column(nullable = false)
    private boolean trangThai;

    @Column(nullable = false)
    private LocalDateTime gioVao;

    @Column(nullable = false)
    private LocalDateTime gioRa;

    @Column(nullable = false)
    private double tongTien;

    @Column(nullable = false)
    private double tongTienThanhToan;

    @Column(nullable = false)
    private double tongTienGiamGia;

    @ToString.Exclude
    @OneToMany(mappedBy = "hoaDon")
    private Set<ChiTietHoaDon> lítCTHD;

    @ManyToOne
    @JoinColumn(name = "maBan")
    private Ban ban;

    @ManyToOne
    @JoinColumn(name = "maKhachHang")
    private KhachHang khachHang;

    @OneToOne
    @JoinColumn(name = "maDonDatBan")
    private DonDatBan donDatBan;

    @ManyToOne
    @JoinColumn(name = "maNhanVien")
    private NhanVien nhanVien;

    @ManyToOne
    @JoinColumn(name = "maKhuyenMai")
    private KhuyenMai khuyenMai;
}
