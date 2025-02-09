package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
public class KhuyenMai {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "nvarchar(5)")
    private String maKM;
    @Column(columnDefinition = "nvarchar(40)", nullable = false)
    private String tenKM;

    private double chietKhau;

    @Column(nullable = false)
    private LocalDateTime ngayBD;

    @Column(nullable = false)
    private LocalDateTime ngayKT;

    @Column(nullable = false)
    private int soLuong;

    @ManyToOne
    @JoinColumn(name = "maLoaiKM")
    private LoaiKhuyenMai loaiKM;
}
