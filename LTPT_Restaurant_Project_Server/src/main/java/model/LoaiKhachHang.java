package model;

import jakarta.persistence.*;
import lombok.*;
import org.checkerframework.checker.units.qual.C;

import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class LoaiKhachHang implements Serializable {
    @Id
    @Column(columnDefinition = "nvarchar(5)")
    private String maLoaiKH;

    @Column(columnDefinition = "nvarchar(15)", nullable = false)
    private String tenLoaiKH;

    @Column(nullable = false)
    private double giamGiaTV;

    @Column(nullable = false)
    private double giamGiaSN;
}
