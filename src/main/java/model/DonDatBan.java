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
public class DonDatBan {
    @Id
    @Column(columnDefinition = "nvarchar(12)")
    private String maDDB;

    @Column(nullable = false)
    private int soLuongKH;

    @Column(nullable = false)
    private boolean trangThai;

    @Column(columnDefinition = "datetime",nullable = false)
    private LocalDateTime gioDat;

    @Column(columnDefinition = "datetime")
    private LocalDateTime gioHuy;

    @Column(nullable = false)
    private LocalDate ngayTao;

    private String ghiChu;

    private double tienCoc;

    private double tienHoanCoc;

    @ToString.Exclude
    @OneToMany(mappedBy = "donDatBan")
    private Set<ChiTietDatBan> listCTDB;

    @ManyToOne
    @JoinColumn(name = "maNhanVien")
    private NhanVien nhanVien;

    @ManyToOne
    @JoinColumn(name = "maBan")
    private Ban ban;

    @ManyToOne
    @JoinColumn(name = "maKhachHang")
    private KhachHang khachHang;
}
