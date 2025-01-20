package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class NhanVien {
    @Id
    @Column(columnDefinition = "nvarchar(8)")
    private String maNV;

    @Column(columnDefinition = "nvarchar(40)", nullable = false)
    private String tenNV;

    @Column(nullable = false)
    private boolean gioiTinh;

    @Column(columnDefinition = "char(10)", nullable = false)
    private String sdt;

    @Column(columnDefinition = "nvarchar(30)", nullable = false)
    private String email;

    @Column(columnDefinition = "date", nullable = false)
    private String ngaySinh;

    @Column(columnDefinition = "varchar(64)", nullable = false)
    private String matKhau;

    @Column(columnDefinition = "varchar(6)", nullable = false)
    private String maXacThuc;

    @Column(nullable = false)
    private boolean trangThai;

    @Column(columnDefinition = "nvarchar(10)", nullable = false)
    private String loaiNV;
}
