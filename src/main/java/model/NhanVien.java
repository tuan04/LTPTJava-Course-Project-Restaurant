package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
public class NhanVien {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "nvarchar(8)")
    private String maNV;

    @Column(columnDefinition = "nvarchar(40)", nullable = false)
    private String tenNV;

    @Column(nullable = false)
    private boolean gioiTinh;

    @Column(columnDefinition = "char(10)", nullable = false)
    private String sdt;

    @Column(columnDefinition = "nvarchar(50)", nullable = false)
    private String email;

    @Column(columnDefinition = "date", nullable = false)
    private LocalDate ngaySinh;

    @Column(columnDefinition = "varchar(64)", nullable = false)
    private String matKhau;

    @Column(columnDefinition = "varchar(6)")
    private String maXacThuc;

    @Column(nullable = false)
    private boolean trangThai;

    @Column(columnDefinition = "nvarchar(10)", nullable = false)
    private String loaiNV;
}
