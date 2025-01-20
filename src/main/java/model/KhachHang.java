package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import model.LoaiKhachHang;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
public class KhachHang {

    @Id
    @Column(columnDefinition =  "nvarchar(8)",nullable = false)
    private String maKH;

    @Column(columnDefinition = "nvarchar(40)", nullable = false)
    private String tenKH;
    @Column(columnDefinition = "nvarchar(10)", nullable = false)
    private String sdt;
    @Column(columnDefinition = "nvarchar(30)", nullable = false)
    private String email;

    @Column(nullable = false)
    private LocalDate ngaySinh;

    @Column(nullable = false)
    private LocalDate ngayTao;
    @Column(nullable = false)
    private int diemTL;
    @Column(nullable = false)
    private boolean trangThai;


    @ManyToOne
    @JoinColumn(name = "maLoaiKH")
    private LoaiKhachHang loaiKH;

//    @OneToMany(mappedBy = "hoaDon")
//    private Set<HoaDon> listHoaDon;
//
//    @OneToMany(mappedBy = "donDatBan")
//    private Set<DonDatBan> listDonDatBan;
}
