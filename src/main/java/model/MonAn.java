package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.checkerframework.checker.units.qual.C;

@Entity
@Getter
@Setter
@ToString
public class MonAn {
    @Id
    @Column(columnDefinition = "nvarchar(5)")
    private String maMonAn;

    @Column(columnDefinition = "nvarchar(40)", nullable = false)
    private String tenMonAn;

    @Column(nullable = false)
    private double gia;

    @Column(nullable = false)
    private boolean trangThai;


    @Column(columnDefinition = "nvarchar(100)")
    private String urlHinhAnh;

    @ManyToOne
    @JoinColumn(name = "maKM")
    private KhuyenMai khuyenMai;

    @ManyToOne
    @JoinColumn(name = "maLoaiMon")
    private LoaiMonAn loaiMonAn;
}
