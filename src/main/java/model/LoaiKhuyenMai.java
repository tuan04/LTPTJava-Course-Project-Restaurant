package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Entity
@Getter
@Setter
@ToString
public class LoaiKhuyenMai {
    @Id
    @Column(columnDefinition = "nvarchar(5)")
    private String maLoaiKM;

    @Column(columnDefinition = "nvarchar(15)", nullable = false)
    private String tenLoaiKM;
}
