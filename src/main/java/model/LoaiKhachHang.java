package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.checkerframework.checker.units.qual.C;

import java.util.Set;

@Entity
@Getter
@Setter
@ToString
public class LoaiKhachHang {
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
