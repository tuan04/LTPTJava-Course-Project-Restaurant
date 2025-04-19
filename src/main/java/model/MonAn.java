package model;

import jakarta.persistence.*;
import lombok.*;
import org.checkerframework.checker.units.qual.C;

import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class MonAn implements Serializable {
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
