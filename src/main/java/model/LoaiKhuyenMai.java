package model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class LoaiKhuyenMai implements Serializable {
    @Id
    @Column(columnDefinition = "nvarchar(5)")
    private String maLoaiKM;

    @Column(columnDefinition = "nvarchar(15)", nullable = false)
    private String tenLoaiKM;
}
