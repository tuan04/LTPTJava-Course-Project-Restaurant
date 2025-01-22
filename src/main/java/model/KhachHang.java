package model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class KhachHang {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition =  "nvarchar(8)",nullable = false)
    private String maKH;

    @Column(columnDefinition = "nvarchar(40)", nullable = false)
    private String tenKH;
    @Column(columnDefinition = "nvarchar(10)", nullable = false)
    private String sdt;
    @Column(columnDefinition = "nvarchar(60)", nullable = false)
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
