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
public class LoaiMonAn {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "nvarchar(5)")
    private String maLoaiMon;

    @Column(columnDefinition = "nvarchar(40)", nullable = false)
    private String tenLoaiMon;
}
